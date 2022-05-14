package FWID.byXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class BySetter implements Serializable {

    @XmlAttribute(name = "names")
    private String names;

    @XmlAttribute(name = "idRefs")
    private String idRefs;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getIdRefs() {
        return idRefs;
    }

    public void setIdRefs(String idRefs) {
        this.idRefs = idRefs;
    }
}
