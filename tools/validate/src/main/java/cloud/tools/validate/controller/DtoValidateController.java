package cloud.tools.validate.controller;

import cloud.tools.validate.entity.UserDTO2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 实体类DTO校验
 * @author 石佳
 * @date 2021/1/28 20:48
 */
@RestController
@RequestMapping("/validate/dto")
@Slf4j
public class DtoValidateController {
    /**
     * 实体类DTO校验
     * @author Jia Shi
     * @since 2021/1/28
     */
    @PostMapping
    @ResponseBody
    //注意：如果方法中的参数是对象类型，则必须要在参数对象前面添加 @Validated
    public UserDTO2 getUser(@Validated @RequestBody UserDTO2 userDTO2){
        userDTO2.setUserId(100);
        return userDTO2;
    }
}
