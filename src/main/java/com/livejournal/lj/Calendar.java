package com.livejournal.lj;

import java.time.LocalDate;
import com.livejournal.htmlunit.*;
import static com.livejournal.utils.Helpers.*;

public class Calendar {

    public static LocalDate getStartYear(String pageContent) {
        if (pageContent == null) {
            println ("Calendar page isn't exist - livejournal won't be saved");
            return null;
        }
        else
            return LocalDate.parse(identifyFirstPostYear(pageContent) + "-01-01");
    }

    static String identifyFirstPostYear (String pageText){
        Integer currentYear = LocalDate.now().getYear();
        Integer startYear = currentYear - 20;
        while (startYear <= currentYear)
        {
            if (pageText.contains(String.valueOf(startYear)))
                return startYear.toString();
            else
                startYear++;
        }
        return currentYear.toString();
    }
}
