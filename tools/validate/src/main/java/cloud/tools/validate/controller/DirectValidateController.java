package cloud.tools.validate.controller;

import cloud.tools.validate.entity.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 直接参数校验
 * @author 石佳
 * @date 2021/1/28 20:42
 */
@Validated
@RestController
@RequestMapping("/validate/direct")
@Slf4j
public class DirectValidateController {
    /**
     * 直接参数校验
     * @author Jia Shi
     * @since 2021/1/28
     */
    @GetMapping
    @ResponseBody
    // 注意：如果想在参数中使用 @NotNull 这种注解校验，就必须在类上添加 @Validated；
    public UserDTO direct(@NotNull(message = "userId不能为空") Integer userId){
        log.info("userId:[{}]",userId);
        UserDTO res = new UserDTO();
        res.setUserId(userId);
        res.setName("程序员自由之路");
        res.setAge(8);
        return res;
    }
}
