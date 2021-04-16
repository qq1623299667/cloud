package cloud.tools.validate.validator;

import cloud.tools.validate.annotation.EncryptId;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 石佳
 * @date 2021/1/29 11:09
 */
public class EncryptIdValidator implements ConstraintValidator<EncryptId, String> {
    private static final Pattern PATTERN = Pattern.compile("^[a-f\\d]{32,256}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(value)) {
            Matcher matcher = PATTERN.matcher(value);
            return matcher.find();
        }
        return false;
    }
}
