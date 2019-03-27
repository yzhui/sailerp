package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * Powered By joffee 歌樵 
 * Web Site: http://www.joffee.net
 * Since 2008 - 2013
 *//**
*
*/
@TableBind(name="oa_kqset")
public class Kqset extends Model<Kqset> {
	private static final long serialVersionUID = -2619947374217253379L;
	public static final String tableName="oa_kqset";
	public static Kqset dao=new Kqset();
	public Kqset qryByDate(String date){
		return findFirst("select * from "+tableName+" where exctdate <=? order by id desc ",date);
	}
}