package com.sailmi.erp.model.biz;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*
*/
@TableBind(name="biz_ptype")
public class Ptype extends Model<Ptype> {
	public static final String tableName="biz_ptype";
	public static Ptype dao=new Ptype();
}