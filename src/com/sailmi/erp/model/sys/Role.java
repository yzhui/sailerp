package com.sailmi.erp.model.sys;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;

/***
 * 角色
 * @author 
 *  2012-9-4 上午10:39:04
 */
@TableBind(name="sys_role")
public class Role extends Model<Role> {
	public static String tableName="sys_role";
	private static final long serialVersionUID = 1287150070505939605L;
	public static final Role dao=new Role();
}
