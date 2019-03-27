package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*Holiday
*/
@TableBind(name="oa_holiday")
public class Holiday extends Model<Holiday> {
	public static final String tableName="oa_holiday";
	public static Holiday dao=new Holiday();
}