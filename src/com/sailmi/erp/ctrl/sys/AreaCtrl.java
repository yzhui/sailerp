package com.sailmi.erp.ctrl.sys;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.sys.Area;
import com.sailmi.jfinal.anatation.RouteBind;
/**
 * 省份地市区域
 * @author joffee
 *2013-3-31 上午11:14:41
 */
@RouteBind(path="area")
public class AreaCtrl extends AdminBaseController<Area> {
	public AreaCtrl(){
		this.tableName=Area.tableName;
		modelClass=Area.class;
	}
	public void combobox(){
		Long pid=this.getParaToLong(0);
		List<Record> list=Db.find("select * from "+this.tableName+" where pid=?",pid);
		this.renderJson(list);
	}
}
