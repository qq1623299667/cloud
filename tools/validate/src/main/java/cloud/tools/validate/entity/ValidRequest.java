package cloud.tools.validate.entity;

import cloud.tools.validate.annotation.FieldEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 石佳
 * @date 2021/1/28 16:24
 */
@Data
public class ValidRequest {
    @NotEmpty
    @FieldEnum(value = {"张三","李四"}, message = "用户名不在系统中")
    private String name;

    private String account;

    private String password;
}
