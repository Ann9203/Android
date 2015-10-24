package com.jczb.checkpoint.db;

public class PhotoFile {

	//表名称
	public static final String TABLENAME = "t_photofile";
	//表字段
	public interface FIELDS{
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String BEGINDATE = "begin_date";
		/**
		 * 关联表ID
		 */
		public static final String MAINID = "association_table_id";
		/**
		 * 类型
		 */
		public static final String MAINSTYPE = "image_type";
		/**
		 * 二进制图片
		 */
		public static final String IMAGE = "binary_image";
		/**
		 * 图片名称
		 */
		public static final String FILENAME = "image_name";
		/**
		 * 图片后缀类型
		 */
		public static final String FILESUFFIX = "image_suffix";
	}
	//操作
	public interface OPERATION{
		//创建表
		public static final String CREATETABLE = "create table if not exists "
				+ TABLENAME + "("
				+ FIELDS.ID + " integer primary key autoincrement,"
				+ FIELDS.NAME + " varchar(50),"
				+ FIELDS.BEGINDATE + " varchar(50),"
				+ FIELDS.MAINID + " integer,"
				+ FIELDS.MAINSTYPE + " varchar(50),"
				+ FIELDS.FILENAME + " varchar(50),"
				+ FIELDS.FILESUFFIX + " varchar(20),"
				+ FIELDS.IMAGE + " binary(10000)" + ")";
		//删除表
		public static final String DROPTABLE = "drop table if exists " + TABLENAME;
		//向表中插入一条记录
		public static final String INSERT = "insert into "
				+ TABLENAME + "("
				+ FIELDS.NAME + ","
				+ FIELDS.BEGINDATE + ","
				+ FIELDS.MAINID + ","
				+ FIELDS.MAINSTYPE + ","
				+ FIELDS.FILENAME + ","
				+ FIELDS.FILESUFFIX + ","
				+ FIELDS.IMAGE + ")"
				+ "values('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
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
				+ FIELDS.MAINID + " = '%s',"
				+ FIELDS.MAINSTYPE + "= '%s',"
				+ FIELDS.FILENAME + "= '%s',"
				+ FIELDS.FILESUFFIX + "= '%s',"
				+ FIELDS.IMAGE + "= '%s'"
				+ " where " + FIELDS.ID + "=%s";
	}
	
}
