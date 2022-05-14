package FWID.byXML;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class ByField implements Serializable {

    @XmlAttribute(name = "names")
    private String names;

    @XmlAttribute(name = "values")
    private String values;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
