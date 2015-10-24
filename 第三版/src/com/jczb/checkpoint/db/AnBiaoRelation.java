package com.jczb.checkpoint.db;

/**
 * 安标证书关联表
 * @author wlc
 * @date 2015-3-20
 */
public class AnBiaoRelation {
	

	//表名称
	public static final String TABLENAME = "t_anbiao_relation";
	//表字段
	public interface FIELDS{
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String BEGINDATE = "begin_date";
		/**
		 * 主安标证书ID
		 */
		public static final String MAINANBIAOID = "main_anbiao_id";
		/**
		 * 关联安标ID
		 */
		public static final String VICEANBIAOID = "vice_anbiao_id";
	}
	//操作
	public interface OPERATION{
		//创建表
		public static final String CREATETABLE = "create table if not exists "
				+ TABLENAME + "("
				+ FIELDS.ID + " integer primary key autoincrement,"
				+ FIELDS.NAME + " varchar(50),"
				+ FIELDS.BEGINDATE + " varchar(50),"
				+ FIELDS.MAINANBIAOID + " int," 
				+ FIELDS.VICEANBIAOID + " varchar(50))";
		//删除表
		public static final String DROPTABLE = "drop table if exists " + TABLENAME;
		//向表中插入一条记录
		public static final String INSERT = "insert into "
				+ TABLENAME + "("
				+ FIELDS.NAME + ","
				+ FIELDS.BEGINDATE + ","
				+ FIELDS.MAINANBIAOID + ","
				+ FIELDS.VICEANBIAOID  +") "
				+ "values('%s','%s','%s', '%s')";
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
				+ FIELDS.MAINANBIAOID + " = '%s',"
				+ FIELDS.VICEANBIAOID + "= '%s'"
				+ " where " + FIELDS.ID + "=%s";
	}
}
