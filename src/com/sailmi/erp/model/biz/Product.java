package com.sailmi.erp.model.biz;

import com.jfinal.plugin.activerecord.Model;
/*
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 */
import com.sailmi.jfinal.anatation.TableBind;

@TableBind(name="biz_product")
public class Product extends Model<Product> {
	public static final String tableName="biz_product";
	public static Product dao=new Product();
}