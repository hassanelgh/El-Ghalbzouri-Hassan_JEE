package FWID;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class BySetter {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "idRef")
    private String idRef;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdRef() {
        return idRef;
    }

    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }
}
