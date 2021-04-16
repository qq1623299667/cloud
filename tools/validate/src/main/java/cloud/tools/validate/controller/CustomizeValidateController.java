package cloud.tools.validate.controller;

import cloud.tools.validate.annotation.EncryptId;
import cloud.tools.validate.entity.ValidRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 自定义校验器
 * @author 石佳
 * @date 2021/1/28 16:23
 */
@Slf4j
@RestController
@RequestMapping("/validate/customize")
@Validated
public class CustomizeValidateController {
    /**
     * 场景：满足自定义条件才能通过
     * 需传入32位-256位a-f字母或者数字，否则不通过校验
     * @author Jia Shi
     * @since 2021/2/3
     */
    @PostMapping("/encrypt")
    @ResponseBody
    public String encrypt( @EncryptId String text){
        return text;
    }
    /**
     * name取张三，李四正常，否则提示用户名不在系统中
     * @author Jia Shi
     * @since 2021/1/28
     */
    @PostMapping("/login")
    public Object test(@RequestBody @Valid ValidRequest request) {
        return request.toString();
    }
}
