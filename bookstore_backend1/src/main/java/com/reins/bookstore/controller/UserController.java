package com.reins.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.reins.bookstore.entity.UserAuth;
import com.reins.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description the controller of user
 * @Author thunderBoy
 * @Date 2019/11/7 13:47
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/checkUser")
    public UserAuth checkUser(@RequestBody JSONObject jsonObject){
        String username =  jsonObject.getString("username");
        String password =  jsonObject.getString("password");
        System.out.println("username"+username+"password"+password);
        return userService.checkUser(username, password);
    }



}
