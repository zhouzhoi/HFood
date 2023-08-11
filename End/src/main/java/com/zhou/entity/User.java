package com.zhou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    private Integer id;

    private String no;

    private String name;

    private String password;

    private Integer age;

    private Integer sex;

    private String phone;

    private Integer roleId;
    @TableField("isValid")
    private String isvalid;

}
