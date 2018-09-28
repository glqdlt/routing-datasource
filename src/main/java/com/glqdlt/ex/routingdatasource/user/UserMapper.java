package com.glqdlt.ex.routingdatasource.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where pid = #{seq}")
    User findUserBySeq(@Param("seq") Integer seq);

    @Update("update user SET name = #{name} where pid = #{seq}")
    void updateUserName(@Param("name") String name, @Param("seq") Integer seq);

}
