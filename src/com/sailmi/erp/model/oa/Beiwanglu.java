package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 备忘录
 * @author joffee 歌樵
 *2013-06-16
 */
@TableBind(name="oa_beiwanglu")
public class Beiwanglu extends Model<Beiwanglu> {
	public static String tableName="oa_beiwanglu";
	private static final long serialVersionUID = -8270232243555946392L;
	public static Beiwanglu dao=new Beiwanglu();
}