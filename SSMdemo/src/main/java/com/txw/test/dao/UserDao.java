package com.txw.test.dao;

import com.txw.test.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<UserEntity> userList(Map<String, Object> map);
}
