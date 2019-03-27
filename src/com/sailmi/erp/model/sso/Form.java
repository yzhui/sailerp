package com.sailmi.erp.model.sso;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 表单
 * @author joffee 歌樵
 *2013-06-23
 */
@TableBind(name="sso_form")
public class Form extends Model<Form> {
	public static String tableName="sso_form";
	private static final long serialVersionUID = -8270232243555946392L;
	public static Form dao=new Form();
}