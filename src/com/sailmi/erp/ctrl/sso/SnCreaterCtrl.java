package com.sailmi.erp.ctrl.sso;

import org.apache.commons.lang3.StringUtils;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.sso.SnCreater;
import com.sailmi.jfinal.anatation.PowerBind;
import com.sailmi.jfinal.anatation.RouteBind;
/**
 * 序号生成器
 * @author 歌樵
 * 2014年9月15日
 */
@RouteBind(path="snCreater",sys="设置",model="序号规则")
public class SnCreaterCtrl extends AdminBaseController<SnCreater> {
	public SnCreaterCtrl() {
		this.modelClass=SnCreater.class;
	}
	public void list(){
		this.rendJson(true, null, "",SnCreater.dao.list(this.getCompanyId()));
	}
	public void qryOp() {
		getId();
		SnCreater m = SnCreater.dao.findById(id, this.getCompanyId());
		if (m != null)
			this.rendJson(true,null, "", m);
		else
			this.rendJson(false,null, "记录不存在！");
	}
	@PowerBind(code="A11_1_E",funcName="编辑")
	public void save() {
		try {
			SnCreater po = (SnCreater) getModel();
			if (po == null) {
				this.rendJson(false,null, "提交数据错误！");
				return;
			}
			getId();
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
}
