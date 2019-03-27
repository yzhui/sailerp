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
@TableBind(name="sale_baobei")
public class Baobei extends Model<Baobei> {
	public static final String tableName="sale_baobei";
	public static Baobei dao=new Baobei();
}