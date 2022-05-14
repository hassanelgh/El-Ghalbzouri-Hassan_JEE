package FWID.byAnnotation;

import java.util.List;

public interface IAnnotationContext {
    void annotationConfigure(Class<?>... classTypes);
    void annotationConfigure(String... packageNames) throws FWIDAnnotationException;
    <T> Class<? extends T> getClassInject(Class<T> type,String name) throws FWIDAnnotationException;
}
