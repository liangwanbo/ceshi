package com.cblue.xutils.sample;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;


@Table(name="student")
public class Student {
	
	@Id
	private int id;
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(column="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
