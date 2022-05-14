package FWID.byXML;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "crt_object")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrtObject implements Serializable {

    @XmlAttribute(name = "class")
    private String aClass;

    @XmlAttribute(name = "ids")
    private String ids;



    @XmlElement(name = "by_constructor_arg")
    private List<ByConstructorArg> byConstructorArg;

    @XmlElement(name = "by_setter")
    private List<BySetter> bySetter;

    @XmlElement(name = "by_field")
    private List<ByField> byFields;


    public List<ByField> getByFields() {
        return byFields;
    }

    public void setByFields(List<ByField> byFields) {
        this.byFields = byFields;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public List<ByConstructorArg> getByConstructorArg() {
        return byConstructorArg;
    }

    public void setByConstructorArg(List<ByConstructorArg> byConstructorArg) {
        this.byConstructorArg = byConstructorArg;
    }

    public List<BySetter> getBySetter() {
        return bySetter;
    }

    public void setBySetter(List<BySetter> bySetter) {
        this.bySetter = bySetter;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
