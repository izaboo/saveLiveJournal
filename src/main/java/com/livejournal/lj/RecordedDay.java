package com.livejournal.lj;
import java.util.ArrayList;
//one day of records holder
public class RecordedDay {

    String date;
    ArrayList<Record> records = new ArrayList<Record>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Record> getRecords() {
        if (records == null) {
            records = new ArrayList<Record>();
        }
        return this.records;
    }
}
