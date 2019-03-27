package com.sailmi.erp.ctrl.oa;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.oa.Calendar;
import com.sailmi.jfinal.anatation.RouteBind;
/**
 * 日程安排
 * @author 
 *2013年8月12日
 */
@RouteBind(path="calendar")
public class CalendarCtrl extends AdminBaseController<Calendar> {
	public CalendarCtrl(){
		modelClass=Calendar.class;
		tableName=Calendar.tableName;
	}
	public void index(){
		//获取传递的日期控件展示方式 日 周 月
		
		//查询当前的日程安排列表
	}
	
}
