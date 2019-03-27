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
@TableBind(name="sale_genjinfankui")
public class Genjinfankui extends Model<Genjinfankui> {
	public static final String tableName="sale_genjinfankui";
	public static Genjinfankui dao=new Genjinfankui();
}