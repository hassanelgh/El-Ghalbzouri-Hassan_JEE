package test.TestAnnotation;

import FWID.byAnnotation.AnnotationContextImpl;
import FWID.byAnnotation.FWIDAnnotationException;
import FWID.byAnnotation.Injector;
import test.TestAnnotation.Dao.DaoImpl;
import test.TestAnnotation.Dao.DaoImpl2;
import test.TestAnnotation.DaoV2.DaoV2Impl;
import test.TestAnnotation.Metier.*;

import java.lang.reflect.InvocationTargetException;


public class mainTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, FWIDAnnotationException {

        AnnotationContextImpl annotationContext=new AnnotationContextImpl();
        // for packages :
            // annotationContext.annotationConfigure("test.TestAnnotation.Dao","test.TestAnnotation.Metier","test.TestAnnotation.DaoV2");
        // for  classs  :
        annotationContext.annotationConfigure(
                DaoImpl.class,DaoV2Impl.class, DaoImpl2.class
        );
        Injector injector=new Injector(annotationContext);

        IMetier metierImplConstructor=(MetierImplConstructor) injector.getBean(MetierImplConstructor.class);
        IMetier metierImplField=(MetierImplField) injector.getBean(MetierImplField.class);
        IMetier metierImplSetter=(MetierImplSetter) injector.getBean(MetierImplSetter.class);
        IMetier metierImplSetter1=(MetierImplSetter) injector.getBean(MetierImplSetter.class);

        System.out.println("___________________________________________________________________________");
        System.out.println("___________________________________________________________________________");
        System.out.println("-------------------- by constructor ---------------------------------------");
        System.out.println(metierImplConstructor.calcule());
        System.out.println("___________________________________________________________________________");
        System.out.println("-------------------- by field ---------------------------------------------");
        System.out.println(metierImplField.calcule());
        System.out.println("___________________________________________________________________________");
        System.out.println("-------------------- by setter 1 ------------------------------------------");
        System.out.println(metierImplSetter.calcule());
        System.out.println("-------------------- by setter 2 ------------------------------------------");
        System.out.println(metierImplSetter1.calcule());


    }



}
