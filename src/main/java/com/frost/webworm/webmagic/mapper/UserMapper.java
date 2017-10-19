package com.frost.webworm.webmagic.mapper;

import com.frost.webworm.webmagic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by LB on 2017/10/19.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> getUserList();
}
