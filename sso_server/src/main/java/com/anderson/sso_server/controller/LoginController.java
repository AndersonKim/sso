package com.anderson.sso_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.anderson.sso_server.bo.Token;
import com.anderson.sso_server.bo.User;
import com.anderson.sso_server.dao.TokenDao;
import com.anderson.sso_server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserDao userDao;
    @Autowired
    TokenDao tokenDao;


    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            return "login";
        }else{
            return "success";
        }

    }
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        User dbUser=userDao.findByUserName(user.getUserName());
        if(dbUser.getPassWord().equals(user.getPassWord())){
            session.setAttribute("username",user.getUserName());
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/toSlave_1")
    public String toSlave_1(){

    }

    @RequestMapping("/checkToken")
    @ResponseBody
    public String checkToken(String json){
        JSONObject jsonObject=JSONObject.parseObject(json);
        String token= jsonObject.getString("token");
        Token dbToken = tokenDao.findByToken(token);
        User dbUser=userDao.findById(dbToken.getUserId());
        return token;
    }

}
