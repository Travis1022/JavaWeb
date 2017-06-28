package com.travis.common.utils.page;

import java.util.List;

import org.matt.persistent.db.PersistentObject;
import org.matt.persistent.db.mybatis.MybatisPage;
import org.matt.util.BeanHelper;
import org.matt.util.StringHelper;

public class PageInfo<T extends PersistentObject> {
	private long total;
	private List<T> rows;

	public PageInfo(MybatisPage page) {
		this.rows = page.dataList;
		this.total = page.getTotal();

	}

	public PageInfo(T entityBean) {
		MybatisPage page = new MybatisPage();
		int rows = StringHelper.formatInt(BeanHelper.getPropertyValue("rows", entityBean), 0);
		int pages = StringHelper.formatInt(BeanHelper.getPropertyValue("page", entityBean), 0);
		if (rows != 0)
			page.setPagesize(rows);
		page.setPage(pages);
		page = entityBean.getSelectPage(page);
		this.rows = page.dataList;
		this.total = page.getTotal();
	}

	public long getTotal() {
		return this.total;
	}

	public List<T> getRows() {
		return this.rows;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("PageInfo{");
		sb.append(", total=").append(this.total);
		sb.append(", rows=").append(this.rows);
		sb.append('}');
		return sb.toString();
	}
}