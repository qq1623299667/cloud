package cloud.tools.validate.validator;

import cloud.tools.validate.annotation.FieldEnum;
import cn.hutool.core.date.DateUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Jia Shi
 * @since 2021/1/28
 */
public class FieldEnumValidator implements ConstraintValidator<FieldEnum, Object> {

    private String[] enums;
    private boolean canbeNull;

    @Override
    public void initialize(FieldEnum fieldEnum) {
        this.enums = fieldEnum.value();
        this.canbeNull = fieldEnum.canbeNull();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean flag = true;
        if (this.enums == null || this.enums.length == 0) {
            return true;
        }
        if (value == null) {
            return true;
        }
        String val;
        String enumstr = Arrays.toString(this.enums);
        if ("class java.util.Date".equals(value.getClass()
            .getName())) {
            val = DateUtil.format((Date) value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            val = value.toString();
        }
        if (StringUtils.isEmpty(val)) {
            flag = this.canbeNull;
        } else {
            if (!enumstr.contains("[" + val) && !enumstr.contains(", " + val + ",") && !enumstr.contains(val + "]")) {
                flag = false;
            }
        }
        return flag;
    }

}
