package com.livejournal.fbbooks;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sectionType", propOrder = {
    "title",
    "epigraph",
    "image",
    "annotation",
    "section",
    "p",
    "poem",
    "subtitle",
    "cite",
    "emptyLine",
    "table",
    "pOrImageOrPoem"
})
public class SectionType {

    protected TitleType title;
    protected List<EpigraphType> epigraph;
    protected ImageType image;
    protected AnnotationType annotation;
    protected List<SectionType> section;
    protected PType p;
    protected PoemType poem;
    protected PType subtitle;
    protected CiteType cite;
    @XmlElement(name = "empty-line")
    protected Object emptyLine;
    protected TableType table;
    @XmlElementRefs({
        @XmlElementRef(name = "cite", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "subtitle", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "poem", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "table", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "image", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> pOrImageOrPoem;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link TitleType }
     *     
     */
    public TitleType getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link TitleType }
     *     
     */
    public void setTitle(TitleType value) {
        this.title = value;
    }

    /**
     * Gets the value of the epigraph property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the epigraph property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEpigraph().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EpigraphType }
     * 
     * 
     */
    public List<EpigraphType> getEpigraph() {
        if (epigraph == null) {
            epigraph = new ArrayList<EpigraphType>();
        }
        return this.epigraph;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link ImageType }
     *     
     */
    public ImageType getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageType }
     *     
     */
    public void setImage(ImageType value) {
        this.image = value;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationType }
     *     
     */
    public AnnotationType getAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationType }
     *     
     */
    public void setAnnotation(AnnotationType value) {
        this.annotation = value;
    }

    /**
     * Gets the value of the section property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the section property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SectionType }
     * 
     * 
     */
    public List<SectionType> getSection() {
        if (section == null) {
            section = new ArrayList<SectionType>();
        }
        return this.section;
    }

    /**
     * Gets the value of the p property.
     * 
     * @return
     *     possible object is
     *     {@link PType }
     *     
     */
    public PType getP() {
        return p;
    }

    /**
     * Sets the value of the p property.
     * 
     * @param value
     *     allowed object is
     *     {@link PType }
     *     
     */
    public void setP(PType value) {
        this.p = value;
    }

    /**
     * Gets the value of the poem property.
     * 
     * @return
     *     possible object is
     *     {@link PoemType }
     *     
     */
    public PoemType getPoem() {
        return poem;
    }

    /**
     * Sets the value of the poem property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoemType }
     *     
     */
    public void setPoem(PoemType value) {
        this.poem = value;
    }

    /**
     * Gets the value of the subtitle property.
     * 
     * @return
     *     possible object is
     *     {@link PType }
     *     
     */
    public PType getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the value of the subtitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link PType }
     *     
     */
    public void setSubtitle(PType value) {
        this.subtitle = value;
    }

    /**
     * Gets the value of the cite property.
     * 
     * @return
     *     possible object is
     *     {@link CiteType }
     *     
     */
    public CiteType getCite() {
        return cite;
    }

    /**
     * Sets the value of the cite property.
     * 
     * @param value
     *     allowed object is
     *     {@link CiteType }
     *     
     */
    public void setCite(CiteType value) {
        this.cite = value;
    }

    /**
     * Gets the value of the emptyLine property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getEmptyLine() {
        return emptyLine;
    }

    /**
     * Sets the value of the emptyLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setEmptyLine(Object value) {
        this.emptyLine = value;
    }

    /**
     * Gets the value of the table property.
     * 
     * @return
     *     possible object is
     *     {@link TableType }
     *     
     */
    public TableType getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableType }
     *     
     */
    public void setTable(TableType value) {
        this.table = value;
    }

    /**
     * Gets the value of the pOrImageOrPoem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pOrImageOrPoem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPOrImageOrPoem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CiteType }{@code >}
     * {@link JAXBElement }{@code <}{@link PType }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     * {@link JAXBElement }{@code <}{@link TableType }{@code >}
     * {@link JAXBElement }{@code <}{@link PType }{@code >}
     * {@link JAXBElement }{@code <}{@link ImageType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getPOrImageOrPoem() {
        if (pOrImageOrPoem == null) {
            pOrImageOrPoem = new ArrayList<JAXBElement<?>>();
        }
        return this.pOrImageOrPoem;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

}
