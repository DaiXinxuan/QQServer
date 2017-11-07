package org.qq.server.service.Impl;

import org.qq.server.dao.UserDao;
import org.qq.server.entity.UserEntity;
import org.qq.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by dxx on 2017/11/7.
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public int userRegister(int userId, String passwd, Timestamp registerTime) {
        return userDao.addUser(userId, passwd, registerTime);
    }

    public UserEntity userLogin(int userId, String passwd, Timestamp loginTime) {
        UserEntity userEntity = userDao.queryUserByIdAndPasswd(userId, passwd);
        //如果用户信息确实存在更新用户在线状态和上次上线时间
        if (userEntity != null && !userEntity.getOnline()) {
            userDao.updateUserStatus(userId, 1);
            userDao.updateUserLastLoginTime(userId, loginTime);
        } else if (userEntity != null) {
            throw new RuntimeException("该用户已经在线，请勿重复登录");
        }
        return userEntity;
    }

    public int userLogoff(int userId) {
        return userDao.updateUserStatus(userId, 0);
    }

    public UserEntity checkRepeatUserId(int userId) {
        return userDao.queryUserById(userId);
    }
}
