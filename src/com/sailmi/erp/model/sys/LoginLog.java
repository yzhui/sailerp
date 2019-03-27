package com.sailmi.erp.model.sys;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;

/**
 * 登录登出日志
 * 
 * @author  2013年8月8日
 */
@TableBind(name = "sys_loginlog")
public class LoginLog extends Model<LoginLog> {
	public static String tableName="sys_loginlog";
	private static final long serialVersionUID = -8687063029204140095L;
	public static LoginLog dao = new LoginLog();
}
