package com.txw.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.txw.test.common.entity.ResultEntity;
import com.txw.test.entity.UserEntity;
import com.txw.test.service.IUserService;

@Controller
@RequestMapping("User")
public class UserController {
	private Logger LOG = Logger.getLogger(this.getClass());

	@Resource
	private IUserService iUserService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultEntity<UserEntity> list() {
		ResultEntity<UserEntity> result = new ResultEntity<UserEntity>();

		List<UserEntity> list = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			list = iUserService.userList(map);
			result.setDataList(list);
			result.setResult("success");
		} catch (Exception e) {
			LOG.error(e.toString());
			result.setResult("error");
			result.setMessage(e.toString());
		}

		return result;
	}

}
