package com.travis.common.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travis.common.service.BaseMattService;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travis.common.utils.JsonUtils;
import com.travis.common.utils.page.PageInfo;
import org.matt.persistent.db.PersistentObject;
import org.matt.persistent.db.mybatis.MybatisPage;
import org.matt.util.BeanUtils;
import org.matt.util.GenericsUtils;
import org.matt.web.action.DefaultAction;
import org.matt.web.utils.SpringContextUtil;

/**
 * 增删改查基类.
 * <p>
 * 提供了访问路径校验、异常处理机制.
 * <p>
 * 同时也提供了请求数据组装(组装到Map/EntityBean)、结果数据和消息打印的公共方法.
 *
 * @author: matt
 * @date: 20150405
 */
@SuppressWarnings("serial")
public abstract class BaseAction<T extends PersistentObject> extends DefaultAction {
    protected Logger logger = Logger.getLogger(getClass());

    protected Class<T> entityClass;
    // protected K entityService;
    protected BaseMattService entityService;

    public BaseAction() {
        entityClass = GenericsUtils.getSuperClassGenricType(getClass(), 0);
        // entityServiceClass =
        // GenericsUtils.getSuperClassGenricType(getClass(),1);
        String entityServiceName = entityClass.getSimpleName();
        entityServiceName = entityServiceName.substring(0, 1).toLowerCase() + entityServiceName.substring(1);
        entityService = SpringContextUtil.getBean(entityServiceName + "Service");
    }

    protected <T extends PersistentObject> T bindEntity(HttpServletRequest request, Class<T> clazz) throws Exception {
        T entity = clazz.newInstance();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String propertyName = (String) enumeration.nextElement();
            String propertyValue = request.getParameter(propertyName).trim();
            propertyValue = propertyValue.replace("\'", "\"");
            BeanUtils.setBeanPropertyByName(entity, propertyName, propertyValue);
        }
        // if (entity.getId() == null) {
        // entity.setCreateUserId(this.getCurrentUser().getId());
        // } else {
        // entity.setModifyUserId(this.getCurrentUser().getId());
        // }
        return entity;
    }

    /**
     * 分页查询列表.
     */
    @RequestMapping(value = "queryPaged")
    public void queryPaged(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            T entityBean = bindEntity(request, entityClass);

            MybatisPage mybatisPage = entityService.getSelectPage(entityBean);
            PageInfo<T> pageInfo = new PageInfo<T>(mybatisPage);
            printJson(response, JsonUtils.bean2Json(pageInfo, "YYYY-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            printJson(response, messageFailureWrap("查询失败！"));
        }
    }

    /**
     * 保存单条Dictionary记录.
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, HttpServletResponse response) {
        try {
            T o = bindEntity(request, entityClass);
            entityService.insertOrUpdate(o);
            printJson(response, messageSuccuseWrap());
        } catch (Exception e) {
            logger.error("save", e);
            printJson(response, messageFailureWrap("保存失败！"));
        }
    }

//	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
//	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
//		try {
//			T entityBean = entityClass.newInstance();
//			BeanUtils.setBeanPropertyByName(entityBean, "id", id.toString());
//			entityBean.deleteEqual(); // entityService.deleteEqual(entityBean);
//			printJson(response, messageSuccuseWrap());
//		} catch (Exception e) {
//			logger.error("delete", e);
//			printJson(response, messageFailureWrap("删除失败！"));
//		}
//	}


    /**
     * 根据id来删除表中的记录（一条或者多条）
     *
     * @param ids
     * @param response
     */
    @RequestMapping(value = "del/{ids}", method = RequestMethod.POST)
    public void del(@PathVariable("ids") String ids, HttpServletResponse response) {
        try {
            if (ids != null && ids.length() > 0) {
                String[] idsarr = ids.split(",");
                List list = new ArrayList();
                for (int i = 0; i < idsarr.length; i++) {
                    T entityBean = entityClass.newInstance();
                    BeanUtils.setBeanPropertyByName(entityBean, "id", idsarr[i]);
                    list.add(entityBean);
                }
                entityService.deleteListEqual(list);
            }
            printJson(response, messageSuccuseWrap());
        } catch (Exception e) {
            logger.error("delete", e);
            printJson(response, messageFailureWrap("删除失败！"));
        }
    }

    @RequestMapping(value = "add")
    public String add() {
        return getNameSpace() + "add";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) throws Exception {
        if (id != null) {
            T entityBean = entityClass.newInstance();
            BeanUtils.setBeanPropertyByName(entityBean, "id", id.toString());

            String entityServiceName = entityClass.getSimpleName();
            entityServiceName = entityServiceName.substring(0, 1).toLowerCase() + entityServiceName.substring(1);
            model.addAttribute(entityServiceName, entityService.load(entityBean));
        }
        return getNameSpace() + "edit";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return getNameSpace() + "index";
    }

    protected String getNameSpace() {
        String ns = null;
        RequestMapping r = getClass().getAnnotation(RequestMapping.class);
        ns = r.value()[0];
        if (!ns.endsWith("/"))
            ns += "/";
        return ns;
    }

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String error() {
        return getNameSpace() + "error";
    }
}
