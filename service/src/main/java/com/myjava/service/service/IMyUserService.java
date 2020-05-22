package com.myjava.service.service;

import com.myjava.service.domain.MyUser;

public interface IMyUserService {
    /**
     * 根据username查询用户信息
     * @param userName
     * @return
     */
    MyUser getUser(String userName,String passWord);
}
