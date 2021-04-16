package cloud.tools.validate.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 石佳
 * @date 2021/1/28 20:40
 */
@Data
public class UserDTO2 {
    private Integer userId;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotNull
    @Range(min = 18,max = 50,message = "年龄必须在18和50之间")
    private Integer age;
}
