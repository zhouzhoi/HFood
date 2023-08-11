package com.zhou.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhou
 * @since 2023-08-11
 */
public interface UserService extends IService<User> {

    IPage pageC(Page<User> page);
    //使用 Wrapper 自定义SQL
    IPage pageCC(IPage<User> page, Wrapper wrapper);

}
