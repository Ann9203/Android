package com.jczb.checkpoint.db;

public class Users {

	//表名称
	public static final String TABLENAME = "t_user";
	//表字段
	public interface FIELDS{
		public static final String ID = "id";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String REALNAME = "realname";
		/**
		 * 服务器用户表ID
		 */
		public static final String SERVERID = "serverid";
	}
	//操作
	public interface OPERATION{
		//创建表
		public static final String CREATETABLE = "create table if not exists "
				+ TABLENAME + "("
				+ FIELDS.ID + " integer primary key autoincrement,"
				+ FIELDS.USERNAME + " varchar(30),"
				+ FIELDS.PASSWORD + " varchar(20),"
				+ FIELDS.REALNAME + " varchar(20),"
				+ FIELDS.SERVERID + " serverid(10))";
		//删除表
		public static final String DROPTABLE = "drop table if exists " + TABLENAME;
		//向表中插入一条记录
		public static final String INSERT = "insert into "
				+ TABLENAME + "("
				+ FIELDS.USERNAME + ","
				+ FIELDS.PASSWORD + ","
				+ FIELDS.REALNAME + ", "
				+ FIELDS.SERVERID + ") "
				+ " values('%s','%s','%s', '%s')";
		//根据id删除一条记录
		public static final String DELETE = "delete from "
				+ TABLENAME
				+ " where %s";
		//查询语句
		public static final String QUERY = "select * from "
				+ TABLENAME
				+ " where %s";
		//查询出全部语句
		public static final String QUERYALL = "select * from "+TABLENAME+"";
		
		//根据id修改一条记录
		public static final String UPDATE = "update "
				+ TABLENAME + " set "
				+ FIELDS.USERNAME + " = '%s',"
				+ FIELDS.PASSWORD + " = '%s',"
				+ FIELDS.REALNAME + " = '%s'"
				+ FIELDS.SERVERID + "= '%s'"
				+ " where " + FIELDS.ID + " =%s";
	}
	
}
