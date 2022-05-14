package test;

import FWID.CrtObject;
import FWID.Inject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class test {
    public static void main(String[] args) {

        try {
            JAXBContext jaxbContext=JAXBContext.newInstance(Inject.class);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
            Inject inject=(Inject) unmarshaller.unmarshal(new File("tst2.xml"));

            List<CrtObject> crtObjects=inject.getCrtObjects();
            for (CrtObject c:crtObjects) {
                System.out.println("id : "+c.getId());
                System.out.println("class : "+c.getaClass());
                System.out.println("by contructor arg   name= "+c.getByConstructorArg().getName() +"  | value ="+c.getByConstructorArg().getValue());
                System.out.println("by setter   name= "+c.getBySetter().getName() +"  | idRef ="+c.getBySetter().getIdRef());
                System.out.println("---------------------------------------------------");
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
