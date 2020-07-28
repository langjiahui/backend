package com.myjava.service.service.serviceImpl;

import com.myjava.service.dao.MyUserMapper;
import com.myjava.service.domain.MyUser;
import com.myjava.service.domain.MyUserExample;
import com.myjava.service.service.IMyUserService;
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
    public MyUser getUser(String userName,String passWord) {
        MyUserExample example = new MyUserExample();
        example.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(passWord);
        List<MyUser> list = myUserMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

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
        return true;
    }
}
