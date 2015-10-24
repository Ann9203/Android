package com.jczb.checkpoint.db;

/**
 * 安标证书从表——RFID设备表
 * @author wlc
 * @date 2015-3-20
 */
public class AppDownCong {
	//表名称
	public static final String TABLENAME="t_app_down_cong";
	//表字段
	public interface FIELDS{
		public static final String ID = "id";
		public static final String NAME="name";
		/**
		 * 创建日期
		 */
		public static final String BEGINDATE="begin_date";
		/**
		 * epc编号
		 */
		public static final String EPCCODE="epc_code";
		/**
		 * tid编号
		 */
		public static final String TIDCODE="tid_code";
		/**
		 * 发放日期
		 */
		public static final String PUTDATE="put_date";
		/**
		 * 发放企业
		 */
		public static final String PUTENTERPRISE="put_enterprise";
		/**
		 * 发放产品名称
		 */
		public static final String PUTPRODUCTNAME="put_product_name";
		/**
		 * 发放产品规格型号
		 */
		public static final String PUTPRODUCTMODEL="put_product_model";
		/**
		 * 关联安标编号
		 */
		public static final String ANBIAOCODE="anbiao_code";
		/**
		 * 状态
		 */
		public static final String STATE= "state";
	}
	//表操作
	public interface OPERATION{
		//创建表
		public static final String CREATETABLE = "create table if not exists "
				+ TABLENAME + "("
				+ FIELDS.ID + " integer primary key autoincrement,"
				+ FIELDS.NAME + " varchar(50),"
				+ FIELDS.BEGINDATE + " varchar(50),"
				+ FIELDS.EPCCODE + " varchar(50),"
				+ FIELDS.TIDCODE + " varchar(50),"
				+ FIELDS.PUTDATE + " varchar(50),"
				+ FIELDS.PUTENTERPRISE + " varchar(50),"
				+ FIELDS.PUTPRODUCTNAME + " varchar(50),"
				+ FIELDS.PUTPRODUCTMODEL + " varchar(50),"
				+ FIELDS.ANBIAOCODE + " varchar(50),"
				+ FIELDS.STATE + " varchar(50))";
		//删除表
		public static final String DROPTABLE = "drop table if exists " + TABLENAME;
		//向表中插入一条记录
		public static final String INSERT = "insert into "
				+ TABLENAME + "("
				+ FIELDS.NAME + ","
				+ FIELDS.BEGINDATE + ","
				+ FIELDS.EPCCODE + ","
				+ FIELDS.TIDCODE + ","
				+ FIELDS.PUTDATE + ","
				+ FIELDS.PUTENTERPRISE + ","
				+ FIELDS.PUTPRODUCTNAME + ","
				+ FIELDS.PUTPRODUCTMODEL + ","
				+ FIELDS.ANBIAOCODE + ","
				+ FIELDS.STATE + ") "
				+ "values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		//删除一条记录
		public static final String DELETE = "delete from " + TABLENAME + " where %s";
		//查询语句
		public static final String QUERY = "select * from " + TABLENAME + " where %s";
		//根据id修改一条记录
		public static final String UPDATE = "update " + TABLENAME + " set "
				+ FIELDS.NAME + " = '%s',"
				+ FIELDS.BEGINDATE + " = '%s',"
				+ FIELDS.EPCCODE + " = '%s',"
				+ FIELDS.TIDCODE + " = '%s',"
				+ FIELDS.PUTDATE + " = '%s',"
				+ FIELDS.PUTENTERPRISE + " = '%s',"
				+ FIELDS.PUTPRODUCTNAME + " = '%s',"
				+ FIELDS.PUTPRODUCTMODEL + " = '%s',"
				+ FIELDS.ANBIAOCODE + " = '%s',"
				+ FIELDS.STATE + " = '%s'"
				+ " where " + FIELDS.ID + " =%s";
	}
}
