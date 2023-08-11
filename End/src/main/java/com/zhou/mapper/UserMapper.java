package com.zhou.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhou
 * @since 2023-08-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage pageC(Page<User> page);

    IPage pageCC(IPage<User> page, Wrapper wrapper);
}
