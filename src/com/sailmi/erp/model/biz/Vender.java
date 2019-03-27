package com.sailmi.erp.model.biz;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 厂家
 * @author 
 *2013年8月13日
 */
@TableBind(name="biz_vender")
public class Vender extends Model<Vender> {
	private static final long serialVersionUID = 6606481004216328944L;
	public static final String tableName="biz_vender";
	public static Vender dao=new Vender();
}
