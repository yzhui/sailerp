package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*
*/
@TableBind(name="oa_kaoqing")
public class Kaoqing extends Model<Kaoqing> {
	private static final long serialVersionUID = -4228949930994021532L;
	public static final String tableName="oa_kaoqing";
	public static Kaoqing dao=new Kaoqing();
	public Kaoqing qryByUidDate(String uid,String date){
		return findFirst("select * from "+tableName+" where uid=? and kqrq=?", uid,date);
	}
	public Kaoqing qryByUid(String uid){
		return findFirst("select * from "+tableName+" where uid=? order by id desc", uid);
	}
	/**
	 * 签退
	 * @param kqid 考勤记录id 
	 * @param uid 	
	 * @param date 考勤日期
	 * @param qtTime 签退时间
	 */
	public void qianTui(Long kqid,String qtTime,String ip,String beizhu){
		Db.update("update "+tableName+" set qttime=? ,qtip=? ,beizhu=? where id=? ",qtTime,ip,beizhu,kqid);
	}
}