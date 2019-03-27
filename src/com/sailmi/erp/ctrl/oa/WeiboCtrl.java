package com.sailmi.erp.ctrl.oa;

import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.oa.Weibo;
import com.sailmi.jfinal.anatation.RouteBind;

/**
 * 微博
 * @author joffee 歌樵
 *2013-6-16
 */
@RouteBind(path = "Weibo")
public class WeiboCtrl extends AdminBaseController<Weibo> {
	public  WeiboCtrl() {
		this.tableName = Weibo.tableName;
		modelClass = Weibo.class;
	}
}