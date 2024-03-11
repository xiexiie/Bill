package com.xieyunjie.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xieyunjie.sys.domain.User;
import com.xieyunjie.sys.mapper.UserMapper;
import com.xieyunjie.sys.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
