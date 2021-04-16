package cloud.tools.validate.controller;

import cloud.tools.validate.entity.UserDTO5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 编程式校验
 * @author 石佳
 * @date 2021/1/29 11:24
 */
@Slf4j
@RestController
@RequestMapping("/validate/code")
public class CodeValidateController {
    @Autowired
    private Validator globalValidator;

    // 编程式校验，可以将指定组的所有的异常全部打印出来，但也包含了分组阻断校验
    @PostMapping("/saveWithCodingValidate")
    public UserDTO5 saveWithCodingValidate(@RequestBody UserDTO5 userDTO5) {
        Set<ConstraintViolation<UserDTO5>> validate = globalValidator.validate(userDTO5, UserDTO5.Default.class);
        // 如果校验通过，validate为空；否则，validate包含未校验通过项
        if (validate.isEmpty()) {
            // 校验通过，才会执行业务逻辑处理

        } else {
            for (ConstraintViolation userDTOConstraintViolation : validate) {
                // 校验失败，做其它逻辑
                System.out.println(userDTOConstraintViolation);
            }
        }
        return userDTO5;
    }
}
