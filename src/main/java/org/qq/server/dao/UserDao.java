package org.qq.server.dao;

import org.qq.server.entity.UserEntity;

import java.sql.Timestamp;

/**
 * Created by dxx on 2017/11/7.
 * 用户注册、登录、注销等功能使用的数据库操作工具类
 */
public interface UserDao {
    int addUser(int userId, String passwd, Timestamp registerTime);

    int updateUserStatus(int userId, int status);

    int updateUserLastLoginTime(int userId, Timestamp loginTime);

    UserEntity queryUserByIdAndPasswd(int userId, String passwd);

    UserEntity queryUserById(int userId);
}
