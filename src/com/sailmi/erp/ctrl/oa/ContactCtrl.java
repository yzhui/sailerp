package com.sailmi.erp.ctrl.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.crm.Contacts;
import com.sailmi.erp.model.oa.Contact;
import com.sailmi.jfinal.anatation.PowerBind;
import com.sailmi.jfinal.anatation.RouteBind;
import com.sailmi.pageModel.DataGrid;
/**
 *通信录联系人
 * @author 
 *2013年8月14日
 */
@RouteBind(path="contact")
public class ContactCtrl extends AdminBaseController<Contact> {
	public ContactCtrl() {
		this.tableName = Contact.tableName;
		modelClass = Contact.class;
	}

	@Override
	public void dataGrid() {
		Map<String,Object> filter=new HashMap<String,Object>();
		filter.put("company_id",this.getCompanyId());
		filter.put("keyword",this.getPara("keyword"));
		filter.put("type",this.getPara("type"));
		filter.put("start_date",this.getPara("start_date"));
		filter.put("end_date",this.getPara("end_date"));
		filter.put("uid",this.getPara("uid"));
		filter.put("customer_id",this.getPara("customer_id"));
		this.sortField(filter);
		Page<Contact> page = Contact.dao.pageGrid(getPageNo(), getPageSize(),filter);
		this.rendJson(true,null, "success", page);
	}
	
	@PowerBind(code="A1_1_E",funcName="编辑")
	public void save() {
		try {
			Contact po = (Contact) getModel();
			System.out.println("contact is:"+po);
			if (po == null) {
				this.rendJson(false,null, "提交数据错误！");
				return;
			}
			getId();
			String uid=this.getCurrentUserId();
			this.pullUser(po, uid);
			System.out.println("current user id is:::::::::::::::::::::::::::::::::::::"+id);
			if (StringUtils.isEmpty(id)) {
				po.set("company_id", this.getCompanyId());
				po.save();
				id=po.getStr("id");
			} else {
				po.update();
			}
			this.rendJson(true,null, "操作成功！",id);
		} catch (Exception e) {
			log.error("保存产品异常", e);
			this.rendJson(false,null, "保存数据异常！");
		}
	}
	@PowerBind(code="A1_1_E",funcName="删除")
	public void del() {
		try {
			getId();
			Contact.dao.del(id,this.getCompanyId());
			rendJson(true,null,"删除成功！",id);
		} catch (Exception e) {
			log.error("删除异常", e);
			rendJson(false,null,"删除失败！");
		}
	}
}
