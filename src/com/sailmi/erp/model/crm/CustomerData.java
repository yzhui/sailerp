package com.sailmi.erp.model.crm;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 客户/供应商 大字段数据
 * @author 歌樵
 */
@TableBind(name="crm_customer_data")
public class CustomerData extends Model<CustomerData> {
	private static final long serialVersionUID = -1974122280599644721L;
	public static final String tableName="crm_customer_data";
	public static CustomerData dao=new CustomerData();
	
}
