package com.sailmi.erp.ctrl.sys;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.sso.Parame;
import com.sailmi.jfinal.anatation.RouteBind;
import com.sailmi.pageModel.TreeNode;
/**
 * 系统参数设置
 * @author joffee
 *2013-3-31 上午11:14:41
 */
@RouteBind(path="parame")
public class ParameCtrl extends AdminBaseController<Parame> {
	public ParameCtrl(){
		this.tableName=Parame.tableName;
		modelClass=Parame.class;
	}
	/**选择树*/
	public void combotree() {
		String type=this.getPara(0);
		List<Record> dataList =Parame.dao.qry(type);
		List<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode rootNode = new TreeNode();
		rootNode.setId(dataList.get(0).getLong("pid"));
		rootNode.setText("");
		rootNode.setIconCls("icon-home");
		rootNode.setCanChk(false);
		fillTree(dataList, rootNode,"cname",true,null);
		list.add(rootNode);
		this.renderJson_(list);
	}
	public void combobox(){
		String type=this.getPara(0);
		List<Record> dataList =Parame.dao.qry(type);
		this.renderJson(dataList);
	}
}
