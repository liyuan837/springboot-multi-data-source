package com.liyuan.demo.controller;

import com.liyuan.demo.util.JwtUtil;
import com.liyuan.demo.util.JwtUser;
import com.liyuan.demo.annotation.NotToken;
import com.liyuan.demo.domain.exception.MultiDataSourceException;
import com.liyuan.demo.domain.response.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/jwt")
@Api(value = "/jwt", description = "jwt登录验证测试：此Controller下的所有接口" +
        "（除添加@NotToken注解）都是被JwtInterceptor拦截的，用来进行登录验证测试")
public class JwtController extends BaseController{

//模拟登陆成功，返回token
    @ApiOperation(value = "登录",notes = "登录",httpMethod = "POST")
    @NotToken
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(){
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername("李袁");
        jwtUser.setUsercode("A0001");

        String token = JwtUtil.generateToken(jwtUser);
        return getSuccessResult(token);
    }
    //模拟携带token的请求解析
    @ApiOperation(value = "解析登录用户数据",notes = "解析登录用户数据",httpMethod = "POST")
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public ResponseEntity get(@RequestHeader("Authorization") String token) throws MultiDataSourceException {
        return getSuccessResult(JwtUtil.checkLogin(token));
    }
}