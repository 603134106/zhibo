package com.live.zbproject.user.service;

import com.live.zbproject.user.dao.UserMapper;
import com.live.zbproject.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 庄科炜
 * @className UserServiceImpl
 * @description user实现类
 * @create 2021/6/23 16:19
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired private UserMapper userMapper;
    /**
     * 查询所有用户信息
     * @author 庄科炜
     * @date 2021/6/23 16:26
     * @return java.util.List<com.live.zbproject.user.entity.User>
     */
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
