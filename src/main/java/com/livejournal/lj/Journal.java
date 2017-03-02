package com.livejournal.lj;

import com.livejournal.htmlunit.HtmlPage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.livejournal.utils.Helpers.println;
//main Journal class
public class Journal {
    String journalName;
    ArrayList<RecordedDay> recordedDays;
    Boolean isExist = false;
    LocalDate startYear = null;

    public Journal(String name, String startYear) {
        this.journalName = name;
        final String REGEX = "\\d{4}";
        Pattern pattern;
        Matcher matcher;

        String calendarPageContent = checkIfJournalExists();
        if (calendarPageContent != null) {
            isExist = true;
            if (startYear == null)
                this.startYear = Calendar.getStartYear(calendarPageContent);
            else {
                pattern = Pattern.compile(REGEX);
                matcher = pattern.matcher(startYear);
                if (matcher.matches() &&  Integer.parseInt(startYear) <= LocalDate.now().getYear())
                    this.startYear = LocalDate.parse(startYear + "-01-01");
                else
                {
                    println("LiveJournal's start year in not in format \"4 digits\" or is in future: " +  startYear);
                    this.startYear = null;
                }
            }
        }
        else
            println("LiveJournal " +  name +  " can't be found.");
    }

    String checkIfJournalExists() {
        HtmlPage calendarPage = new HtmlPage(journalName);
        return calendarPage.getHTMLPageContent("calendar");
    }

    public Boolean isExist() {
        return isExist;
    }

    public LocalDate getStartDate() {
        return startYear;
    }

    public String getJournalName() {
        return journalName;
    }

    public ArrayList<RecordedDay> getRecordedDays() {
        if (recordedDays == null) {
            recordedDays = new ArrayList<RecordedDay>();
        }
        return this.recordedDays;
    }
}
