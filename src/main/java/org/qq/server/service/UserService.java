package org.qq.server.service;

import org.qq.server.entity.UserEntity;

import java.sql.Timestamp;

/**
 * Created by dxx on 2017/11/7.
 * 用户登录，注册，注销操作的服务接口
 */
public interface UserService {
    //用户注册
    int userRegister(int userId, String passwd, Timestamp registerTime);
    //用户登录
    UserEntity userLogin(int userId, String passwd, Timestamp loginTime);
    //用户注销
    int userLogoff(int userId);
    //检查userId是否已被注册
    UserEntity checkRepeatUserId(int userId);
}
