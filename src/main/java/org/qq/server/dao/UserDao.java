package org.qq.server.dao;

import org.qq.server.entity.UserEntity;

import java.sql.Timestamp;

/**
 * Created by dxx on 2017/11/7.
 */
public interface UserDao {
    int addUser(int userId, String passwd, Timestamp registerTime);

    int updateUserStatus(int userId, int status);

    int updateUserLastLoginTime(int userId, Timestamp loginTime);

    UserEntity queryUserByIdAndPasswd(int userId, String passwd);

    UserEntity queryUserById(int userId);
}
