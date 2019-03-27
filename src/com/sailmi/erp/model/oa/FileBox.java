package com.sailmi.erp.model.oa;

import com.jfinal.plugin.activerecord.Model;
import com.sailmi.jfinal.anatation.TableBind;
/**
 * 文件站
 * @author 
 *2013年8月12日
 */
@TableBind(name="oa_filebox")
public class FileBox extends Model<FileBox> {
	public static String tableName="oa_filebox";
	/**1MB=1024KB=1048576B*/
	public static float mb=1048576;
	private static final long serialVersionUID = -3857562245294137140L;
	public static final FileBox dao=new FileBox();
}
