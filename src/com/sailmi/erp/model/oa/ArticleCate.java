package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 文章类别
 * @author joffee
 */
@TableBind(name="oa_articlecate")
public class ArticleCate extends Model<ArticleCate> {
	public static String tableName="oa_articlecate";
	private static final long serialVersionUID = 1291278238314905734L;
	public static final ArticleCate dao=new ArticleCate();
}
