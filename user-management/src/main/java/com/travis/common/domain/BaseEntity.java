package com.travis.common.domain;

import java.util.Date;

import com.travis.sys.domain.SysUser;
import org.matt.persistent.db.PersistentObject;
import org.matt.persistent.db.find.annotation.model.SSPrimaryKey;
import org.matt.persistent.db.find.annotation.model.SSProperty;

/**
 * @description:这是所有Domain的父类，提供的公共的字段供参考
 * @author: matt
 * @history:
 */
public abstract class BaseEntity extends PersistentObject {

	@SSPrimaryKey
	private Long id;

	/** 创建人ID **/
	@SSProperty(column = "CREATE_USER_ID")
	private Long createUserId;

	/** 创建日期 **/
	@SSProperty(column = "CREATE_DATE", update = false, select_type = "[min,max]", select_min_property = "startDate", select_max_property = "endDate")
	private Date createDate;

	/** 更新人ID **/
	@SSProperty(column = "MODIFY_USER_ID")
	private Long modifyUserId;

	/** 更新日期 **/
	@SSProperty(column = "MODIFY_DATE")
	private Date modifyDate;

	@SSProperty(defaultValue = "Y")
	private String sts;

	@SSProperty(disuse = true)
	private Integer page;
	@SSProperty(disuse = true)
	private Integer rows;
	@SSProperty(disuse = true)
	private Date startDate;
	@SSProperty(disuse = true)
	private Date endDate;

	@SSProperty(disuse = true)
	private SysUser createUser;

	@SSProperty(disuse = true)
	private SysUser modifyUser;

	/**
	 * 假删除
	 */
	public void deleteFack() {
		this.setSts("N");
		super.update();
	}

	public SysUser loadCreateUser() {
		if (createUser != null)
			return createUser;
		else if (createUserId != null) {
			SysUser sysUser = new SysUser();
			sysUser.setId(createUserId);
			sysUser.load();
			return sysUser;
		}
		return null;
	}

	public SysUser loadModifyUser() {
		if (modifyUser != null)
			return modifyUser;
		else if (modifyUserId != null) {
			SysUser sysUser = new SysUser();
			sysUser.setId(modifyUserId);
			sysUser.load();
			return sysUser;
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}