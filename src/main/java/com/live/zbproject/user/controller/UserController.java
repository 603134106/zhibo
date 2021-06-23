package com.live.zbproject.user.controller;

import com.live.zbproject.user.entity.User;
import com.live.zbproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 庄科炜
 * @className UserController
 * @description 用户控制层
 * @create 2021/6/23 16:18
 **/
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("findAll")
    public List<User> findAll(){
        JSONObject json = new JSONObject();
        return userService.findAll();
    }

}
