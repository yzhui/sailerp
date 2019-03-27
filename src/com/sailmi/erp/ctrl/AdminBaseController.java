package com.sailmi.erp.ctrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.gson.util.Tools;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jfinal.token.TokenManager;
import com.sailmi.ctrl.BaseController;
import com.sailmi.erp.model.sso.User;
import com.sailmi.jfinal.TypeConverter;
import com.sailmi.pageModel.DataGrid;
import com.sailmi.pageModel.TreeNode;
import com.sailmi.vo.JqFilters;

/**
 * 后台管理基础Controller 只提供qryOp save del dataGrid方法
 * @author  2012-9-3 下午10:37:28
 */
public abstract class AdminBaseController<M extends Model<M>> extends BaseController<M> {
	protected String tableName;
	/**yyyy-MM-dd HH:mm:ss 日期时间*/
	protected static final SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**yyyy-MM-dd 日期*/
	protected static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	/**yyyy年*/
	protected static final SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
	/**MM月*/
	protected static final SimpleDateFormat monthFormat=new SimpleDateFormat("MM");
	/**dd 日*/
	protected static final SimpleDateFormat dayFormat=new SimpleDateFormat("dd");
	/**HH:mm:ss 时间*/
	protected static final SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
	/**HH:mm 小时分钟*/
	protected static final SimpleDateFormat hmFormat=new SimpleDateFormat("HH:mm");
	/**EEE 星期*/
	protected static final SimpleDateFormat wdFormat=new SimpleDateFormat("EEE");
	/**token超时时间600s*/
	protected int tokenTout=600; 

	protected int getPageNo(){
		return this.getParaToInt("page", 1);
	}
	protected int getPageSize(){
		return this.getParaToInt("rows",10);
	}
	/***
	 * 组合查询条件 支持 >,>= ,<,<=,!=,=,in,Nin,like, Llike,Rlike,Nlike,NLlike,NRlike
	 * @param where
	 * @param param
	 */
	protected void qryField(StringBuffer where,List<Object> param){
		String qryField=this.getPara("qryField");//查询字段 以逗号分隔
		if(StringUtils.isNotEmpty(qryField)){
			String[] k=qryField.split(",");
			for(String q:k){
				Object t=this.getPara(q);
				if(t!=null&&StringUtils.isNotEmpty(t.toString())){
					if(q.toLowerCase().contains("date")){
						try{
							t=dateFormat.parse(t.toString());
							t=new java.sql.Date(((java.util.Date)t).getTime());
						}catch(Exception e){}
					}
					if(q.toLowerCase().contains("time")){
						try{
							t=hmFormat.parse(t.toString());
							t=new java.sql.Date(((java.util.Date)t).getTime());
						}catch(Exception e){}
					}
					String[] pp=q.split("\\$");
					if(pp.length>2){
						where.append(" "+pp[1]+" ");
					}else{
						where.append(" and ");
					}
					where.append(pp[0]);
					/**生成对应字段 START*/
					if(q.endsWith("$ge")){
						where.append(" >=? ");
					}else if(q.endsWith("$gt")){
						where.append(" >? ");
					}else if(q.endsWith("$le")){
						where.append(" <=? ");
					}else if(q.endsWith("$lt")){
						where.append(" <? ");
					}else if(q.endsWith("$Nlike")||q.endsWith("$NLlike")||q.endsWith("$NRlike")){
						where.append("not like ? ");
					}else if(q.endsWith("$like")||q.endsWith("$Llike")||q.endsWith("$Rlike")){
						where.append(" like ? ");
					}else if(q.endsWith("$ne")){
						where.append(" !=? ");
					}else if(q.endsWith("$in")){
						String val=this.getPara(q);
						if(StringUtils.isNotEmpty(val)){
							where.append(" in ( ");
							String[] vs=val.split(",");
							for(int i=0;i<vs.length;i++){
								where.append("?");
								if(i<vs.length-1){
									where.append(",");
								}
								param.add(vs[i]);
							}
							where.append(" ) ");
						}
					}else if(q.endsWith("$Nin")){
						String val=this.getPara(q);
						if(StringUtils.isNotEmpty(val)){
							where.append(" not in ( '");
							String[] vs=val.split(",");
							for(int i=0;i<vs.length;i++){
								where.append("?");
								if(i<vs.length-1){
									where.append(",");
								}
								param.add(vs[i]);
							}
							where.append("' ) ");
						}
					}else{
						where.append(" =? ");
					}
					/**生成对应字段 END*/
					/**生成对应值 START*/
					if(q.endsWith("$like")){
						param.add("%"+t+"%");
					}else if(q.endsWith("$Llike")){
						param.add("%"+t);
					}else if(q.endsWith("$Rlike")){
						param.add(t+"%");
					}else  if(q.endsWith("$Nlike")){
						param.add("%"+t+"%");
					}else if(q.endsWith("$in")){
					}else if(q.endsWith("$Nin")){
					}else{
						param.add(t);
					}
					/** END*/
				}
			}
		}
	}

