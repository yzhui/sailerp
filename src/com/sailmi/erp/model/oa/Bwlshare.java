package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 备忘录共享
 * @author joffee 歌樵
 *2013-06-16
 */
@TableBind(name="oa_bwlshare")
public class Bwlshare extends Model<Bwlshare> {
	public static String tableName="oa_bwlshare";
	private static final long serialVersionUID = -8270232243555946392L;
	public static Bwlshare dao=new Bwlshare();
}