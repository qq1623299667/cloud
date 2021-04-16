package cloud.tools.validate.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 石佳
 * @date 2021/1/29 10:53
 */
@Data
public class UserDTO6 {
    private Integer userId;
    @NotEmpty
    private String name;
    @NotNull
    private Integer age;
    // 需要注意的是，在job类的校验上面一定要加上@Valid注解。
    @Valid
    @NotNull
    private Job job;

    /**
     * 这边必须设置成静态内部类
     */
    @Data
    static class Job {
        @NotEmpty
        private String jobType;
        @DecimalMax(value = "1000.99")
        private Double salary;

    }
}
