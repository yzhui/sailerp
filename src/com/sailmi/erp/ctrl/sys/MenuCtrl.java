package com.sailmi.erp.ctrl.sys;

import com.jfinal.plugin.ehcache.CacheKit;
import com.sailmi.erp.ctrl.AdminBaseController;
import com.sailmi.erp.model.sys.Menu;
import com.sailmi.jfinal.anatation.RouteBind;

/**
 * 菜单
 * 
 * @author joffee 2013-3-31 上午11:20:05
 */
@RouteBind(path = "menu")
public class MenuCtrl extends AdminBaseController<Menu> {
	public MenuCtrl() {
		this.tableName = Menu.tableName;
		modelClass = Menu.class;
	}

	public void save() {
		CacheKit.removeAll("oneday");
	}

	
}
