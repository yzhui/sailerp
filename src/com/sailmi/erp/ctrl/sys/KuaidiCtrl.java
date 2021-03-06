package com.sailmi.erp.ctrl.sys;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.sys.Kuaidi;
import com.sailmi.jfinal.anatation.RouteBind;
import com.sailmi.pageModel.DataGrid;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*Kuaidi
*/
@RouteBind(path = "kuaidi")
public class KuaidiCtrl extends AdminBaseController<Kuaidi> {
	public KuaidiCtrl() {
		this.tableName = Kuaidi.tableName;
		modelClass = Kuaidi.class;
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
					if(q.endsWith("start")){
						where.append(q.replace("start",""));
						where.append(" >=? ");
					}else if(q.endsWith("end")){
						where.append(q.replace("end",""));
						where.append(" <=? ");
					}else{
						where.append(q);
						where.append(" =? ");
					}
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
		Page<Record> p = Db.paginate(this.getParaToInt("page", 1), this.getParaToInt("rows", 20), "select t.ID,t.CNAME,t.HOTLINE,t.NETTEL,t.KDYNAME,t.KDYTEL, 1 ", "from " + this.tableName+ " t where 1=1 " + where.toString(), param.toArray());
		DataGrid dg = new DataGrid();
		dg.setRows(p.getList());
		dg.setTotal(p.getTotalRow());
		this.renderJson(dg);
	}
}