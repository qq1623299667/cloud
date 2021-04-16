package cloud.tools.validate.controller;

import cloud.tools.validate.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 分组校验
 * @author 石佳
 * @date 2021/1/29 9:47
 */
@RestController
@RequestMapping("/validate/group")
@Slf4j
public class GroupValidateController {
    @PostMapping("/saveUserGroup")
    @ResponseBody
//注意：如果方法中的参数是对象类型，则必须要在参数对象前面添加 @Validated
//进行分组校验，年龄满足大于17。由于name用了default分组，将不会对name进行校验
    public UserDTO3 saveUserGroup(@Validated(value = {UserDTO3.Group1.class}) @RequestBody UserDTO3 userDTO3){
        userDTO3.setUserId(100);
        return userDTO3;
    }

    // 先计算属于 First 组的约束，再计算属于 Second 组的约束
    @PostMapping("/testGroupOrder")
    @ResponseBody
    public UserDTO4 testGroupOrder(@Validated(value = {UserDTO4.GroupOrderedOne.class}) @RequestBody UserDTO4 userDTO4){
        userDTO4.setUserId(100);
        return userDTO4;
    }

    // 基于上一个方法的复杂变种
    @PostMapping("/testGroupOrder2")
    @ResponseBody
    public UserDTO5 testGroupOrder2(@Validated(value = { Second.class,First.class}) @RequestBody UserDTO5 userDTO5){
        userDTO5.setUserId(100);
        return userDTO5;
    }

    //基于上一个方法的复杂变种
    @PostMapping("/testGroupOrder3")
    @ResponseBody
    public UserDTO5 testGroupOrder3(@Validated(value = { UserDTO5.Default.class}) @RequestBody UserDTO5 userDTO5){
        userDTO5.setUserId(100);
        return userDTO5;
    }
}
