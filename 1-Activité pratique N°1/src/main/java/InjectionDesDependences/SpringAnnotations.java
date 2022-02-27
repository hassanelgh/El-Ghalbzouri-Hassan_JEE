package InjectionDesDependences;

import Metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotations {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext("Dao","Metier");
        IMetier metier=(IMetier) applicationContext.getBean("metier");
        System.out.println("---->Spring Annotations");
        System.out.println(metier.calcule());
    }
}
