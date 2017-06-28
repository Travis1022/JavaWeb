package com.travis.sys.service;

import com.travis.sys.domain.SysUser;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface ISysUserService {

    /**
     * 插入用户表数据
     */
    void insert(SysUser sysUser);

    /**
     * 删除用户表数据
     */
    void delete(SysUser sysUser);
}
