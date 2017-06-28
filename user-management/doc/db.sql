/*
Navicat MySQL Data Transfer

Source Server         : dev_mysql
Source Server Version : 50621
Source Host           : 10.0.0.110:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-05-24 10:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_config_appfile`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_appfile`;
CREATE TABLE `sys_config_appfile` (
  `ID` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `PATH` varchar(500) DEFAULT NULL COMMENT '文件路径',
  `FILETYPE` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `FILESIZE` bigint(10) DEFAULT NULL COMMENT '文件大小',
  `OBJECT_ID` bigint(12) DEFAULT NULL,
  `TYPE` varchar(1) DEFAULT NULL,
  `CREATE_USER_ID` bigint(12) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `STS` varchar(1) DEFAULT NULL,
  `MODIFY_USER_ID` bigint(12) DEFAULT NULL COMMENT '更新人ID',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config_appfile
-- ----------------------------
INSERT INTO `sys_config_appfile` VALUES ('6', 'XX-ISMS-SO-02002_XXXXX信息安全检查管理规定V0.1', '/appfile/2016/05/19/XX-ISMS-SO-02002_XXXXX信息安全检查管理规定V0.1_1463646628312.docx', 'docx', '41978', '-1', null, null, null, 'Y', null, null);

-- ----------------------------
-- Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统字典表ID',
  `Table_Name` varchar(100) DEFAULT NULL COMMENT '表名',
  `Column_Name` varchar(100) DEFAULT NULL COMMENT '字段名',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `Value` varchar(500) DEFAULT NULL COMMENT '值',
  `Orderby` bigint(20) DEFAULT NULL COMMENT '排序',
  `Memo` varchar(500) DEFAULT NULL COMMENT '备注',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统字典表';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('1', 'SYS_USER', 'SEX', '男', '1', '1', null, null, '1', '2016-05-19 08:59:53', null, 'Y');
INSERT INTO `sys_dictionary` VALUES ('2', 'SYS_MODULE', 'MODULE_TYPE', '菜单', '1', '1', '', null, null, null, null, 'Y');
INSERT INTO `sys_dictionary` VALUES ('3', 'SYS_MODULE', 'MODULE_TYPE', '按钮', '2', '2', '', null, null, null, null, 'Y');
INSERT INTO `sys_dictionary` VALUES ('4', 'SYS_MODULE', 'MODULE_TYPE', '链接', '3', '3', '', null, null, null, null, 'Y');
INSERT INTO `sys_dictionary` VALUES ('5', 'SYS_USER', 'SEX', '女', '2', '2', '', null, null, null, null, 'Y');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统日志表ID',
  `Info_Department_Id` bigint(20) DEFAULT NULL COMMENT '单位ID',
  `Type` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `Module` varchar(32) DEFAULT NULL COMMENT '操作模块',
  `Content` varchar(500) DEFAULT NULL COMMENT '操作内容',
  `IP` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `Exception` varchar(2000) DEFAULT NULL COMMENT '异常',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_login`
-- ----------------------------
DROP TABLE IF EXISTS `sys_login`;
CREATE TABLE `sys_login` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户登录表ID',
  `Sys_User_Id` bigint(20) DEFAULT NULL COMMENT '用户表ID',
  `SerialNumber` varchar(100) DEFAULT NULL COMMENT '序列号',
  `Ukey_Password` varchar(32) DEFAULT NULL COMMENT 'U盾密码',
  `SaltValue` varchar(100) DEFAULT NULL COMMENT '随机混淆数',
  `Fingerprint1` varchar(1024) DEFAULT NULL COMMENT '指纹1',
  `Fingerprint2` varchar(1024) DEFAULT NULL COMMENT '指纹2',
  `Account` varchar(100) NOT NULL COMMENT '登录账号',
  `Password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `Last_Time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `Limit_Time` datetime DEFAULT NULL COMMENT '有效时间',
  `Login_Id` varchar(50) DEFAULT NULL COMMENT '用户登录标识',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of sys_login
-- ----------------------------
INSERT INTO `sys_login` VALUES ('1', '2', '11111', '11111', '11111', '', '', 'test', 'test123', '2016-05-23 13:32:33', '2016-05-20 19:59:31', '111111122334566', '1', '1', '2016-05-04 19:45:03', '2016-05-14 19:45:08', 'Y');
INSERT INTO `sys_login` VALUES ('2', '2', '', '', '', '', '', 'test2', '123456', null, '2016-05-28 19:59:35', '', '1', '1', null, null, 'Y');

-- ----------------------------
-- Table structure for `sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模块表ID',
  `Module_Name` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `Module_Code` varchar(50) NOT NULL COMMENT '模块代码',
  `Parent_Module_Id` bigint(20) DEFAULT NULL COMMENT '父模块ID',
  `Parent_Module_Name` varchar(50) DEFAULT NULL COMMENT '父模块NAME',
  `Target` varchar(50) DEFAULT NULL COMMENT '目标位置',
  `Url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `html` varchar(100) DEFAULT NULL COMMENT 'HTML',
  `Module_Type` varchar(20) DEFAULT NULL COMMENT '模块类型',
  `Orderby` int(11) DEFAULT NULL COMMENT '排序',
  `Module_Memo` varchar(200) DEFAULT NULL COMMENT '模块描述',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`),
  KEY `AK_Key_2` (`Module_Code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='模块表';

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('1', '系统管理', 'xtgl', '-1', '', '', '', '', '1', '1', '菜单', null, null, null, null, 'Y');
INSERT INTO `sys_module` VALUES ('2', '登录管理', 'xtgl_dlgl', '1', '系统管理', '', '', '', '1', '2', '菜单', null, null, null, null, 'Y');
INSERT INTO `sys_module` VALUES ('3', '新增', 'xtgl_dlgl_xz', '2', '登录管理', '', '', '', '2', '1', '按钮', null, null, null, null, 'Y');
INSERT INTO `sys_module` VALUES ('4', '修改', 'xtgl_dlgl_xg', '2', '登录管理', '', '', '', '2', '2', '按钮', null, null, null, null, 'Y');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色表ID',
  `Role_Name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `Parent_Role_Id` bigint(20) DEFAULT NULL COMMENT '父角色ID',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '-1', null, null, null, null, 'Y');
INSERT INTO `sys_role` VALUES ('2', '管理员', '1', null, null, null, null, 'Y');

-- ----------------------------
-- Table structure for `sys_role_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色模块表ID',
  `Sys_Role_Id` bigint(20) DEFAULT NULL COMMENT '角色表ID',
  `Sys_Module_Id` bigint(20) DEFAULT NULL COMMENT '模块表ID',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色模块表';

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户表ID',
  `Info_Department_Id` bigint(20) DEFAULT NULL COMMENT '单位表ID',
  `Name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `Email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `Sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `Mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `Photo_Path` varchar(100) DEFAULT NULL COMMENT '照片路径',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', '1', 'test', 'test@hzcec.net', '1', '13335712501', 'c:/photo', null, '2', '2016-05-24 09:54:53', null, 'Y');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色表ID',
  `Sys_User_Id` bigint(20) DEFAULT NULL COMMENT '用户表ID',
  `Sys_Role_Id` bigint(20) DEFAULT NULL COMMENT '角色表ID',
  `Modify_User_Id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `Create_User_Id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `Create_Date` datetime DEFAULT NULL COMMENT '创建时间',
  `Modify_Date` datetime DEFAULT NULL COMMENT '更新时间',
  `Sts` char(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
