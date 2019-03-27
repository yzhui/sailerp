package com.sailmi.erp.model.sale;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*
*/
@TableBind(name="sale_richeng")
public class Richeng extends Model<Richeng> {
	public static final String tableName="sale_richeng";
	public static Richeng dao=new Richeng();
}