package com.glqdlt.ex.routingdatasource.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity()
@Table(name = "user")
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
