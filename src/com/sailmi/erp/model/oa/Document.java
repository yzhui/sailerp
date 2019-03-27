package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;

/**
 * 文档
 * @author joffee
 *2012-11-25 下午4:29:12
 */
@TableBind(name = "oa_document")
public class Document extends Model<Document> {
	public static String tableName="oa_document";
	private static final long serialVersionUID = -246148641655505272L;
	public static Document dao = new Document();
}
