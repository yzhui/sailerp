package com.sailmi.erp.model.sys;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/***
 * 省份地市地区
 * @author 
 */
@TableBind(name="base_area")
public class Area extends Model<Area> {
	private static final long serialVersionUID = -3591577384917167495L;
	public static String tableName="base_area";
	public static final Area dao=new Area();
}
