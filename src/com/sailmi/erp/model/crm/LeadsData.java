package com.sailmi.erp.model.crm;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 线索数据
 * @author 歌樵
 * 2014年9月22日
 */
@TableBind(name="crm_leads_data")
public class LeadsData extends Model<LeadsData> {
	private static final long serialVersionUID = 8699093530520166772L;
	public static final String tableName="crm_leads_data";
	public static LeadsData dao=new LeadsData();
}
