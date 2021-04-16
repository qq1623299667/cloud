package cloud.tools.validate.annotation;

import cloud.tools.validate.validator.EncryptIdValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 石佳
 * @date 2021/1/29 11:09
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EncryptIdValidator.class})
public @interface EncryptId {
    // 默认错误消息
    String message() default "加密id格式错误";

    // 分组
    Class[] groups() default {};

    // 负载
    Class[] payload() default {};
}
