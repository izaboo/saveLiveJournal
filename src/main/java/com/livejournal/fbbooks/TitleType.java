package com.livejournal.fbbooks;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "titleType", propOrder = {
    "pOrEmptyLine"
})
public class TitleType {

    @XmlElements({
        @XmlElement(name = "p", type = PType.class),
        @XmlElement(name = "empty-line")
    })
    protected List<Object> pOrEmptyLine;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;
    public List<Object> getPOrEmptyLine() {
        if (pOrEmptyLine == null) {
            pOrEmptyLine = new ArrayList<Object>();
        }
        return this.pOrEmptyLine;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String value) {
        this.lang = value;
    }

}
