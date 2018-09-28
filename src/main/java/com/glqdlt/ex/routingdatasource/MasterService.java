package com.glqdlt.ex.routingdatasource;

import com.glqdlt.ex.routingdatasource.user.User;
import com.glqdlt.ex.routingdatasource.user.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MasterService {

    @Autowired
    UserRepo userRepo;

    @Transactional(transactionManager = "transactionManager")
    public List<User> findAllMasterUsers(){
        return userRepo.findAll();
    }

    @Transactional(transactionManager = "transactionManager")
    public void saveUser(){
        User user = new User();
        user.setName("masterrrr");
        user.setPassword("masterrr");
        user.setRegDate(new Date());
        userRepo.save(user);
        userRepo.flush();
    }



}
