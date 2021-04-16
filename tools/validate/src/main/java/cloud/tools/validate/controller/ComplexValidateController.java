package cloud.tools.validate.controller;

import cloud.tools.validate.entity.First;
import cloud.tools.validate.entity.Second;
import cloud.tools.validate.entity.UserDTO7;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 复杂的校验
 * @author 石佳
 * @date 2021/2/3 19:09
 */
@Slf4j
@RestController
@RequestMapping("/validate/complex")
public class ComplexValidateController {
    /**
     * 条件校验,达到某个条件才进行校验
     * 场景：如果用户通过的人脸识别，就校验证件信息不为空，否则证件信息不校验
     * @author Jia Shi
     * @since 2021/2/3
     */
    @PostMapping("/conditionValidate")
    public UserDTO7 conditionValidate(@RequestBody @Validated(value = {First.class, Second.class}) UserDTO7 userDTO7){
        return userDTO7;
    }
}
