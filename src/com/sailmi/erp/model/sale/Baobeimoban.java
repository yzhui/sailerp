package com.sailmi.erp.model.sale;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*SaleBaobeimoban
*/
@TableBind(name="sale_baobeimoban")
public class Baobeimoban extends Model<Baobeimoban> {
	private static final long serialVersionUID = -2550091191627847801L;
	public static final String tableName="sale_baobeimoban";
	public static Baobeimoban dao=new Baobeimoban();
}