package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 日程安排
 * @author 
 *2013年8月12日
 */
@TableBind(name="oa_calendar")
public class Calendar extends Model<Calendar> {
	private static final long serialVersionUID = 8721928416565055455L;
	public static Calendar da=new Calendar();
	public static String tableName="oa_calendar";
}
