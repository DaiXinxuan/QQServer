package org.qq.server.controller;

import org.qq.server.entity.UserEntity;
import org.qq.server.request.UserInfo;
import org.qq.server.result.ResultJson;
import org.qq.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dxx on 2017/11/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
        return resultJson;
    }
}
