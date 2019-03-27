package com.sailmi.erp.model.oa;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/***
 * 邮箱设置
 * @author 
 *2013年8月12日
 */
@TableBind(name="oa_emailset")
public class EmailSet extends Model<EmailSet> {
	public static String tableName="oa_emailset";
	private static final long serialVersionUID = -8733069995700407680L;
	public static EmailSet dao=new EmailSet();
	/**查询个人邮箱设置*/
	public EmailSet qryByUid(String uid){
		List<EmailSet> list= dao.findByCache("oneday", "emailSet_"+uid,"select * from "+tableName+" where uid=? and companyemail=0",uid);
		if(list!=null&&list.isEmpty()==false){
			return list.get(0);
		}
		return null;
	}
	/**查询公司邮箱设置*/
	public EmailSet qryByCompanyEmail(){
		List<EmailSet> list= dao.findByCache("oneday", "emailSet_company","select * from "+tableName+" where companyemail=1");
		if(list!=null&&list.isEmpty()==false){
			return list.get(0);
		}
		return null;
	}
	/***/
	public EmailSet qryByUidNoCache(String uid) {
		List<EmailSet> list= dao.find("select * from "+tableName+" where uid=?",uid);
		if(list!=null&&list.isEmpty()==false){
			return list.get(0);
		}
		return null;
	}
	
}
