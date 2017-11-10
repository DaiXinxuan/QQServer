package org.qq.server.controller;

import org.qq.server.entity.FriendshipEntity;
import org.qq.server.entity.UserEntity;
import org.qq.server.request.UserInfo;
import org.qq.server.result.ResultJson;
import org.qq.server.result.UserStatus;
import org.qq.server.service.FriendshipService;
import org.qq.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dxx on 2017/11/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendshipService friendshipService;

    /**
     * 用户注册接口
     * @param request
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson userRegister(HttpServletRequest request, @RequestBody
            UserInfo userInfo) {
        ResultJson resultJson = new ResultJson(false);

        int userId = userInfo.getUserId();
        String passwd = userInfo.getPasswd();

        //检查用户id是否已被注册
        if(userService.checkRepeatUserId(userId) != null) {
            resultJson.setErrorCode(-1);
            resultJson.setErrorMsg("该id已被注册");
            return resultJson;
        }
        Timestamp now = new Timestamp(new Date().getTime());
        if(userService.userRegister(userId, passwd, now) != 1) {
            resultJson.setErrorCode(0);
            resultJson.setErrorMsg("数据库操作出错!请稍后再试");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson userLogin(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);

        int userId = userInfo.getUserId();
        String passwd = userInfo.getPasswd();

        Timestamp now = new Timestamp(new Date().getTime());
        UserEntity userEntity;
        try {
            userEntity = userService.userLogin(userId, passwd, now);
        } catch (Exception e) {
            //对用户已是在线状态下登录进行处理
            resultJson.setErrorCode(-1);
            resultJson.setErrorMsg(e.getMessage());
            return resultJson;
        }
        if (userEntity == null) {
            resultJson.setErrorCode(-2);
            resultJson.setErrorMsg("账号或密码有误，请查证");
            return resultJson;
        }
        request.getSession().setAttribute("userId", userId+"");
        resultJson.setStatus(true);
        resultJson.setResultObj(userId);
        return resultJson;
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson userLogOff(HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        String userIdStr = (String) request.getSession().getAttribute("userId");
        int userId = Integer.parseInt(userIdStr);

        int result = userService.userLogoff(userId);
        if(result != 1) {
            resultJson.setErrorCode(0);
            resultJson.setErrorMsg("数据库操作出错!请稍后再试");
            return resultJson;
        }
        resultJson.setStatus(true);
        request.getSession().removeAttribute("userId");
        return resultJson;
    }

    //查找好友接口
    @RequestMapping(value = "/findFriend", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson findFriend(HttpServletRequest request, @RequestParam int userId) {
        ResultJson resultJson = new ResultJson(true);
        UserEntity userEntity = userService.checkRepeatUserId(userId);
        //如果查询的用户不存在
        if (userEntity == null) {
            return resultJson;
        }

        UserStatus userStatus = new UserStatus();
        userStatus.setUserId(userId);
        userStatus.setOnline(userEntity.getOnline());
        if (userEntity.getLastLoginTime() != null) {
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            userStatus.setLastLoginTime(sdf.format(userEntity.getLastLoginTime()));
        } else {
            userStatus.setLastLoginTime(null);
        }

        resultJson.setResultObj(userStatus);
        return resultJson;
    }

    //添加好友接口
    @RequestMapping(value = "/makeFriend", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson makeFriend(@RequestParam int sUserId,
                                 HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        if (userService.checkRepeatUserId(sUserId) == null) {
            resultJson.setErrorCode(-1);
            resultJson.setErrorMsg("想要建立好友关系的用户不存在");
            return resultJson;
        }
        FriendshipEntity friendshipEntity = new FriendshipEntity();
        //从Session中获取userId
        String userIdStr = (String) request.getSession().getAttribute("userId");
        int userId = Integer.parseInt(userIdStr);
        //如果用户尝试添加自己
        if (userId == sUserId) {
            resultJson.setErrorCode(-1);
            resultJson.setErrorMsg("无法添加自己为好友");
            return resultJson;
        }
        //检查两人是否已是好友
        if(friendshipService.friendshipIsExisted(userId, sUserId)) {
            resultJson.setErrorCode(-1);
            resultJson.setErrorMsg("已是好友关系，请勿重复添加");
            return resultJson;
        }

        friendshipEntity.setfUserId(userId);
        friendshipEntity.setsUserId(sUserId);
        friendshipEntity.setSetUpTime(new Timestamp(new Date().getTime()));

        int result = friendshipService.makeFriendShip(friendshipEntity);
        if (result != 1) {
            resultJson.setErrorCode(0);
            resultJson.setErrorMsg("数据库操作出错，请稍后再试");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }

    //解除好友关系
    @RequestMapping(value = "/cancelFriend", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson cancelFriend(@RequestParam int sUserId,
                                   HttpServletRequest request) {
        ResultJson resultJson = new ResultJson(false);
        //从Session中获取userId
        String userIdStr = (String) request.getSession().getAttribute("userId");
        int userId = Integer.parseInt(userIdStr);

        FriendshipEntity friendshipEntity = friendshipService.
                getFriendship(userId, sUserId);
        if (friendshipEntity == null) {
            resultJson.setErrorCode(-1);
            resultJson.setErrorMsg("不存在的好友关系，无法删除");
            return resultJson;
        }
        int result = friendshipService.cancelFriendship(friendshipEntity);
        if (result != 1) {
            resultJson.setErrorCode(0);
            resultJson.setErrorMsg("数据库操作出错");
            return resultJson;
        }

        resultJson.setStatus(true);
        return resultJson;
    }
}
