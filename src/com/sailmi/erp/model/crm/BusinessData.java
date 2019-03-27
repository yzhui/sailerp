package com.sailmi.erp.model.crm;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 商机数据
 * @author 歌樵
 */
@TableBind(name="crm_business_data")
public class BusinessData extends Model<BusinessData> {
	private static final long serialVersionUID = 8699093530520166772L;
	public static final String tableName="crm_business_data";
	public static BusinessData dao=new BusinessData();
	
}
