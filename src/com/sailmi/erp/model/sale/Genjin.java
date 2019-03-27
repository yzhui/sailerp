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
@TableBind(name="sale_genjin")
public class Genjin extends Model<Genjin> {
	public static final String tableName="sale_genjin";
	public static Genjin dao=new Genjin();
}