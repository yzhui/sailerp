package com.sailmi.erp.ctrl.scm;

import java.util.HashMap;
import java.util.Map;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.crm.Customer;
import com.sailmi.jfinal.anatation.RouteBind;

/**
 * 员工绩效
 * 
 * @author 歌樵 2014年10月21日
 */
@SuppressWarnings("rawtypes")
@RouteBind(path = "performance")
public class PerformanceCtrl extends AdminBaseController {
	public void rptList() {
		Map<String,Object> filter=new HashMap<String,Object>();
		filter.put("uid",this.getPara("uid"));
		filter.put("department_id",this.getPara("department_id"));
		filter.put("qryType",this.getParaToInt("qryType"));
		filter.put("start_date",this.getPara("start_date"));
		filter.put("end_date",this.getPara("end_date"));
		filter.put("company_id",this.getCompanyId());
		this.rendJson(true,null,"",Customer.dao.performance(filter));
	}
}
