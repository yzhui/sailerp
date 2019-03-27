package com.sailmi.erp.ctrl.sys;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.sso.FormField;
import com.sailmi.jfinal.anatation.RouteBind;
import com.sailmi.pageModel.DataGrid;
/**
 * 表单字段
 * @author joffee 歌樵
 *2013-06-23 19:43:20
 */
@RouteBind(path = "formField")
public class FormFieldCtrl extends AdminBaseController<FormField> {
	public FormFieldCtrl() {
		this.tableName = FormField.tableName;
		modelClass = FormField.class;
	}
	public void index(){
		this.setAttr("tableId", this.getParaToLong(0));
	}
	public void dataGrid() {
		List<Object> param = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		/** 添加查询字段条件*/
		String sortName = this.getPara("sort");
		String sortOrder = this.getPara("order", "desc");
		where.append(" order by sortnum asc ");//排序号 升序 
		if (sortName != null) {
			where.append(", ");
			where.append(sortName);
			where.append(" ");
			where.append(sortOrder);
		}
		Long tableId=this.getParaToLong(0);
		this.setAttr("tableId", tableId);
		param.add(tableId);
		Page<Record> p = Db.paginate(this.getParaToInt("page", 1), this.getParaToInt("rows", 20), "select * ", "from " + this.tableName+ " where fmid=? " + where.toString(), param.toArray());
		DataGrid dg = new DataGrid();
		dg.setRows(p.getList());
		dg.setTotal(p.getTotalRow());
		this.renderJson(dg);
	}
}