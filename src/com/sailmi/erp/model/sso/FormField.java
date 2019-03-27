package com.sailmi.erp.model.sso;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 表单字段
 * @author joffee 歌樵
 *2013-06-23 19:43:20
 */
@TableBind(name="sso_fields")
public class FormField extends Model<FormField> {
	public static String tableName="sso_fields";
	private static final long serialVersionUID = -8270232243555946392L;
	public static FormField dao=new FormField();
}