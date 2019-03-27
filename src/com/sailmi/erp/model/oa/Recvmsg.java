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
@TableBind(name="oa_recvmsg")
public class Recvmsg extends Model<Recvmsg> {
	public static final String tableName="oa_recvmsg";
	public static Recvmsg dao=new Recvmsg();
}