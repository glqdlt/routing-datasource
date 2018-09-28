package com.glqdlt.ex.routingdatasource;

import com.glqdlt.ex.routingdatasource.user.User;
import com.glqdlt.ex.routingdatasource.user.UserMapper;
import com.glqdlt.ex.routingdatasource.user.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MasterService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    SlaveService slaveService;

    @Autowired
    UserMapper userMapper;

    @Transactional(transactionManager = "transactionManager")
    public List<User> findAllMasterUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public User findBySeq(Integer seq) {
        return userRepo.findBySeq(seq);
    }

    @Transactional(transactionManager = "transactionManager")
    public void saveUser() {
        User user = new User();
        user.setName("masterrrr");
        user.setPassword("masterrr");
        user.setRegDate(new Date());
        userRepo.save(user);
        userRepo.flush();
    }

    @Transactional(transactionManager = "transactionManager")
    public void saveUser(User user) {
        userRepo.save(user);
        userRepo.flush();
    }

    @Transactional()
    public void findSlaveSaveMaster() {
        log.info("find to slave...");
        User slaveUser = slaveService.findBySeqUser(1);
        log.info("find it : {}", slaveUser.toString());
        saveUser(slaveUser);
        User masterUser = findBySeq(slaveUser.getSeq());
        log.info("mastered : {}", masterUser.toString());
    }

    @Transactional(readOnly = false)
    public User findUserBySeqMybatis(Integer seq) {
        return userMapper.findUserBySeq(seq);
    }

    @Transactional()
    public void findSlaveOrmSaveMasterMybatis() {
        User user = slaveService.findBySeqUser(1);
        userMapper.updateUserName(user.getName(), 10);
    }


}
