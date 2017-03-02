package calendar;

import com.livejournal.lj.Calendar;
import com.livejournal.lj.Journal;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//
public class CalendarTest extends Assert
{
    @Test
    public  void testStartYearCorrectness (){
        assertEquals (LocalDate.parse("2006-01-01"), Calendar.getStartYear("2008 2006 2007"));
    }

    @Test
    public  void testStartYearCorrectnessMoreThan20 (){
        assertEquals (LocalDate.parse("2017-01-01"), Calendar.getStartYear("1995 1996"));
    }

    @Test
    public  void testStartYearCorrectness20 (){
        assertEquals (LocalDate.parse("1997-01-01"), Calendar.getStartYear("1995 1996 1997"));
    }

    @Test
    public void testIdentifyFirstPostYear_KnownLJ() {
        Journal testJournal = mock(Journal.class);
        when(testJournal.isExist()).thenReturn(true);
        when(testJournal.getStartDate()).thenReturn(LocalDate.parse("2007-01-01"));
        assertEquals (LocalDate.parse("2007-01-01"), testJournal.getStartDate());
    }

    @Test
    public void testIdentifyFirstPostYear_IncorrectLJ() {
        Journal journal = new Journal(" ", null);
        assertEquals (null, journal.getStartDate());
    }
}
