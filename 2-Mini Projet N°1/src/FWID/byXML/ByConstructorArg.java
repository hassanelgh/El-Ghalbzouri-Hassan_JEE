package FWID.byXML;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "by_constructor_arg")
@XmlAccessorType(XmlAccessType.FIELD)
public class ByConstructorArg implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "values")
    private String values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String value) {
        this.values = value;
    }
}
