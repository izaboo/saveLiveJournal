package com.livejournal;
import com.livejournal.book.BookCreator;
import com.livejournal.book.Fb2Creator;
import com.livejournal.lj.Journal;

import static com.livejournal.utils.Helpers.println;
import static com.livejournal.xmlRpc.LJContent.fromContent;
//main class
public class SaveJournal {
    public static void main(String args[]) {
        String year = System.getProperty("year");
        String startMessage = year == null ? "journal from the begining" : "for year " + year;
        if (args.length == 0) {
            println("Please, provide livejournal user names and run again");
            return;
        } else
            for (String liveJournal : args) {
                println("Saving " + liveJournal + " " + startMessage);
                BookCreator fb2Creator = new Fb2Creator();
                Journal journal = new Journal(liveJournal, year);
                fb2Creator.createBookFrom(fromContent(journal));
            }
    }
}
