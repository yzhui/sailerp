package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 微博
 * @author joffee 歌樵
 *2013-6-16
 */
@TableBind(name="oa_weibo")
public class Weibo extends Model<Weibo> {
	public static String tableName="oa_weibo";
	private static final long serialVersionUID = -6650644535415019610L;
	public static Weibo dao=new Weibo();
	/**赞
	 * @param id 评论id
	 */
	public void zan(Long id){
		Db.update("update "+tableName+" set zan=zan+1 where id=?", id);
	}
	/***
	 * 删除评论 只有创建人可删除
	 * @param id 微博id
	 * @param cuid 创建人id
	 */
	public void delByC(Long id,Long cuid){
		Db.update("delete from "+tableName+" where id=? and uid=?", id,cuid);
	}
}
