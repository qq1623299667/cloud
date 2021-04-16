package cloud.tools.validate.controller;

import cloud.tools.validate.entity.UserDTO;
import cloud.tools.validate.entity.UserDTO2;
import cloud.tools.validate.service.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 对Service层方法参数校验
 * @author 石佳
 * @date 2021/1/28 20:48
 */
@RestController
@RequestMapping("/validate/service")
@Slf4j
public class ServiceValidateController {
    @Autowired
    private ValidatorService validatorService;
    /**
     * 对Service层方法参数校验
     * @author Jia Shi
     * @since 2021/1/28
     */
    @PostMapping
    @ResponseBody
    public UserDTO getUser(@RequestBody UserDTO userDTO){
        validatorService.show(userDTO.getAge());
        return userDTO;
    }
}
