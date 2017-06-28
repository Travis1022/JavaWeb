package com.travis.common.service;

import org.matt.persistent.db.PersistentFactory;
import org.matt.persistent.db.PersistentObject;
import org.matt.persistent.db.mybatis.MybatisPage;
import org.matt.util.BeanHelper;
import org.matt.util.StringHelper;

import java.io.Serializable;
import java.util.List;

//import org.matt.util.GenericsUtils;

public abstract class BaseMattService {

	// protected Class<T> entityClass;
	@SuppressWarnings("unchecked")
	public BaseMattService() {
		// this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	public void insert(PersistentObject entityBean) {
		PersistentFactory.insert(entityBean);
	}

	public void delete(PersistentObject entityBean) {
		PersistentFactory.delete(entityBean);
	}

	public void deleteEqual(PersistentObject entityBean) {
		PersistentFactory.deleteEqual(entityBean);
	}

	public void deleteListEqual(List<PersistentObject> entityBeanList) {
		if (entityBeanList != null && entityBeanList.size() > 0) {
			for (int i = 0; i < entityBeanList.size(); i++) {
				PersistentFactory.deleteEqual(entityBeanList.get(i));
			}
		}
	}

	public void update(PersistentObject entityBean) {
		PersistentFactory.update(entityBean);
	}

	public void updateNull(PersistentObject entityBean) {
		PersistentFactory.updateNull(entityBean);
	}

	public void insertOrUpdate(PersistentObject entityBean) {
		PersistentFactory.insertOrUpdate(entityBean);
	}

	public Object load(PersistentObject entityBean) {
		return PersistentFactory.load(entityBean);
	}

	public boolean exist(PersistentObject entityBean) {
		return PersistentFactory.exist(entityBean);
	}

	public boolean updateExist(PersistentObject entityBean, Serializable id) {
		return PersistentFactory.updateExist(entityBean, id);
	}

	public int count(PersistentObject entityBean) {
		return PersistentFactory.count(entityBean);
	}

	public int countEqual(PersistentObject entityBean) {
		return PersistentFactory.countEqual(entityBean);
	}

	public Object loadEqual(PersistentObject entityBean) {
		return PersistentFactory.loadEqual(entityBean);
	}

	public List select(PersistentObject entityBean) {
		return PersistentFactory.select(entityBean);
	}

	public List select(PersistentObject entityBean, int maxsize) {
		return PersistentFactory.select(entityBean, maxsize);
	}

	public List selectEqual(PersistentObject entityBean) {
		return PersistentFactory.selectEqual(entityBean);
	}

	public List selectEqual(PersistentObject entityBean, int maxsize) {
		return PersistentFactory.selectEqual(entityBean, maxsize);
	}

	public Object selectEqualSingle(PersistentObject entityBean) {
		return PersistentFactory.selectEqualSingle(entityBean);
	}

	public Object selectSingle(PersistentObject entityBean) {
		return PersistentFactory.selectSingle(entityBean);
	}

	public List selectAll(PersistentObject entityBean) {
		return PersistentFactory.selectAll(entityBean);
	}

	public MybatisPage getSelectPage(PersistentObject entityBean, MybatisPage page) {
		return PersistentFactory.getSelectPage(entityBean, page);
	}

	public MybatisPage getSelectPage(PersistentObject entityBean) {
		MybatisPage mybatisPage = new MybatisPage();
		int rows = StringHelper.formatInt(BeanHelper.getPropertyValue("rows", entityBean), 0);
		int pages = StringHelper.formatInt(BeanHelper.getPropertyValue("page", entityBean), 0);
		if (rows != 0)
			mybatisPage.setPagesize(rows);
		mybatisPage.setPage(pages);
		mybatisPage = entityBean.getSelectPage(mybatisPage);
		return PersistentFactory.getSelectPage(entityBean, mybatisPage);
	}

	public List queryPageList(PersistentObject entityBean, MybatisPage page) {
		return PersistentFactory.queryPageList(entityBean, page);
	}

}
