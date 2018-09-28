package com.glqdlt.ex.routingdatasource;

import com.glqdlt.ex.routingdatasource.user.User;
import com.glqdlt.ex.routingdatasource.user.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SlaveService {

    @Autowired
    UserRepo userRepo;

//    public 이 붙어야지  @Transactional(readOnly = true) 의 readOnly 가 적용된다.
//    실제로 TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? 를 찍어보면.. public 의 유무에 따라 동작안하는 것을 확인할 수가 있다.
//    http://netframework.tistory.com/entry/Spring-Transactional%EC%97%90-%EB%8C%80%ED%95%98%EC%97%AC 에서 양질의 정보를 얻을 수 있었다.
//    @Transactional 어노테이션은 proxy 를 사용하기 때문에 public method 에서만 동작한다.

//    이 사실을 몰라 하루죙일 엄청난 삽질을 했던 건 비밀..
    @Transactional(readOnly = true)
    public List<User> findAllSlaveUsers(){
        return userRepo.findAll();
    }


}
