package com.sailmi.erp.ctrl.km;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.oa.ArticleCate;
import com.sailmi.jfinal.anatation.RouteBind;
/**
 * 文章类别
 * @author joffee
 *2013-3-31 上午11:14:41
 */
@RouteBind(path="articleCate")
public class ArticleCateCtrl extends AdminBaseController<ArticleCate> {
	public ArticleCateCtrl(){
		this.tableName=ArticleCate.tableName;
		modelClass=ArticleCate.class;
	}
}
