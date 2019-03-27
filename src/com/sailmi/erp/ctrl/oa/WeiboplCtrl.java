package com.sailmi.erp.ctrl.oa;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.oa.Weibopl;
import com.sailmi.jfinal.anatation.RouteBind;

/**
 * 微博评论
 * @author joffee 歌樵
 *2013-6-16
 */
@RouteBind(path = "Weibopl")
public class WeiboplCtrl extends AdminBaseController<Weibopl> {
	public WeiboplCtrl() {
		this.tableName =Weibopl.tableName;
		modelClass = Weibopl.class;
	}
}