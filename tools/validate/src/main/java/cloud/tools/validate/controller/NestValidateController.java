package cloud.tools.validate.controller;

import cloud.tools.validate.entity.UserDTO6;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 嵌套校验
 * validated 提示框架进行校验，但是不提供嵌套校验功能
 * valid 配合框架校验进行嵌套校验
 * @author 石佳
 * @date 2021/1/29 10:55
 */
@RestController
@RequestMapping("/validate/nest")
@Slf4j
public class NestValidateController {
    @PostMapping("/saveUserWithJob")
    @ResponseBody
    public UserDTO6 saveUserWithJob(@Validated @RequestBody UserDTO6 userDTO6){
        userDTO6.setUserId(100);
        return userDTO6;
    }
}