	/***
	 * <pre>
	 * jqGrid 查询
	 *  eq>等于 ne>不等 bw>开始于 bn>不开始于 ew>结束于 en>不结束于 cn>包含 nc>不包含 nu>不存在 nn>存在 in>属于 ni>不属于
	 *  rows:10
	 *  page:1
	 * sidx: //排序字段
	 * sord:asc
	 * filters:{"groupOp":"OR","rules":[{"field":"myac","op":"eq","data":""},{"field":"myac","op":"eq","data":""}]}
	 * searchField: 搜索字段
	 * searchString: 搜索内容
	 * searchOper:
	 * </pre>
	 * @param where
	 * @param parame
	 */
	protected void jqFilters(StringBuffer where, List<Object> parame) {
		String filtersStr = this.getPara("filters");
		Boolean _search=this.getParaToBoolean("_search");
		if(_search!=null&&_search==false){
			String searchField=this.getPara("searchField");
			String searchString=this.getPara("searchString");
			String searchOper=this.getPara("searchOper");
			if(StringUtils.isNotEmpty(searchField)&&StringUtils.isNotEmpty(searchOper)){
				where.append(" and ");
				where.append(searchField);
				where.append(" ");
				where.append(searchOper);
				where.append(" ? ");
				try{
					Date date=dateFormat.parse(searchString);
					parame.add(date);
				}catch(Exception e){
					parame.add(searchString);
				}
			}
			return;
		}
		if (StringUtils.isNotEmpty(filtersStr)) {
			JqFilters filters=(JqFilters)JSON.parseObject(filtersStr,JqFilters.class);
			filters.doFilter(filters,where,parame);
		}
	}
	/**
	 * 字段排序
	 * @param where
	 * @return boolean true:没有字段排序
	 */
	protected boolean sortField(StringBuffer where) {
		String sortName = this.getPara("sidx");
		String sortOrder = this.getPara("sort", "desc");
		if (StringUtils.isNotEmpty(sortName)) {
			where.append(" order by ");
			where.append(sortName);
			where.append(" ");
			where.append(sortOrder);
		}
		return sortName != null;
	}
	/**
	 * 给map传递 _sortField,_sort
	 * @param parame
	 */
	protected void sortField(Map<String,Object>parame){
		String sortField = this.getPara("sidx");
		String sort = this.getPara("sort", "desc");
		parame.put("_sortField", sortField);
		parame.put("_sort", sort);
	}
	/**
	 * 设置操作人员id及操作时间
	 * @param m
	 * @param uid
	 */
	protected void pullUser(Model<M> m,String uid) {
		Date date=new Date();
		this.getId();
		String now=dateTimeFormat.format(date);
		if (StringUtils.isNotEmpty(id)) {
				m.put("update_datetime",now);//修改时间
				m.put("updater_id",uid);//修改人
		} else {
			m.put("creater_id",uid);//创建人
			m.put("create_datetime",now);//创建时间
		}
	}
	protected Model<M> get(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		try {
			Model<M>	model_ = modelClass.newInstance();
			return model_.findById(id);
		} catch (Exception e) {log.error("查询Model异常，id："+id,e);}
		return null;
	}

