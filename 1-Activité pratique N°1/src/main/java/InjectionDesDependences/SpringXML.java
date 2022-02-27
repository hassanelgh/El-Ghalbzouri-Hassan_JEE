package InjectionDesDependences;

import Metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXML {
    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");

        IMetier iMetier=(IMetier) context.getBean("metier");
        System.out.println("------->Spring XML");
        System.out.println(iMetier.calcule());
    }
}
