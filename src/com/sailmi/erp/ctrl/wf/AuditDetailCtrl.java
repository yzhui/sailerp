package com.sailmi.erp.ctrl.wf;

import java.util.Date;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.wf.AuditDetail;
import com.sailmi.jfinal.anatation.RouteBind;

/**
 * 审核记录信息
 * 
 * @author 歌樵 2014年10月10日
 */
@RouteBind(path = "auditDetail")
public class AuditDetailCtrl extends AdminBaseController<AuditDetail> {
	public AuditDetailCtrl(){
		this.modelClass=AuditDetail.class;
	}
	public void list() {
		this.rendJson(true, null, "",AuditDetail.dao.list(this.getId(),this.getCompanyId()));
	}
	public void save(){
		try{
			AuditDetail po=(AuditDetail)this.getModel();
			po.set("auditor_id",this.getCurrentUserId());
			po.set("audit_datetime",dateFormat.format(new Date()));
			String tablename=this.getPara("table");
			AuditDetail.dao.save(po,tablename);
			this.rendJson(true, null, "保存审核结果成功！");
		}catch(Exception e){
			log.error(e);
			this.rendJson(false, null, "保存审核结果异常！");
		}
	}
}
