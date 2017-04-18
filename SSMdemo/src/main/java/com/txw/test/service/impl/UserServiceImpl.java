package com.txw.test.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.txw.test.dao.UserDao;
import com.txw.test.entity.UserEntity;
import com.txw.test.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public List<UserEntity> userList(Map<String, Object> map) {
		return dao.userList(map);
	}

}
