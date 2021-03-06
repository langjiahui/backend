package com.myjava.service.serviceImpl;

import com.myjava.dao.MyUserMapper;
import com.myjava.domain.MyUser;
import com.myjava.domain.MyUserExample;
import com.myjava.service.IMyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Component
public class MyUserServiceImpl implements IMyUserService {
    @Autowired
    MyUserMapper myUserMapper;
    
    @Override
    public MyUser getUser(String userName) {
        MyUserExample example = new MyUserExample();
        example.createCriteria().andUsernameEqualTo(userName);
        List<MyUser> list = myUserMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean checkUser(String loginName, String passWord) {
        MyUserExample example = new MyUserExample();
        example.createCriteria().andUsernameEqualTo(loginName).andPasswordEqualTo(passWord);
        List<MyUser> list = myUserMapper.selectByExample(example);
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
