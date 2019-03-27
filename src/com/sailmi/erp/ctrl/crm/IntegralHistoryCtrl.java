package com.sailmi.erp.ctrl.crm;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Page;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.crm.IntegralHistory;
import com.sailmi.jfinal.anatation.RouteBind;

/**
 * 会员积分记录查询
 * @author 歌樵
 * 2014年9月15日
 */
@RouteBind(path = "integralHistory")
public class IntegralHistoryCtrl extends AdminBaseController<IntegralHistory> {
	public void dataGrid(){
		Map<String,Object> filter=new HashMap<String,Object>();
		String cust_id=this.getPara("customer_id");
		filter.put("customer_id",cust_id);
		String keyword=this.getPara("keyword");
		filter.put("keyword",keyword);
		String rating=this.getPara("rating");
		filter.put("rating",rating);
		filter.put("company_id",this.getCompanyId());
		Page<IntegralHistory> page = IntegralHistory.dao.pageGrid(getPageNo(), getPageSize(),filter);
		this.rendJson(true,null, "success", page);
	}
}
