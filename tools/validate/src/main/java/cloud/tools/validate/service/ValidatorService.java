package cloud.tools.validate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 石佳
 * @date 2021/1/28 20:57
 */
@Validated
@Service
@Slf4j
public class ValidatorService {
    // 和controller层一样，指定@NotNull等注解进行校验，另外service类需要加入@Validated注解
    public Integer show(@NotNull(message = "不能为空") @Min(value = 18, message = "最小18") Integer age) {
        log.info("age = {}", age);
        return age;
    }
}
