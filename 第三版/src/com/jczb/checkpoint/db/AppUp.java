package com.jczb.checkpoint.db;

/**
 * 上传表--抽查管理表
 * @author wlc
 * @date 2015-3-20
 */
public class AppUp {

	//表名称
		public static final String TABLENAME = "t_app_up";
		//表字段
		public interface FIELDS{
			public static final String ID = "id";
			public static final String NAME = "Name";
			public static final String BEGINDATE = "begindate";
			/**
			 * EPC编号
			 */
			public static final String EPCCODE = "erp_code";
			/**
			 * TID编号
			 */
			public static final String TIDCODE = "tid_code";
			/**
			 * 煤矿名称
			 */
			public static final String MEKUANGINAME = "meikuang_name";
			/**
			 * 安检仪编号
			 */
			public static final String ANJIANCODE = "anjianyi_code";
			/**
			 * 抽查工作人员
			 */
			public static final String JOBNAME = "worker_name";
			/**
			 * 抽查时间
			 */
			public static final String CHECKDATE = "check_date";
			/**
			 * 异常信息
			 */
			public static final String EXCEREMARK = "exception_remark";
			/**
			 * 安标编号
			 */
			public static final String ANBIAOCODE = "anbiao_code";
			/**
			 * 抽查结果
			 */
			public static final String CHECKRESULT = "check_result";
			/**
			 * 状态
			 */
			public static final String STATE= "state";
			/**
			 * 从服务器上获取的抽查工作人员的Id
			 */
			public static final String UESRID = "userid";
			
		}
		//操作
		public interface OPERATION{
			//创建表
			public static final String CREATETABLE = "create table if not exists "
					+ TABLENAME + "("
					+ FIELDS.ID + " integer primary key autoincrement,"
					+ FIELDS.NAME + " varchar(50),"
					+ FIELDS.BEGINDATE + " varchar(50),"
					+ FIELDS.EPCCODE + " varchar(50),"
					+ FIELDS.TIDCODE + " varchar(50),"
					+ FIELDS.MEKUANGINAME + " varchar(100),"
					+ FIELDS.ANJIANCODE + " varchar(50),"
					+ FIELDS.JOBNAME + " varchar(50),"
					+ FIELDS.CHECKDATE + " varchar(50),"
					+ FIELDS.EXCEREMARK + " varchar(500),"
					+ FIELDS.ANBIAOCODE + " varchar(50)," 
					+ FIELDS.CHECKRESULT + " varchar(8)," 
					+ FIELDS.STATE + " varchar(8),"
					+ FIELDS.UESRID + " integer "
					+ ")";
			//删除表
			public static final String DROPTABLE = "drop table if exists " + TABLENAME;
			//向表中插入一条记录
			public static final String INSERT = "insert into "
					+ TABLENAME + "("
					+ FIELDS.NAME + ","
					+ FIELDS.BEGINDATE + ","
					+ FIELDS.EPCCODE + ","
					+ FIELDS.TIDCODE + ","
					+ FIELDS.MEKUANGINAME + ","
					+ FIELDS.ANJIANCODE + ","
					+ FIELDS.JOBNAME + ","
					+ FIELDS.CHECKDATE + ","
					+ FIELDS.EXCEREMARK + ","
					+ FIELDS.ANBIAOCODE + ","
					+ FIELDS.CHECKRESULT + ","
					+ FIELDS.STATE + ","
					+ FIELDS.UESRID
					+ ")"
					+ "values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
			//根据id删除一条记录
			public static final String DELETE = "delete from "
					+ TABLENAME
					+ " where %s";
			//查询语句
			public static final String QUERY = "select * from "
					+ TABLENAME
					+ " where %s";
			//根据id修改一条记录
			public static final String UPDATE = "update "
					+ TABLENAME + " set "
					+ FIELDS.NAME + " = '%s',"
					+ FIELDS.BEGINDATE + " = '%s',"
					+ FIELDS.EPCCODE + " = '%s',"
					+ FIELDS.TIDCODE + "= '%s',"
					+ FIELDS.MEKUANGINAME + "= '%s',"
					+ FIELDS.ANJIANCODE + "= '%s',"
					+ FIELDS.JOBNAME + "= '%s',"
					+ FIELDS.CHECKDATE + "= '%s',"
					+ FIELDS.EXCEREMARK + "= '%s',"
					+ FIELDS.ANBIAOCODE + "= '%s',"
					+ FIELDS.CHECKRESULT + "= '%s',"
					+ FIELDS.STATE + "= '%s',"
					+ FIELDS.UESRID + "='%s'"
					+ " where " + FIELDS.ID + "=%s";
		}
	
}
