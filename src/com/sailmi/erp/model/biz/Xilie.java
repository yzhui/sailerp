package com.sailmi.erp.model.biz;

import com.jfinal.plugin.activerecord.Model;
/*
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 */
import com.sailmi.jfinal.anatation.TableBind;

@TableBind(name="biz_xilie")
public class Xilie extends Model<Xilie> {
	public static final String tableName="biz_xilie";
	public static Xilie dao=new Xilie();
}