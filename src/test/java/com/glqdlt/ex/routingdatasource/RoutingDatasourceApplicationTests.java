package com.glqdlt.ex.routingdatasource;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoutingDatasourceApplicationTests {

    @Autowired
    SlaveService slaveService;

    @Autowired
    MasterService masterService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findSlaveUsers() {
        Assert.assertEquals(1, slaveService.findAllSlaveUsers().size());
    }

    @Test
    public void findMasterUsers() {
        log.info(masterService.findAllMasterUsers().toString());
        Assert.assertNotEquals(1,masterService.findAllMasterUsers().size());
    }

    @Test
    public void findSlaveSaveMaster() {
        masterService.findSlaveSaveMaster();
    }

    @Test
    public void findMybatisSlave(){
        log.info(slaveService.findUserBySeqMybatis(1).toString());
    }


    @Test
    public void findMybatisMaster(){
        log.info(masterService.findUserBySeqMybatis(3).toString());
    }

    @Test
    public void findSlaveWithMasterJpa(){
        masterService.findSlaveOrmSaveMasterMybatis();
    }

}
