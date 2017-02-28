package bookCreator;

import com.livejournal.book.BookCreator;
import com.livejournal.book.Fb2Creator;
import com.livejournal.lj.Journal;
import com.livejournal.lj.Record;
import com.livejournal.lj.RecordedDay;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;


import static com.livejournal.xmlRpc.LJContent.fromContent;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BookCreatorTest {

    @Test()
    public void testCreateBookForNull() {
        BookCreator above = new Fb2Creator();
        above.createBookFrom(null);
    }

    @Test()
    public void testCreateBookForEmptyDaysRecords() {
        BookCreator above = new Fb2Creator();
        Journal testJournal = mock(Journal.class);
        when(testJournal.isExist()).thenReturn(true);
        above.createBookFrom(testJournal);
    }

    @Test()
    public void testCreateBookForUnexistedJournal() {
        BookCreator above = new Fb2Creator();
        Journal testJournal = mock(Journal.class);
        when(testJournal.isExist()).thenReturn(false);
        above.createBookFrom(testJournal);
    }

    @Test()
    public void testCreateBookAllMethods() {
        File f = new File("test.fb2");
        if (f.exists())
            f.delete();
        ArrayList<RecordedDay> testRecords = new ArrayList<RecordedDay>();
        RecordedDay testRecordedDay = new RecordedDay();
        Record testRecord = new Record();
        testRecord.setTitle("testTitle");
        testRecord.setSubject("test subject");
        testRecordedDay.setDate("testDate");
        testRecordedDay.getRecords().add(testRecord);
        testRecords.add(testRecordedDay);

        Journal testJournal = mock(Journal.class);
        when(testJournal.isExist()).thenReturn(true);
        when(testJournal.getJournalName()).thenReturn("test");
        when(testJournal.getStartDate()).thenReturn(LocalDate.now());
        when(testJournal.getRecordedDays()).thenReturn(testRecords);

        BookCreator above = new Fb2Creator();
        above.createBookFrom(testJournal);
        f = new File("test.fb2");
        assertTrue(f.exists());
        f.delete();
    }

    @Test
    public void testxmlRpcNullJournalIsReturned() {
        Journal testJournal1 = fromContent(null);
        assertEquals(null, testJournal1);
    }

    @Test
    public void testJournalYearTrash() {
        Journal journal = new Journal(null, "13213");
        assertEquals(null, journal.getStartDate());
        journal = new Journal(null, "3213");
        assertEquals(null, journal.getStartDate());
        journal = new Journal(null, "133");
        assertEquals(null, journal.getStartDate());
        journal = new Journal(null, "asdf");
        assertEquals(null, journal.getStartDate());
        journal = new Journal(null, "2015");
        assertEquals("2015-01-01", journal.getStartDate());
    }

}

