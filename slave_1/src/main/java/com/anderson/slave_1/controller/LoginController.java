package com.anderson.slave_1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anderson.slave_1.bo.User;
import com.anderson.slave_1.dao.UserDao;
import com.anderson.slave_1.utils.HttpClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            return "login";
        } else {
            return "success";
        }

    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User dbUser = userDao.findByUserName(user.getUserName());
        if (dbUser.getPassWord().equals(user.getPassWord())) {
            session.setAttribute("username", user.getUserName());
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/signInWithToken/{token}")
    public String signInWithToken(@PathVariable String token, HttpServletRequest request) {
        //1.使用token获取用户信息
        String ssoServerUrl = "http://locahost:8083/checkToken";
        Map<String, String> parms = new HashMap<>();
        parms.put("token", token);
        String feedBack = HttpClientHandler.doPostJson(ssoServerUrl, JSON.toJSONString(parms));
        JSONObject jsonObject = JSONObject.parseObject(feedBack);
        String cert = jsonObject.getString("cert");

        //2.通过用户信息匹配当前系统用户
        User dbUser=userDao.findByCert(cert);

        //3.匹配成功后设置session后跳转成功页面，否则跳转登录页面
        if(dbUser!=null){
            HttpSession session = request.getSession();
            session.setAttribute("username", dbUser.getUserName());
            return "success";
        }else{
            return "login";
        }


    }
}
