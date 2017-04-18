package com.txw.test.dao;

import java.util.List;
import java.util.Map;

import com.txw.test.entity.UserEntity;

public interface UserDao {
	public List<UserEntity> userList(Map<String, Object> map);
}