	/**查询Model返回json*/
	public void qryOp(){
		id=this.getPara(0);
		if(StringUtils.isEmpty(id)){
			id=this.getPara("id");
		}
		Model<M> m=get(id);
		if(m!=null)
			this.rendJson(true,null,"",m);
		else
			this.rendJson(false,null,"记录不存在！");
	}
	@SuppressWarnings("unchecked")
	protected M getModel() throws Exception {
		return (M)getModel2(modelClass);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Model getModel2(Class clz) throws Exception  {
		HttpServletRequest request = this.getRequest();
		try{
			Model model_ = (Model) clz.newInstance();
			Table tableInfo = TableMapping.me().getTable(clz);
			model_.getAttrNames();
			Enumeration<String> attrNames = request.getParameterNames();
			System.out.println("attr names is:"+attrNames);
			while(attrNames.hasMoreElements()) {
				String attr = attrNames.nextElement();
				System.out.println("attr name is:"+attr);
				Class<?> colType = null;
				if (tableInfo.hasColumnLabel(attr.toLowerCase()))
					colType = tableInfo.getColumnType(attr.toLowerCase());
				if (tableInfo.hasColumnLabel(attr.toUpperCase())) {
					colType = tableInfo.getColumnType(attr.toUpperCase());
				}
				if (colType != null) {
					String value = request.getParameter(attr);
					model_.set(attr.toLowerCase(),
							value != null ? TypeConverter.convert(colType, value): null);
				}
			}
			/**从request流中取json*/
			if(model_.getAttrValues()==null||model_.getAttrValues().length==0){
				String json = Tools.inputStream2String(this.getRequest().getInputStream());
				model_=JSON.parseObject(json,model_.getClass());
			}
			return model_;
		}catch(Exception e){
			throw e;
		}
	}
	/**组织成树
	 * 
	 * @param id
	 * @param list
	 * @param list_tree
	 */
	protected void dotree(String id,List<Record> list,List<Record> list_tree,int level,boolean fill){
		for(Record r:list){
			String id_=r.getStr("id");
			String pid=r.getStr("pid");
			if(id==pid||(id!=null&&id.equals(pid))||(id==null&&"".equals(pid))){
				if(level>0){
					StringBuffer sb=new StringBuffer();
					if(fill){
						for(int i=1;i<level;i++){
							sb.append("&nbsp;&nbsp;");
						}
						if(level>0){
							sb.append("├--");
						}
					}
					r.set("name", sb+r.getStr("name"));
				}
				list_tree.add(r);
				dotree(id_,list,list_tree,level+1,fill);
			}
		}
	}
	
	public void dataGrid(){
		List<Object> param = new ArrayList<Object>();
		StringBuffer where = new StringBuffer();
		/** 添加查询字段条件*/
		String sortName = this.getPara("sort");
		String sortOrder = this.getPara("order", "desc");
		String qryField=this.getPara("qryField");//查询字段 以逗号分隔
		if(qryField!=null&&!"".equals(qryField)){
			String[] k=qryField.split(",");
			for(String q:k){
				Object t=this.getPara(q);
				if(t!=null&&!"".equals(t)){
					where.append(" and ");
					if(q.endsWith("start")){
						where.append(q.replace("start",""));
						where.append(" >=? ");
					}else if(q.endsWith("end")){
						where.append(q.replace("end",""));
						where.append(" <=? ");
					}else{
						where.append(q);
						where.append(" =? ");
					}
					param.add(t);
				}
			}
		}
		if (sortName != null) {
			where.append(" order by ");
			where.append(sortName);
			where.append(" ");
			where.append(sortOrder);
		}
		Page<Record> p = Db.paginate(this.getParaToInt("page", 1), this.getParaToInt("rows", 20), "select * ", "from " + this.tableName+ " where 1=1 " + where.toString(), param.toArray());
		DataGrid dg = new DataGrid();
		dg.setRows(p.getList());
		dg.setTotal(p.getTotalRow());
		this.renderJson(dg);
	};
	/**树形表格*/
	public void treeDataGrid() {
		DataGrid dg=new DataGrid();
		List<Record> list= Db.find("select a.*,p.cname pcname from "+this.tableName+" a left join "+this.tableName+" p on p.id=a.pid");
		List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
		for(Record r:list){
			Map<String,Object> d=new HashMap<String,Object>();
			for(String col:r.getColumnNames()){
				d.put(col,r.get(col));
			}
			Long pid=r.getLong("pid");
			if(pid!=null&&pid!=0)
			d.put("_parentId",pid);//tree datagrid 需要使用此字段作为父节点关联
			list1.add(d);
		}
		dg.setRows(list1);
		dg.setTotal(list1.size());
		this.renderJson(dg);
	}
	/**选择树*/
	public void combotree() {
		List<Record> dataList = Db.find("select id,cname,pid from "+this.tableName);
		List<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode rootNode = new TreeNode();
		rootNode.setId(0L);
		rootNode.setText("");
		rootNode.setIconCls("icon-home");
		rootNode.setCanChk(false);
		fillTree(dataList, rootNode,"cname",true,null);
		list.add(rootNode);
		this.renderJson(list);
	}
	public void combobox(){
		List<Record> list=Db.find("select * from "+this.tableName);
		this.renderJson(list);
	}

	/**
	 * 通过pid字段关联
	 * @param dataList 集合
	 * @param pnode 父节点
	 * @param cname 节点名称字段
	 */
	protected void fillTree(List<Record> dataList,TreeNode pnode,String cname,Boolean canChk,List<Long>ckidList) {
		List<TreeNode> childelist = new ArrayList<TreeNode>();
		for (Record m : dataList) {
			Long pid_ = m.getLong("pid");
			if (pnode.getId() == pid_) {
				Long id = m.getLong("id");
				TreeNode nodechild = new TreeNode();
				nodechild.setId(id);
				nodechild.setText(m.getStr(cname));
				nodechild.setCanChk(canChk);
				childelist.add(nodechild);
				if(ckidList!=null&&ArrayUtils.contains(ckidList.toArray(), id))
				nodechild.setChecked(true);
				fillTree(dataList, nodechild,cname,canChk,ckidList);
			}
		}
		if (childelist.isEmpty() == false && childelist.size() > 0) {
			pnode.setChildren(childelist);
		}
	}
	public void save() {
		try {
			Model<M> m = this.getModel();
			String id = m.getStr("ID");
			Long tokenl=this.getParaToLong("token");
			if(tokenl!=null){
				if(!TokenManager.validateToken(this, tokenName)){
					this.rendJson_(false, "会话令牌错误！",id,new Random().nextLong());
					return;
				}
			}
			String uid=this.getCurrentUserId();
			Date now=new Date();
			if (id != null) {
				m.set("update_datetime",now);//修改时间
				m.set("updater_id",uid);//修改人
				m.update();
			} else {
				m.set("creater_id",uid);//创建人
				m.set("create_datetime",now);//创建时间
				m.save();
			}
			id = m.getStr("ID");
			rendJson_(true, "保存成功！", id,new Random().nextLong());
		} catch (Exception e) {
			log.error("保存异常", e);
			rendJson_(false, "保存异常！");
		}
	}

	/**是否有子集 针对树形数据*/
	private boolean hashChild(Long id){
		try{
		Table tableInfo = TableMapping.me().getTable(modelClass);
		if(tableInfo.hasColumnLabel("pid")){
			List<Record> list=Db.find("select * from "+this.tableName+" where pid=?", id);
			return list!=null&&list.isEmpty()==false;
		}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	public void del() {
		try {
			Long id = this.getParaToLong(0);
			if (id == null||id==0L){
				String ids = this.getPara("ids");
				if (ids != null && !"".equals(ids)) {
					Db.update("delete from " + this.tableName + " where id in ("+ ids+ ")");
				} else {
					id = this.getParaToLong("ID");
				}
			}else{
				po = get(id);
				String uid=this.getCurrentUserId();
				String creater=po.getStr("creater");
				if(creater!=null&&uid!=creater){
					rendJson_(false, "您无权限操作他人所创建的信息！");
					return;
				}
				if(hashChild(id)==false){
					Db.deleteById(this.tableName, id);
				}else{
					rendJson_(false, "此数据有相关联的子集数据，请先删除子集数据项！");
					return;
				}
			}
			rendJson_(true, "删除成功！",id);
		} catch (Exception e) {
			log.error("删除异常", e);
			rendJson_(false, "删除异常！");
		}
	}
	public Model<M> get(Long id) {
		Model<M> model_;
		try {
			model_ = modelClass.newInstance();
			return model_.findById(id);//Db.findById(this.tableName, id);
		} catch (Exception e) {
		}
		return null;
	}

	public void view() {
		Long id= this.getParaToLong(0,0L);
		if(id!=null&&id!=0L)
			this.setAttr("po",get(id));
		else
			this.setAttr("po",new Record());
	}
	public void edit() {
		try {
			Long id = this.getParaToLong(0, 0L);
			if (id != 0L) {
				po = get(id);
//				Long uid=this.getCurrentUserId();
//				Long creater=po.getLong("creater");
				/*if(creater!=null&&uid!=creater&&creater.equals(uid)){
					this.renderText("<h1><font color=red>您无权限操作他人所创建的信息！</font></h1>");
					return;
				}*/
			}else{
				setAttr("now",new Date());
			}
			TokenManager.createToken(this, tokenName, tokenTout);
			 this.setAttr("po",po);
		} catch (Exception e) {
			log.error("编辑异常", e);
		}
	}
	/** 获取当前系统操作人 */
	protected User getCurrentUser() {
		return User.dao.qryLoginUser(getCurrentUserId());
	}
}
