package com.sailmi.erp.model.oa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.sailmi.erp.model.sso.Person;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 联系人
 * @author 
 *2013年8月14日
 */
@TableBind(name="oa_contact")
public class Contact extends Model<Contact> {
	private static final long serialVersionUID = -2225431510883615908L;
	public  static Contact dao=new Contact();
	public static final String tableName="oa_contact";
	
	public Page<Contact> pageGrid(int pageNo, int pageSize,Map<String, Object> filter) {
		String userView=Person.tableName;
		List<Object> parame=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer(" from ");
		sql.append(tableName);
		sql.append(" t left join ");
		sql.append(userView);
		sql.append(" c on c.id=t.creater_id left join ");
		sql.append(userView);
		sql.append(" u on u.id=t.updater_id ");
		String keyword=(String)filter.get("keyword");
		if(StringUtils.isNotEmpty(keyword)){
			sql.append(" and (t.uname like ?  or t.mobile like ? or t.worktel like ?)");
			keyword="%"+keyword+"%";
			parame.add(keyword);
			parame.add(keyword);
			parame.add(keyword);
			parame.add(keyword);
			parame.add(keyword);
		}
		String sortField=(String)filter.get("_sortField");
		if(StringUtils.isNotEmpty(sortField)){
			String sort=(String)filter.get("_sort");
			sql.append(" order by ");
			sql.append(sortField);
			sql.append(" ");
			sql.append(sort);
		}
		return this.paginate(pageNo, pageSize, "select t.*  ",sql.toString(),parame.toArray());
	}
}
