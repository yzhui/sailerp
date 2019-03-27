package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 消息
 * @author 歌樵
 * 2014年9月19日
 */
@TableBind(name="oa_message")
public class Message extends Model<Message> {
	private static final long serialVersionUID = -5904205242847326222L;
	public static final String tableName="oa_message";
	public static Message dao=new Message();
	
}
