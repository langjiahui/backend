package com.myjava.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.myjava.rest.utils.JwtUtil;
import com.myjava.rest.vo.AjaxResult;
import com.myjava.domain.MyUser;
import com.myjava.service.IMyUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserManageController {
    @Resource
    IMyUserService myUserService;

    @GetMapping("/hello")
    public String helloSpringBoot() {
        return "Hello SpringBoot Project.";
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody Map<String, String> map) {
        String loginName = map.get("loginName");
        String passWord = map.get("passWord");
        //身份验证
        boolean isSuccess = myUserService.checkUser(loginName, passWord);
        if (isSuccess) {
            //模拟数据库查询
            MyUser user = myUserService.getUser(loginName);
            if (user != null) {
                //返回token
                String token = JwtUtil.sign(loginName, passWord);
                if (token != null) {
                    return AjaxResult.success("成功", token);
                }
            }
        }
        return AjaxResult.fail();
    }

    @PostMapping("/getUser")
    public AjaxResult getUserInfo(HttpServletRequest request, @RequestBody Map<String, String> map) {
        String loginName = map.get("loginName");
        MyUser user = myUserService.getUser(loginName);
        return AjaxResult.success("成功", JSONObject.toJSONString(user));
    }


}
