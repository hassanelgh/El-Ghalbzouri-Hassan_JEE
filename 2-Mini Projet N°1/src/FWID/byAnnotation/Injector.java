package FWID.byAnnotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Injector {
    private IAnnotationContext annotationContext;
    private Map<String,Object> saveObjects=new HashMap<>();

    public Injector(IAnnotationContext annotationContext) {
        this.annotationContext = annotationContext;
    }


    public <T> T getBean(Class<T> cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, FWIDAnnotationException {
       /*
            - find if there are annotations of type Inject.class
            - sperifier if it is for a constructor or setter or field , and after the detection we execute one of the functions:
                         injectByConstructor,
                         injectBySetter,
                         injectByField,
        */

        for (Constructor<?> constructor : cl.getConstructors()) {
            if(constructor.isAnnotationPresent(Inject.class)){
                return injectByConstructor(constructor,cl);
            }
        }

        for (Method method : cl.getMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                return injectBySetter(cl);
            }
        }
        return injectByField(cl);
    }

    private <T> T injectBySetter(Class<T> cl) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, FWIDAnnotationException {

        T t = cl.getConstructor().newInstance();

        // all methods of class cl
        for (Method method : cl.getMethods()) {

            // if method is a setter and has annotation Inject
            if (method.isAnnotationPresent(Inject.class) && method.getName().startsWith("set") && method.getParameterCount()==1) {
                String name=method.getAnnotation(Inject.class).name();

                // if name (in inject annotation) is "" we give name of method without 'set' and make the first letter lower case
                if(name.equals("")){
                     name=method.getName().replace("set","");
                    name=name.substring(0,1).toLowerCase(Locale.ROOT).concat(name.substring(1,name.length()));
                }

                Object o=null;
                // case InjectType is SINGLE_ONE
                if(method.getAnnotation(Inject.class).value()==InjectType.SINGLE_ONE)
                {
                    // if id exist
                    if(!method.getAnnotation(Inject.class).id().equals("")){
                        String id=method.getAnnotation(Inject.class).id();
                        if(saveObjects.containsKey(id)){
                            // get object from saveObjects
                            o=saveObjects.get(id);
                        }
                        else {
                            // get bean  and put it in saveObjects
                            o=getBean(annotationContext.getClassInject(method.getParameterTypes()[0],name));
                            saveObjects.put(id,o);
                        }
                    }else {
                        throw new FWIDAnnotationException("method '"+method.getName()+"' of class '"+cl.getName()+"' has InjectType equal to SINGLE_ONE but he dont have id");
                    }
                }

                if (o==null)
                    o=getBean(annotationContext.getClassInject(method.getParameterTypes()[0],name));

                method.invoke(t, o);
            }
        }
        return t;
    }

    private <T> T injectByConstructor(Constructor<?> constructor, Class<T> cl) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, FWIDAnnotationException {
         /*
            - get all parameters types of constructor
            - create list of objects for save objects
            - get bean for all parameters
         */
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] depend=new Object[parameterTypes.length];

        for (int i1 = 0; i1 < parameterTypes.length; i1++) {
            if(constructor.getAnnotation(Inject.class).value()!=InjectType.SINGLE_ONE ||
                    !constructor.getAnnotation(Inject.class).name().equals("") ||
                    !constructor.getAnnotation(Inject.class).id().equals("")
            ){
                depend[i1]=getBean(annotationContext.getClassInject(parameterTypes[i1],""));
            }else
                System.out.println("[info]>>:annotation Inject for constructor '"+constructor.getName()+"' of class '"+cl.getName()+"' not need to add name or id or value ");

        }

        return cl.getConstructor(parameterTypes).newInstance(depend);
    }

    private <T> T injectByField(Class<T> cl) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, FWIDAnnotationException {
        T instance = cl.getConstructor().newInstance();

        // for all field declaration of class cl
        for (Field declaredField : cl.getDeclaredFields()) {

            // if he has annotation Inject
            if(declaredField.isAnnotationPresent(Inject.class)){
                declaredField.setAccessible(true);
                String name=declaredField.getAnnotation(Inject.class).name();

                //if name equal to "" we give name of the field
                if(name.equals(""))
                    name=declaredField.getName();

                Object o=null;

                // case InjectType is SINGLE_ONE
                if(declaredField.getAnnotation(Inject.class).value()==InjectType.SINGLE_ONE)
                {
                    // if id exist
                    if(!declaredField.getAnnotation(Inject.class).id().equals("")){
                        String id=declaredField.getAnnotation(Inject.class).id();
                        if(saveObjects.containsKey(id)){
                            // get object from saveObjects
                            o=saveObjects.get(id);
                        }
                        else {
                            // get bean and put it in saveObjects
                            o=getBean(annotationContext.getClassInject(declaredField.getType(),name));
                            saveObjects.put(id,o);
                        }
                    }else {
                        throw new FWIDAnnotationException("method '"+declaredField.getName()+"' of class '"+cl.getName()+"' has InjectType equal to SINGLE_ONE but he dont have id");
                    }
                }
                if (o==null)
                    o=getBean(annotationContext.getClassInject(declaredField.getType(),name));
                declaredField.set(instance,o);
            }
        }
        return instance;
    }
}
