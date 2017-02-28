package com.livejournal.book;
import com.livejournal.fbbooks.*;
import com.livejournal.lj.Journal;
import com.livejournal.lj.Record;
import com.livejournal.lj.RecordedDay;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.livejournal.utils.Helpers.println;

public class Fb2Creator implements BookCreator {
    Journal recordedJournal;
    FictionBook book;

    @Override
    public void createBookFrom(Journal journal) {
        if (journal == null || !journal.isExist()) {
            println("Book for user hasn't been created.");
            return;
        }
        if (journal.getRecordedDays().size() == 0) {
            println("Book for user hasn't been created since no day records were obtained from journal.");
            return;
        }

        recordedJournal = journal;
        book = new FictionBook();
        formBookDescription();
        formBookBody();
        formFB2File();
    }

    void formBookDescription() {
        FictionBook.Description description = new FictionBook.Description();
        TitleInfoType titleInfo = new TitleInfoType();
        TextFieldType title = new TextFieldType();
        title.setValue(recordedJournal.getJournalName());
        titleInfo.setBookTitle(title);
        List<TitleInfoType.Genre> genre = new ArrayList<>();
        TitleInfoType.Genre specGenre = new TitleInfoType.Genre();
        specGenre.setValue(GenreType.ESSAYS);
        genre.add(specGenre);
        titleInfo.getGenre().add(specGenre);
        description.setTitleInfo(titleInfo);
        TitleInfoType.Author author = new TitleInfoType.Author();
        TextFieldType lastName = new TextFieldType();
        lastName.setValue(recordedJournal.getJournalName());
        author.getContent().add(new JAXBElement<TextFieldType>(new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "nickname"), TextFieldType.class, AuthorType.class, lastName));
        titleInfo.getAuthor().add(author);
        book.setDescription(description);
    }

    void formBookBody() {
        FictionBook.Body body = new FictionBook.Body();
        body.getSection().addAll(formAllBookSections(recordedJournal.getRecordedDays()));
        book.getBody().add(body);

    }

    void formFB2File() {
        try {

            File file = new File(recordedJournal.getJournalName() + ".fb2");
            JAXBContext jaxbContext = JAXBContext.newInstance(FictionBook.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(book, file);
//            jaxbMarshaller.marshal(book, System.out);
        } catch (JAXBException e) {
            println("Book for user " + recordedJournal.getJournalName() + "hasn't been created");
            e.printStackTrace();
        }
    }

    ArrayList<SectionType> formAllBookSections(ArrayList<RecordedDay> recordedJournal) {
        ArrayList<SectionType> allBook = new ArrayList<SectionType>();
        String newline = System.getProperty("line.separator");

        for (RecordedDay day : recordedJournal) {
            PType sectionTitle = new PType();
            sectionTitle.getContent().add(day.getDate());
            TitleType titleTypeForSection = new TitleType();
            titleTypeForSection.getPOrEmptyLine().add(sectionTitle);

            SectionType dayRecord = new SectionType();
            dayRecord.setTitle(titleTypeForSection);

            for (Record recordForDay : day.getRecords()) {
                PType recordTitle = new PType();
                recordTitle.getContent().add(recordForDay.getTitle());
                TitleType dayRecordTitle = new TitleType();
                dayRecordTitle.getPOrEmptyLine().add(recordTitle);

                SectionType dayRecordSection = new SectionType();
                dayRecordSection.setTitle(dayRecordTitle);

                for (String paragraph : recordForDay.getSubject().split(newline)) {
                    PType text = new PType();
                    text.getContent().add(paragraph);
                    JAXBElement<PType> pTypeJAXBElement = new JAXBElement<PType>(new QName("http://www.gribuser.ru/xml/fictionbook/2.0", "p"), PType.class, SectionType.class, text);
                    dayRecordSection.getPOrImageOrPoem().add(pTypeJAXBElement);
                }
                dayRecord.getSection().add(dayRecordSection);
            }
            allBook.add(dayRecord);
        }
        return allBook;
    }
}
