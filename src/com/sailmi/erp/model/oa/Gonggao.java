package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 公告
 * @author joffee 歌樵
 *2013-06-16
 */
@TableBind(name="oa_gonggao")
public class Gonggao extends Model<Gonggao> {
	public static String tableName="oa_gonggao";
	private static final long serialVersionUID = -8270232243555946392L;
	public static Gonggao dao=new Gonggao();
}