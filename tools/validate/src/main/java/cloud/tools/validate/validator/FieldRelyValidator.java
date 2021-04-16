package cloud.tools.validate.validator;

import cloud.tools.validate.annotation.FieldRely;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jia Shi
 * @since 2021/2/3
 */
@Slf4j
public class FieldRelyValidator implements ConstraintValidator<FieldRely, Object> {

    private String relyField;
    private FieldRely.RelyType relyType;
    private String currentField;
    private String[] relyValues;

    @Override
    public void initialize(FieldRely fieldRely) {
        this.relyField = fieldRely.relyField();
        this.relyType = fieldRely.relyType();
        this.currentField = fieldRely.currentField();
        this.relyValues = fieldRely.relyValues();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean flag = true;

        try {

            String relyValue = this.getStringValue(object, this.relyField);
            String currentValue = this.getStringValue(object, this.currentField);

            switch (this.relyType) {
                case null2notnull:
                    if (StringUtils.isEmpty(relyValue)) {
                        flag = !StringUtils.isEmpty(currentValue);
                    }

                    break;

                case notnull2null:
                    if (!StringUtils.isEmpty(relyValue)) {
                        flag = true;
                    } else {
                        flag = !StringUtils.isEmpty(currentValue);
                    }

                    break;

                case notnull2notnull:
                    if (!StringUtils.isEmpty(relyValue)) {
                        flag = !StringUtils.isEmpty(currentValue);
                    }

                    break;

                case value2null:
                    if (this.relyValues == null || this.relyValues.length < 1) {
                        flag = false;
                        break;
                    }

                    if (relyValue.equals(this.relyValues[0])) {
                        flag = true;
                    } else {
                        flag = !StringUtils.isEmpty(currentValue);
                    }

                    break;

                case value2notnull:
                    if (this.relyValues == null || this.relyValues.length < 1) {
                        flag = false;
                        break;
                    }

                    if (relyValue.equals(this.relyValues[0])) {
                        flag = !StringUtils.isEmpty(currentValue);
                    } else {
                        flag = true;
                    }

                    break;

                case value2value:
                    if (this.relyValues == null || this.relyValues.length != 2) {
                        flag = false;
                        break;
                    }

                    if (relyValue.equals(this.relyValues[0])) {
                        flag = currentValue.equals(this.relyValues[1]);
                    }

                    break;

                case valueltvalue:
                    //空值不能比较
                    //					if(StringUtils.isEmpty(currentValue) || StringUtils.isEmpty(relyValue)) {
                    //						flag = false;
                    //						break;
                    //					}

                    flag = relyValue.compareTo(currentValue) < 0;

                    break;

                case valuelqvalue:
                    //空值不能比较
                    //					if(StringUtils.isEmpty(currentValue) || StringUtils.isEmpty(relyValue)) {
                    //						flag = false;
                    //						break;
                    //					}

                    flag = relyValue.compareTo(currentValue) <= 0;

                    break;

                case valuegtvalue:
                    //空值不能比较
                    //					if(StringUtils.isEmpty(currentValue) || StringUtils.isEmpty(relyValue)) {
                    //						flag = false;
                    //						break;
                    //					}

                    flag = relyValue.compareTo(currentValue) > 0;

                    break;

                case valuegqvalue:
                    //空值不能比较
                    //					if(StringUtils.isEmpty(currentValue) || StringUtils.isEmpty(relyValue)) {
                    //						flag = false;
                    //						break;
                    //					}

                    flag = relyValue.compareTo(currentValue) >= 0;

                    break;

                case valueeqvalue:
                    //空值不能比较
                    //					if(StringUtils.isEmpty(currentValue) || StringUtils.isEmpty(relyValue)) {
                    //						flag = false;
                    //						break;
                    //					}

                    flag = relyValue.compareTo(currentValue) == 0;

                    break;

                case value2regex:
                    if (StringUtils.isEmpty(relyValue)) {
                        break;
                    }

                    if (this.relyValues == null || this.relyValues.length != 2) {
                        flag = false;
                        break;
                    }

                    if (relyValue.equals(this.relyValues[0])) {
                        flag = isValid(currentValue, this.relyValues[1]);
                    }

                    break;

                case regex2value:
                    if (this.relyValues == null || this.relyValues.length != 2) {
                        flag = false;
                        break;
                    }

                    if (isValid(relyValue, this.relyValues[0])) {
                        flag = currentValue.equals(this.relyValues[1]);
                    }

                    break;

                case regex2regex:
                    if (this.relyValues == null || this.relyValues.length != 2) {
                        flag = false;
                        break;
                    }

                    if (isValid(relyValue, this.relyValues[0])) {
                        flag = isValid(currentValue, this.relyValues[1]);
                    }

                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            flag = false;
        }

        return flag;
    }

    /**
     * 获取值，并对日期进行特殊处理
     */
    private String getStringValue(Object object, String fieldName) throws Exception {
        String className = getFieldGenericType(object.getClass(), this.currentField);
        String value = BeanUtils.getProperty(object, fieldName);
        if ("class java.util.Date".equals(className)) {
            // 获取值，并对日期进行特殊处理
            value = DateUtil.format(DateUtil.parse(value, DatePattern.PURE_DATE_PATTERN),DatePattern.NORM_DATE_PATTERN);
        }
        return value;

    }

    public static boolean isValid(String value, String regex) {
        if (value == null || StringUtils.isEmpty(regex)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 根据字段名称获取该字段的类型
     */
    public static String getFieldGenericType(Class<?> clazz, String fieldName) {
        String genericType = "";
        Field[] fields;
        do {
            fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    genericType = field.getGenericType()
                            .toString();
                    break;
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);
        return genericType;
    }
}
