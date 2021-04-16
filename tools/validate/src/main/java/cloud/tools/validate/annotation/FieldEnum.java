package cloud.tools.validate.annotation;


import cloud.tools.validate.validator.FieldEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 字段枚举值
 * @author Jia Shi
 * @since 2021/1/28
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {FieldEnumValidator.class})
@Repeatable(FieldEnum.FieldEnums.class)
@Inherited
public @interface FieldEnum {

    String[] value();

    String message();

    boolean canbeNull() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Retention(RUNTIME)
    @Target(FIELD)
    @Inherited
    @Documented
    @interface FieldEnums {

        FieldEnum[] value();

    }

}
