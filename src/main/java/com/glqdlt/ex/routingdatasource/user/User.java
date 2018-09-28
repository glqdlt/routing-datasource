package com.glqdlt.ex.routingdatasource.user;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.util.Date;

@Alias("user")
@Data
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Integer seq;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    private Date regDate;

}
