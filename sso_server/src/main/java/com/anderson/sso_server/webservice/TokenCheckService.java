package com.anderson.sso_server.webservice;

import com.alibaba.fastjson.JSONObject;
import com.anderson.sso_server.bo.Token;
import com.anderson.sso_server.dao.TokenDao;
import com.anderson.sso_server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenCheckService {
    @Autowired
    TokenDao tokenDao;

    @Autowired
    UserDao userDao;

    @PostMapping(path="/checkToken",consumes = "application/json",produces = "application/json")
    @ResponseBody
    //接受post的json时需要注意，将接收的信息设置为@RequestBody注解可以接收到对应的json信息
    public String checkToken(@RequestBody String jsonStr){
        //业务系统使用token来认证中心查验token对应的用户信息
        JSONObject jsonObject=JSONObject.parseObject(jsonStr);
        String tokenStr= jsonObject.getString("token");
        Token dbToken = tokenDao.findByToken(tokenStr);
        if(dbToken!=null){
            return JSONObject.toJSONString(userDao.findByUuid(dbToken.getUserId()));
        }else{
            return JSONObject.toJSONString("");
        }
    }
}
