package cloud.tools.validate.entity;

import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 石佳
 * @date 2021/1/28 18:09
 */
@Data
public class UserDTO5 {
    @NotBlank(message = "至少包含一个非空字符", groups = {First.class})
    @Size(min = 2, max = 3, message = "长度必须是2-3之间", groups = {Third.class})
    private String name;
    private Integer userId;
    @NotNull(message = "年龄不能为空",groups = {First.class})
    private Integer age;
    //注意：@Validated 注解中加上groups属性后,DTO中没有加group属性的校验规则将失效
    @NotBlank(message = "性别不能为空",groups = {UserDTO5.class})
    private String sex;

    @NotBlank(message = "手机号不能为空",groups = Second.class)
    // 没有带group的，就是没有，而不是UserDTO5.class
    @Pattern(regexp = "^[150[0-9]+]{11}", message = "手机号格式不正确")
    private String mobile;

    @GroupSequence(value = {First.class,Second.class,Third.class,UserDTO5.class})
    public interface Default{

    }
}
