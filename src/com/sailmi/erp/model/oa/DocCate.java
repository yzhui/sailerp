package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 文档类别
 * @author joffee
 *2012-11-25 下午4:28:13
 */
@TableBind(name="oa_doccate")
public class DocCate extends Model<DocCate> {
	public static String tableName="oa_doccate";
	private static final long serialVersionUID = 8097458172749976793L;
	public static final DocCate dao=new DocCate();
}
