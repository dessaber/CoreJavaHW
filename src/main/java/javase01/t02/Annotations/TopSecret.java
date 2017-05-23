package javase01.t02.Annotations;

import java.lang.annotation.*;

/**
 * Created by m-levin on 08.05.2017.
 * Inherited - if some document is secret, its subdocuments gotta be secret as well.
 * Documented - to add its contents in javadoc
 * This annotation is used in InnerClasses.NuclearSubmarine.
 */

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TopSecret {
    String name();
    String classificationDate();
    SecrecyLevel secrecy();

    enum SecrecyLevel { ULTRA, HIGH, MIDDLE, LOW }
}

