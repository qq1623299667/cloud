package cloud.tools.validate.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * @author 石佳
 * @date 2021/1/29 9:47
 */
@Data
public class UserDTO3 {
    public interface Default {
    }

    public interface Group1 {
    }

    private Integer userId;
    //注意：@Validated 注解中加上groups属性后,DTO中没有加group属性的校验规则将失效
    @NotEmpty(message = "姓名不能为空",groups = Default.class)
    private String name;

    //注意：加了groups属性之后，必须在@Validated 注解中也加上groups属性后，校验规则才能生效，不然下面的校验限制就失效了
    @Range(min = 18, max = 50, message = "年龄必须在18和50之间",groups = Default.class)
    @Range(min = 17, message = "年龄必须大于17", groups = Group1.class)
    private Integer age;
}
