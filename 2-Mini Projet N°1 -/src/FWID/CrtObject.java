package FWID;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "crt_object")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrtObject {

    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "class")
    private String aClass;

    @XmlElement(name = "by_constructor_arg")
    private ByConstructorArg byConstructorArg;

    @XmlElement(name = "by_setter")
    private BySetter bySetter;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public ByConstructorArg getByConstructorArg() {
        return byConstructorArg;
    }

    public void setByConstructorArg(ByConstructorArg byConstructorArg) {
        this.byConstructorArg = byConstructorArg;
    }

    public BySetter getBySetter() {
        return bySetter;
    }

    public void setBySetter(BySetter bySetter) {
        this.bySetter = bySetter;
    }
}
