package com.zhou.service.impl;

import com.zhou.entity.User;
import com.zhou.mapper.UserMapper;
import com.zhou.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhou
 * @since 2023-08-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
