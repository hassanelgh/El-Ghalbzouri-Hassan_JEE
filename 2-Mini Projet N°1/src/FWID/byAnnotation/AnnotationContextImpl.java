package FWID.byAnnotation;

import java.io.File;
import java.util.*;

public class AnnotationContextImpl implements IAnnotationContext {

    private List<Class<?>> injectables=new ArrayList<>();

    @Override
    public void annotationConfigure(Class<?>... classTypes) {
        for (Class<?> classType : classTypes) {
            if (!injectables.contains(classType)) {
                injectables.add(classType);
            }
        }
    }

    @Override
    public void annotationConfigure(String... packageNames) throws FWIDAnnotationException {
        // for all packageNames
        for (String packageName : packageNames) {
            File file = new File("src/" + packageName.replace('.', '/') + "/");

            if (file.exists() && file.isDirectory()) {

                for (File f : file.listFiles()) {
                    // if is directory  execute annotationConfigure for this directory
                    // if not and end with '.java' create class
                    if(f.isDirectory())
                        annotationConfigure(packageName + "." +f.getName());
                    else if (f.getName().endsWith(".java")) {
                        try {
                            String className=packageName + "." + f.getName().substring(0, f.getName().length() - 5);
                            Class c=Class.forName(className);

                            // if c is not an interface add it to injectebales list
                            if(!c.isInterface())
                                if (!injectables.contains(c)) {
                                    injectables.add(c);
                                }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else {
                throw new FWIDAnnotationException("file '"+file.getPath()+" is not exist or not a directory");
            }
        }
    }

    @Override
    public <T> Class<? extends T> getClassInject(Class<T> type,String name) throws FWIDAnnotationException {
        // for case 1:  NameInject of class equal to the name
        Class<? extends T> injectableClasseWithAnnotationName=null;
        // for case 2: name of class equal to the name
        Class<? extends T> injectableClasseWithNameVariable=null;
        // for case 1:  class type
        Class<? extends T> injectableClasseWithOut=null;


        for (Class<?> injectable : injectables) {
            if (Arrays.asList(injectable.getInterfaces()).contains(type)) {
                // case 1
                if(     injectableClasseWithAnnotationName==null &&
                        injectable.isAnnotationPresent(NameInject.class) &&
                        injectable.getAnnotation(NameInject.class).value().equals(name)
                ){
                    injectableClasseWithAnnotationName=injectable.asSubclass(type);
                    break;
                }

                // case 2
                String fullClassName=injectable.getName();
                String nameClass=fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
                nameClass=nameClass.substring(0,1).toLowerCase(Locale.ROOT).concat(nameClass.substring(1,nameClass.length()));

                if(   injectableClasseWithNameVariable==null &&
                        !injectable.isAnnotationPresent(NameInject.class) &&
                        nameClass.equals(name))
                    injectableClasseWithNameVariable=injectable.asSubclass(type);


                // case 3
                if(      injectableClasseWithOut==null &&
                        !injectable.isAnnotationPresent(NameInject.class))
                        injectableClasseWithOut=injectable.asSubclass(type);





            }
        }
        /*
        *  Sort by important
        *       1-injectableClasseWithAnnotationNam
        *       2-injectableClasseWithNameVariable
        *       3-injectableClasseWithOut
        * */
        if(injectableClasseWithAnnotationName!=null)
            return injectableClasseWithAnnotationName;
        else if(injectableClasseWithNameVariable!=null)
            return injectableClasseWithNameVariable;
        else if (injectableClasseWithOut!=null)
            return injectableClasseWithOut;
        else
            throw new FWIDAnnotationException(" no injectebel register type "+ type+" and name ="+name);


    }
}
