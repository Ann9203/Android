package com.itheima.domain;

public class Student {
	private int id;
	private String stuname;
	private String sex;
	private int chinese;
	private int english;
	private int math;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", stuname=" + stuname + ", sex=" + sex
				+ ", chinese=" + chinese + ", english=" + english + ", math="
				+ math + "]";
	}
	

}
