package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 公告栏
 * @author joffee 歌樵
 *2013-06-16
 */
@TableBind(name="oa_gglan")
public class Gglan extends Model<Gglan> {
	public static String tableName="oa_gglan";
	private static final long serialVersionUID = -8270232243555946392L;
	public static Gglan dao=new Gglan();
}