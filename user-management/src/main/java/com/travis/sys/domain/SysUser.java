package com.travis.sys.domain;

import com.travis.common.domain.BaseEntity;

import org.matt.persistent.db.find.annotation.model.SSCacheModel;
import org.matt.persistent.db.find.annotation.model.SSDomain;
import org.matt.persistent.db.find.annotation.model.SSId;
import org.matt.persistent.db.find.annotation.model.SSProperty;
import org.matt.persistent.db.find.annotation.model.SSSelectHead;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@SSDomain(table="sys_user",condition="sts='Y'",order="id asc")
@SSId(generateType="identity")
@SSCacheModel(selectAll = true,flushondomain="SysRole,SysModule")
public class SysUser extends BaseEntity {
	
	/** 单位表ID**/
	@SSProperty(column = "Info_Department_Id")
	private Long infoDepartmentId;
	
	/** 姓名**/
	@SSProperty(column = "name",select_property="searchContent",select_sql="in (${searchContent})")
	private String name;
	
	/** 邮箱**/
	private String email;
	
	/** 性别**/
	private String sex;
	
	/** 手机号码**/
	private String mobile;
	
	/** 照片路径**/
	@SSProperty(column = "Photo_Path")
	private String photoPath;
	

	@SSProperty(disuse=true)
	private String sexContent;
	
	@SSProperty(disuse=true)
	private String searchContent;
	
	@SSSelectHead
	private String selectHead;
	
	public String getSelectHead() {
		return selectHead;
	}

	public void setSelectHead(String selectHead) {
		this.selectHead = selectHead;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	
	public void setInfoDepartmentId(Long infoDepartmentId){
		this.infoDepartmentId = infoDepartmentId;
	}
	
	public Long getInfoDepartmentId(){
		return infoDepartmentId;
	} 
		
	public void setName(String name){
		this.name = name;
	} 
	
	public String getName(){
		return name;
	} 
		
	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	} 
		
	public void setSex(String sex){
		this.sex = sex;
	} 
	
	public String getSex(){
		return sex;
	} 
		
	public void setMobile(String mobile){
		this.mobile = mobile;
	} 
	
	public String getMobile(){
		return mobile;
	} 
		
	public void setPhotoPath(String photoPath){
		this.photoPath = photoPath;
	} 
	
	public String getPhotoPath(){
		return photoPath;
	}


}
