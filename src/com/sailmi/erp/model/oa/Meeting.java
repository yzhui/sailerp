package com.sailmi.erp.model.oa;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.sailmi.jfinal.anatation.TableBind;

/**
 * 会议
 * @author  2013年8月9日
 */
@TableBind(name="oa_meeting")
public class Meeting extends Model<Meeting> {
	public static String tableName="oa_meeting";
	private static final long serialVersionUID = 1576312572744127276L;
	public static Meeting dao = new Meeting();
	/**
	 * 会议参与人员
	 * @param id
	 * @return
	 */
	public List<Record> shareList(Long id){
		return Db.find("select e.fullname,e.userno,p.cname as pname from oa_meetingjoin b,hr_employee e,sys_partment p where p.id=e.partmentid and e.id=b.uid and b.mid=? ", id);
	}
	/**发布会议
	 * 对参与人员发出站内信息
	 * @param ids 会议id
	 * @param string 发布人id
	 */
	public void publish(String ids,String string){
		Db.update("update " + tableName + " set stat=1,fbtime=? where id in ("+ ids+ ")",new Date());
		/**发布会议通知与会人员*/
		
	}
}
