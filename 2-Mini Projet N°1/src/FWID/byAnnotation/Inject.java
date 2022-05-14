package FWID.byAnnotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD}) //type for element to write thise annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

    InjectType value() default InjectType.NEW;
    String id() default "";
    String name() default "";
}
