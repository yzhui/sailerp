package com.sailmi.erp.ctrl.oa;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.oa.DocCate;
import com.sailmi.jfinal.anatation.RouteBind;
/**
 * 文档类别
 * @author joffee
 *2013-3-31 上午11:14:41
 */
@RouteBind(path="docCate")
public class DocCateCtrl extends AdminBaseController<DocCate> {
	public DocCateCtrl(){
		this.tableName=DocCate.tableName;
		modelClass=DocCate.class;
	}
}
