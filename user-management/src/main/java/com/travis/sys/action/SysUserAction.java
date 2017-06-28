package com.travis.sys.action;

import com.travis.common.action.BaseAction;
import com.travis.sys.domain.SysUser;
import com.travis.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:SysUser action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/sys/sys-user")
@SuppressWarnings("serial")
public class SysUserAction extends BaseAction<SysUser> {
    @Autowired
    @Qualifier("sysUserService")
    protected ISysUserService sysUserService;

    /**
     * 保存单条Dictionary记录.
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, HttpServletResponse response) {
        try {
            SysUser sysUser = bindEntity(request, SysUser.class);
            sysUserService.insert(sysUser);
            printJson(response, messageSuccuseWrap());
        } catch (Exception e) {
            logger.error("save", e);
            printJson(response, messageFailureWrap("保存失败！"));
        }
    }

}
