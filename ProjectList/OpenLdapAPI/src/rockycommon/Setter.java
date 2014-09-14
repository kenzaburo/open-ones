package rockycommon;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Setter {
    AccessLevel values() default AccessLevel.PUBLIC;
    
    AnyAnnotation[] onMethod() default {};

    /**
     * Any annotations listed here are put on the generated method's parameter. The syntax for this feature is: {@code @Setter(onParam=@__({@AnnotationsGoHere}))}
     */
    AnyAnnotation[] onParam() default {};

    /**
      * Placeholder annotation to enable the placement of annotations on the generated code.
      * @deprecated Don't use this annotation, ever - Read the documentation.
      */
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    @Target({})
    @interface AnyAnnotation {}
}
