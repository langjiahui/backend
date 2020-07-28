package com.myjava.service.service;

import com.myjava.service.domain.MyUser;

public interface IMyUserService {

    /**
     * 根据username查询用户信息
     * @param userName
     * @return
     */
    MyUser getUser(String userName);

    /**
     * 校验用户信息
     * @param loginName
     * @param passWord
     * @return
     */
    boolean checkUser(String loginName, String passWord);

}
