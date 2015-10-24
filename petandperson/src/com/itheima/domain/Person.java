package com.itheima.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

	
	private String name;
	private String pass;
	private Integer id;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(Integer id,String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		//这个就是将Person的数据写入到数据包中
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(pass);

	}
	//添加一个静态的成员 Creator,该对象实现了parcelable.creator接口
	public static final Parcelable.Creator<Person> CREATOR=new Parcelable.Creator<Person>() {

		//将数据包中的数据返回给Person对象
		@Override
		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Person(source.readInt(),source.readString(),source.readString());
		}

		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
	};

}
