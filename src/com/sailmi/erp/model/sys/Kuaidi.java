package com.sailmi.erp.model.sys;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*Kuaidi
*/
@TableBind(name="sys_kuaidi")
public class Kuaidi extends Model<Kuaidi> {
	private static final long serialVersionUID = 6127997239358027146L;
	public static final String tableName="sys_kuaidi";
	public static Kuaidi dao=new Kuaidi();
}