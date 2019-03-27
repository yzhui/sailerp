package com.sailmi.erp.ctrl.biz;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.biz.Vender;
import com.sailmi.erp.model.sso.Parame;
import com.sailmi.jfinal.anatation.RouteBind;
import com.sailmi.pageModel.DataGrid;
/**
 * 厂家
 * @author 
 *2013年8月13日
 */
@RouteBind(path="vender")
public class VenderCtrl extends AdminBaseController<Vender> {
	public VenderCtrl() {
		this.tableName = Vender.tableName;
		modelClass = Vender.class;
	}
	public void edit(){
		this.setAttr("sys_parame", Parame.dao);
		super.edit();
	}
	public void view(){
		this.setAttr("sys_parame", Parame.dao);
		super.view();
	}
	public void dataGrid() {
		List<Object> param = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		/** 添加查询字段条件*/
		String qryField=this.getPara("qryField");//查询字段 以逗号分隔
		if(qryField!=null&&!"".equals(qryField)){
			String[] k=qryField.split(",");
			for(String q:k){
				Object t=this.getPara(q);
				if(t!=null&&!"".equals(t)){
					where.append(" and ");
					where.append(q);
					where.append(" =? ");
					param.add(t);
				}
			}
		}
		String sortName = this.getPara("sort");
		String sortOrder = this.getPara("order", "desc");
		if (sortName != null) {
			where.append(" order by ");
			where.append(sortName);
			where.append(" ");
			where.append(sortOrder);
		}
		Page<Record> p = Db.paginate(this.getParaToInt("page", 1), this.getParaToInt("rows", 20), "select ID,CNAME,JCNAME,HANGYE,TYPE,LAIYUAN,PROVINCE,CITY, 1 ", "from " + this.tableName+ " where 1=1 " + where.toString(), param.toArray());
		DataGrid dg = new DataGrid();
		dg.setRows(p.getList());
		dg.setTotal(p.getTotalRow());
		this.renderJson(dg);
	}
}
