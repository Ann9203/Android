package com.jczb.checkpoint.db;

import com.jczb.checkpoint.db.Users.FIELDS;

/**
 * 下载的安标证书表
 * @author wlc
 * @date 2015-3-20
 */
public class AppDown {
	//表名称
	public static final String TABLENAME="t_app_down";
	//表字段
	public interface FIELDS{
		public static final String ID = "id";
		public static final String NAME = "name";
		
		/**
		 * 安标证书记录创建日期
		 */
		public static final String BEGINDATE = "begin_date";
		/**
		 * 安标证书编码
		 */
		public static final String ANBIAOCODE = "anbiao_code";
		/**
		 * 发证日期
		 */
		public static final String PROVIDEDATE = "provide_date";
		/**
		 * 有效期
		 */
		public static final String TERMVALIDITY = "term_validity";
		/**
		 * 持证人
		 */
		public static final String HOLDERPERSON = "holder_person";
		/**
		 * 注册地址
		 */
		public static final String REGISTEREDADDRESS = "registered_address";
		/**
		 * 生产单位
		 */
		public static final String PRODUCTIONUNITID = "production_unit_id";
		/**
		 * 生产地址
		 */
		public static final String PRODUCTIONADDRESS = "production_address";
		/**
		 * 产品名称
		 */
		public static final String PRODUCTNAME = "product_name";
		/**
		 * 规格型号
		 */
		public static final String PRODUCTMODEL = "product_model";
		/**
		 * 标准和要求
		 */
		public static final String STANDARD = "standard";
		/**
		 * 适用范围
		 */
		public static final String SCOPEAPPLICATION = "scope_application";
		/**
		 * 备注
		 */
		public static final String REMARK = "remark";
		/**
		 * 状态
		 */
		public static final String STATE = "state";
	}
	//表操作
	public interface OPERATION{
		//创建表
		public static final String CREATETABLE = "create table if not exists "
				+ TABLENAME + "("
				+ FIELDS.ID + " integer primary key autoincrement,"
				+ FIELDS.NAME + " varchar(50),"
				+ FIELDS.BEGINDATE + " varchar(50),"
				+ FIELDS.PROVIDEDATE + " varchar(50),"
				+ FIELDS.TERMVALIDITY + " varchar(50),"
				+ FIELDS.HOLDERPERSON + " varchar(50),"
				+ FIELDS.REGISTEREDADDRESS + " varchar(100),"
				+ FIELDS.PRODUCTIONUNITID + " int,"
				+ FIELDS.PRODUCTIONADDRESS + " varchar(100),"
				+ FIELDS.PRODUCTNAME + " varchar(50),"
				+ FIELDS.PRODUCTMODEL + " varchar(50),"
				+ FIELDS.STANDARD + " varchar(200),"
				+ FIELDS.SCOPEAPPLICATION + " varchar(100),"
				+ FIELDS.REMARK + " varchar(200),"
				+ FIELDS.STATE + " varchar(2),"
				+ FIELDS.ANBIAOCODE + " varchar(50))";
		//删除表
		public static final String DROPTABLE = "drop table if exists " + TABLENAME;
		//向表中插入一条记录
		public static final String INSERT ="insert into "
				+ TABLENAME + "("
				+ FIELDS.BEGINDATE + ","
				+ FIELDS.ANBIAOCODE + ","
				+ FIELDS.PROVIDEDATE + ","
				+ FIELDS.TERMVALIDITY + ","
				+ FIELDS.HOLDERPERSON + ","
				+ FIELDS.REGISTEREDADDRESS + ","
				+ FIELDS.PRODUCTIONUNITID + ","
				+ FIELDS.PRODUCTIONADDRESS + ","
				+ FIELDS.PRODUCTNAME + ","
				+ FIELDS.PRODUCTMODEL + ","
				+ FIELDS.STANDARD + ","
				+ FIELDS.SCOPEAPPLICATION + ","
				+ FIELDS.REMARK + ","
				+ FIELDS.STATE + ","
				+ FIELDS.NAME + ")"
				+ "values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		//删除一条记录
		public static final String DELETE = "delete from " + TABLENAME + " where %s";
		//查询记录
		public static final String QUERY = "select * from " + TABLENAME + " where %s";
		
		//查询出全部语句，从小到大排序
		public static final String QUERYALL = "select * from " + TABLENAME + " order by id asc";
		
		//根据id修改一条记录
		public static final String UPDATE = "update " 
				+ TABLENAME + " set "
				+ FIELDS.NAME + " = '%s',"
				+ FIELDS.BEGINDATE + " = '%s',"
				+ FIELDS.ANBIAOCODE + " = '%s',"
				+ FIELDS.PROVIDEDATE + " = '%s',"
				+ FIELDS.TERMVALIDITY + " = '%s',"
				+ FIELDS.HOLDERPERSON + " = '%s',"
				+ FIELDS.REGISTEREDADDRESS + " = '%s',"
				+ FIELDS.PRODUCTIONUNITID + " = '%s',"
				+ FIELDS.PRODUCTIONADDRESS + " = '%s',"
				+ FIELDS.PRODUCTNAME + " = '%s',"
				+ FIELDS.PRODUCTMODEL + " = '%s',"
				+ FIELDS.STANDARD + " = '%s',"
				+ FIELDS.SCOPEAPPLICATION + " = '%s',"
				+ FIELDS.REMARK + " = '%s',"
				+ FIELDS.STATE + " = '%s'"
				+ " where " + FIELDS.ID +" = %s";
	}
}
