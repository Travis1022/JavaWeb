package com.travis.sys.domain;

import com.travis.common.domain.BaseEntity;

import org.matt.persistent.db.find.annotation.model.SSCacheModel;
import org.matt.persistent.db.find.annotation.model.SSDomain;
import org.matt.persistent.db.find.annotation.model.SSId;
import org.matt.persistent.db.find.annotation.model.SSProperty;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@SSDomain(table = "sys_dictionary", condition = "sts='Y'", order = "table_name ,column_name,orderby asc")
@SSId(generateType = "identity")
@SSCacheModel(selectAll = true)
public class SysDictionary extends BaseEntity {

    /**
     * 表名
     **/
    @SSProperty(column = "Table_Name")
    private String tableName;

    /**
     * 字段名
     **/
    @SSProperty(column = "Column_Name")
    private String columnName;

    /**
     * 内容
     **/
    private String content;

    /**
     * 值
     **/
    private String value;

    /**
     * 排序
     **/
    private Long orderby;

    /**
     * 备注
     **/
    private String memo;


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }
}
