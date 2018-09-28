package com.glqdlt.ex.routingdatasource;

import com.glqdlt.ex.routingdatasource.user.User;
import com.glqdlt.ex.routingdatasource.user.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public List<User> findAllSlaveUsers() {
        return userRepo.findAll();
    }

//    propagation 은 기본적으로 required 가 default 이다. 이놈이 하는 것은 부모 트랙잭션이 있으면 거기에 따르겠다는 의미이다. 부모 트랜잭션이 없으면 자신의 트랜잭션을 새로 생성한다.
//            문제는 부모 트랜잭션이 readOnly= false 로 설정되어 있으면, 자기 자신도 false 로 동작한다. 이러면 의도한 데로 되지 않는다.
//            이를 막기 위해 REQUIRES_NEW 로 변경하면, 부모가 있든 말든 나는 내 트랜잭션을 생성하겠다는 의미이다. 이러면 의도한 데로 된다.I
//    자세한 것은 아래 블로그에서 공부가 되었다.
//    https://taetaetae.github.io/2016/10/08/20161008/
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public User findBySeqUser(Integer seq) {
        return userRepo.findBySeq(seq);

    }


}
