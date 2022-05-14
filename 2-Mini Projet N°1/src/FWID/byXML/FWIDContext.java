package FWID.byXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class FWIDContext implements Serializable {

    private Map<String,Object> allObjects;
    private Inject inject;

    public FWIDContext(String path){

        allObjects=new HashMap<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Inject.class);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
            inject=(Inject) unmarshaller.unmarshal(new File(path));
            creatAllObjects();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void creatAllObjects() {

        List<CrtObject> crtObjects=inject.getCrtObjects();

        for (CrtObject c:crtObjects) {
            initializeObject(c);
        }

    }

    private void initializeObject(CrtObject crtObject){
        String ids = crtObject.getIds();
        String classObject = crtObject.getaClass();

        try {

            Class<?> className= Class.forName(classObject);
            if(ids==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'ids' not exist ");
            if(className==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'class' not exist ");

                String[] listId=ids.split(" ");

                for (String  id:listId) {

                    Object object;

                    if(crtObject.getByConstructorArg()!=null)
                        object=injectByConstructor(crtObject,className);
                    else
                        object=className.newInstance();

                    if(crtObject.getByFields()!=null)
                        injectByField(crtObject,className,object);

                    if(crtObject.getBySetter()!=null)
                        injectBySetter(crtObject,className,object);


                    allObjects.put(id,object);
                }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        } catch (FWIDXmlException e) {
            e.printStackTrace();
        }

    }

    private void injectBySetter(CrtObject crtObject, Class<?> className, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, FWIDXmlException {
        for (BySetter b: crtObject.getBySetter()) {
            if(b.getNames()==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'names' not exist ");
            if(b.getIdRefs()==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'idRefs' not exist ");

            String names[]=b.getNames().split(" ");
            String idRefs[]=b.getIdRefs().split(" ");

            if(names.length!=idRefs.length)
                throw new FWIDXmlException("[ERROR:]>> you don't have the same length of 'names' and 'idrefs'");

            for (int i = 0; i <names.length ; i++) {
                    String nameSetter="set" +names[i].substring(0,1).toUpperCase()+names[i].substring(1);
                    Object o=allObjects.get(idRefs[i]);
                    Method methodSet=className.getMethod(nameSetter,o.getClass().getInterfaces()[0]);
                    methodSet.invoke(object,o);
            }
        }
    }

    private void injectByField(CrtObject crtObject, Class<?> className, Object object) throws NoSuchFieldException, IllegalAccessException, FWIDXmlException {
        for (ByField byField : crtObject.getByFields()) {
            if(byField.getNames()==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'names' not exist ");
            if(byField.getValues()==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'values' not exist ");

            String names[]=byField.getNames().split(" ");
            String values[]=byField.getValues().split(" ");

            if(names.length!=values.length)
                throw new FWIDXmlException("[ERROR:]>> you don't have the same length of 'names' and 'values'");

            for (int i = 0; i <names.length ; i++) {
                Field declaredField = className.getDeclaredField(names[i]);
                declaredField.setAccessible(true);
                declaredField.set(object,allObjects.get(values[i]));
            }

        }
    }

    public Object injectByConstructor(CrtObject crtObject,Class<?> className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, FWIDXmlException {
        List<Object> paramList=new ArrayList<>();
        int numbreParam=0;
        for (ByConstructorArg bc: crtObject.getByConstructorArg()) {
            String values=bc.getValues();
            if(values==null)
                throw new FWIDXmlException("[ERROR:]>> attribute 'values' not exist ");

            String[] listValue= values.split(" ");
            for (String v:listValue) {
                numbreParam++;
                paramList.add(allObjects.get(v));
            }
        }

        Class<?>[] parameterTypes = null;
        for (Constructor<?> constructor : className.getConstructors()) {
            if(constructor.getParameterCount()==numbreParam){
                if(parameterTypes==null){
                    parameterTypes = constructor.getParameterTypes();
                }
                boolean compar=true;
                Class<?>[] parameterTypesConstructor = constructor.getParameterTypes();
                for (int i = 0; i < parameterTypesConstructor.length; i++) {
                    Class<?> c= parameterTypesConstructor[i];
                    if(!c.isInstance(paramList.get(i))){
                        compar=false;
                        break;
                    }
                }
                if(compar){
                    parameterTypes = constructor.getParameterTypes();
                    break;
                }

            }
        }

        if(parameterTypes==null)
            throw new FWIDXmlException("[ERROR:]>> constructor has "+numbreParam+" parameter does not exist");

        return className.getDeclaredConstructor(parameterTypes).newInstance(paramList.toArray(new Object[0]));

    }


    public <T> T getInstance(String id){
        return (T) allObjects.get(id);
    }


}
