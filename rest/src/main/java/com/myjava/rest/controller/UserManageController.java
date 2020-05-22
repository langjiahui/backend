package com.myjava.rest.controller;

import com.myjava.rest.vo.RespBean;
import com.myjava.service.domain.MyUser;
import com.myjava.service.service.IMyUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
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
    public RespBean login(@RequestBody Map<String, Object> info){
        String password = (String) info.get("password");
        String username = (String) info.get("username");
        MyUser user =  myUserService.getUser(username,password);
        RespBean respBean = new RespBean();
        if(user!=null){
            respBean.setMsg("登入成功!");
        }else{
            respBean.setMsg("登入失败");
        }
        return  respBean;
    }

}
