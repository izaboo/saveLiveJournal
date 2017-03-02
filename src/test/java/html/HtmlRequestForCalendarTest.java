package html;

import com.livejournal.htmlunit.HtmlPage;
import org.junit.Assert;
import org.junit.Test;

public class HtmlRequestForCalendarTest extends Assert
{
    @Test
    public void testHtmlRequestForCalendarPageWithNotExistedJournal() {
        assertEquals (new HtmlPage("tuyt").getHTMLPageContent("calendar"), null);
    }
    @Test
    public void testHtmlRequestForCalendarPageWithBlank() {

        assertEquals (new HtmlPage(" ").getHTMLPageContent("calendar"), null);
    }
    @Test
    public void testHtmlRequestForCalendarPageWithEmptyString() {
        assertEquals (new HtmlPage("").getHTMLPageContent("calendar"), null);
    }
    @Test
    public void testHtmlRequestForCalendarPageWithNull() {
        assertNotEquals (new HtmlPage(null).getHTMLPageContent("calendar"), null);
    }
}
