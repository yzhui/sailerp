package com.sailmi.erp.model.crm;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 客户流转记录
 * @author 歌樵
 */
@TableBind(name="crm_customer_record")
public class CustomerRecord extends Model<CustomerRecord> {
	private static final long serialVersionUID = -1974122280599644721L;
	public static final String tableName="CustomerData.java";
	public static CustomerRecord dao=new CustomerRecord();
	
}
