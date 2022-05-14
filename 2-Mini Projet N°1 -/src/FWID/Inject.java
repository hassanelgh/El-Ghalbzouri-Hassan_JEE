package FWID;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "inject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Inject {

    @XmlElement(name = "crt_object")
    private List<CrtObject> crtObjects=new ArrayList<>();

    public List<CrtObject> getCrtObjects() {
        return crtObjects;
    }

    public void setCrtObjects(List<CrtObject> crtObjects) {
        this.crtObjects = crtObjects;
    }

    public void addCrtObject(CrtObject crtObject){
        crtObjects.add(crtObject);
    }
}
