package com.maigechuang.controller;

import com.maigechuang.entity.User;
import com.maigechuang.entity.bo.UserBO;
import com.maigechuang.entity.response.JSONResult;
import com.maigechuang.service.UserService;
import com.maigechuang.utils.CookieUtils;
import com.maigechuang.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mgc on 2020/11/29.
 */
@Controller
public class UserController {



    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    @ResponseBody
    public void testlogin(HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.sendRedirect("/login");
    }

    @PostMapping("/user/login")
    @ResponseBody
    public JSONResult login(User user, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        String username = user.getUsername();
        String password = user.getPassword();

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JSONResult.errorMsg("用户名或密码不能为空！");
        }

        if(password.length() > 15 ){
            return JSONResult.errorMsg("参数错误！");
        }

        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        User loginUser = userService.getUser(user);

        if(loginUser == null){
            return JSONResult.errorMsg("用户名或密码错误！");
        }

        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"caijiUser",user.getPassword());
        return JSONResult.ok();
    }



    @GetMapping("/user/logout")
    public void deletelogin(User user, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {

        String caijiUser = CookieUtils.getCookieValue(httpServletRequest, "caijiUser");
        if(StringUtils.isBlank(caijiUser)){
            httpServletResponse.sendRedirect("/login");
        }
        CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,"caijiUser");

        httpServletResponse.sendRedirect("/login");

    }

    //@GetMapping("/user/register")
    //@ResponseBody
    public String registerLogin(User user,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception {

        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        userService.insertUser(user);

        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"caijiUser",user.getPassword());

        return "注册成功";
    }


    @PostMapping("/user/edit")
    @ResponseBody
    public JSONResult editlogin(UserBO userBO, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {

        String oldpassword = userBO.getOldpassword();
        String password = userBO.getPassword();
        String username = userBO.getUsername();

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(oldpassword)){
            return JSONResult.errorMsg("参数不能为空！");
        }

        password = MD5Utils.getMD5Str(password);
        oldpassword = MD5Utils.getMD5Str(oldpassword);

        User user = new User();
        user.setPassword(oldpassword);
        user.setUsername(username);

        User queryUser = userService.getUser(user);
        if(queryUser == null){
            return JSONResult.errorMsg("旧密码验证失败！");
        }

        user.setPassword(password);

        userService.updateUser(user);

        return JSONResult.ok();
    }


}
