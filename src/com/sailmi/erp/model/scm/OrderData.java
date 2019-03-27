package com.sailmi.erp.model.scm;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 订单数据
 * @author 歌樵
 * 2014年9月23日
 */
@TableBind(name="scm_order_data")
public class OrderData extends Model<OrderData> {
	private static final long serialVersionUID = 1694015863938065042L;
	public static final String tableName="scm_order_data";
	public static OrderData dao=new OrderData();
}
