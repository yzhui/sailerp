package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 文章
 * @author joffee
 */
@TableBind(name="oa_article")
public class Article extends Model<Article> {
	public static String tableName="oa_article";
	private static final long serialVersionUID = -2865038753805061266L;
	public static final Article dao=new Article();
}
