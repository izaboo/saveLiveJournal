package com.livejournal.xmlRpc;
import com.livejournal.htmlunit.HtmlPage;
import com.livejournal.lj.Journal;
import com.livejournal.lj.Record;
import com.livejournal.lj.RecordedDay;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.livejournal.utils.Helpers.*;
import static com.livejournal.utils.Helpers.println;
//create Journal class from lj content via xmlRpc request to journal pages
public class LJContent {
    static Map<String, Object> struct = new HashMap<String, Object>();

    public static Journal fromContent(Journal journal) {
        if (journal == null || !journal.isExist() || journal.getStartDate() == null) {
            println("LiveJournal can't be saved.");
            return null;
        }
        LocalDate startDate = journal.getStartDate();
        XmlRpcClient client = createRequest(journal.getJournalName());
        LocalDate today = LocalDate.now();
        while (startDate.isBefore(today)) {
            struct.put("year", String.valueOf(startDate.getYear()));
            struct.put("month", String.valueOf(startDate.getMonthValue()));
            struct.put("day", String.valueOf(startDate.getDayOfMonth()));
            Object[] events = makeRequest(client);
            print(".");
            if (events != null && events.length != 0) {
                RecordedDay recordedDay = new RecordedDay();
                recordedDay.setDate(startDate.toString());
                for (Object ev : events) {
                    Record record = new Record();
                    record.setTitle(formStringValue(ev, "subject"));
                    record.setSubject(formStringValue(ev, "event"));
                    recordedDay.getRecords().add(record);
                }
                journal.getRecordedDays().add(recordedDay);
            }
            startDate = startDate.plusDays(1);
        }
        return journal;
    }

    static String formStringValue(Object object, String fieldName) {
        String value = toUnicodeString(((Map) object).get(fieldName));
        if (value != null)
            return br2nl(value);
        else
            return "";
    }

    static String cleanupHtml(String subject) {
        return Jsoup.clean(subject, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    static String toUnicodeString(Object text) {
        if (text == null) {
            return null;
        } else if (text instanceof byte[]) {
            return new String((byte[]) text, Charset.forName("UTF-8"));
        } else {
            return String.valueOf(text);
        }
    }

    static XmlRpcClient createRequest(String journalName) {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        struct.put("usejournal", journalName);
        struct.put("ver", 1);
        struct.put("noprops", "1");
        struct.put("selecttype", "day");

        XmlRpcClient client = new XmlRpcClient();
        try {
            config.setServerURL(new URL("http://www.livejournal.com/interface/xmlrpc"));
            client.setConfig(config);
        } catch (MalformedURLException e) {
            return null;
        }
        return client;
    }

    static Object[] makeRequest(XmlRpcClient client) {
        Object[] events = null;
        try {
            Map result = (Map) client.execute("LJ.XMLRPC.getevents", new Object[]{struct});
            events = (Object[]) result.get("events");
        } catch (XmlRpcException e) {
            println("Request for lj failed - may be issue of internet connection or lj's API etc.");
        }
        return events;
    }

    static String br2nl(String html) {
        if (html == null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        s = s.replaceAll("&nbsp;", " ");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }
}
