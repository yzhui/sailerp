package com.sailmi.erp.ctrl.sys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.jfinal.anatation.RouteBind;
import com.sailmi.pageModel.DataGrid;
/**
 * 登录日志
 * @author 
 *2013年8月8日
 */
@RouteBind(path="sysLog")
public class SysLogCtrl extends AdminBaseController {
	public SysLogCtrl(){
	}
	@Override
	public void dataGrid() {
		DataGrid dg = new DataGrid();
		this.renderJson(dg);
	}
	public void del(){
		try{
			Date now=new Date();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MONTH,-3);//3个月前
			String time=(new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime())+" 23:59:59";
			Db.update("delete from "+tableName+" where LOGINTIME<=?",time);
			rendJson_(true, "删除成功！");
		} catch (Exception e) {
			log.error("删除异常", e);
			rendJson_(false, "删除异常！");
		}
	}
}
