package com.travis.sys.service.impl;

import com.travis.common.service.BaseMattService;
import com.travis.sys.domain.SysUser;
import com.travis.sys.service.ISysUserService;
import org.matt.persistent.db.mybatis.MybatisPage;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Service
public class SysUserService extends BaseMattService implements ISysUserService {

    /**
     * 查询分页
     *
     * @param
     * @return
     */

    public MybatisPage queryPaged(SysUser sysUser) {
        return super.getSelectPage(sysUser);
    }

    @Override
    public void insert(SysUser sysUser) {
        if (sysUser != null) {
            //插入或者替换（根据主键来定）
            sysUser.insertOrUpdate();
        }
    }

    @Override
    public void delete(SysUser sysUser) {
        if (sysUser != null) {
            sysUser.delete();
        }
    }

}
