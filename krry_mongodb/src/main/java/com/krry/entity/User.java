package com.krry.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Document(collection = "user")这个注解和Hibernate的注解Entiry非常相似，
 * 就是定义一个文档，对象MongoDB存储的Collection（表）的名称是user
 * @Id指该字段是主键，不能缺少
 * @Field("username")指该字段映射MongoDB的实际字段，如果一致可以省略、
 *
 * User
 * @author krry
 * @version 1.0.0
 *
 */
//@Document(collection = "user")
public class User implements Serializable{

	private static final long serialVersionUID = -250098600900666239L;
	//主键
//	@Id
	//private String id;
	//用户名
//	@Field("username")
	private String username;
	//密码
	private String password;
	//建时间
	private String createTime;

	private String level;
	private String[] tags;
	private String ip;
	private String result;

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", createTime='" + createTime + '\'' +
				", level='" + level + '\'' +
				", tags=" + Arrays.toString(tags) +
				", ip='" + ip + '\'' +
				", result='" + result + '\'' +
				'}';
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public User() {

	}

	public User(String username,String password,String createTime) {
		this.username = username;
		this.password = password;
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


//	 public String getId() {
//		 return id;
//	 }
//
//	 public void setId(String id) {
//		 this.id = id;
//	 }

	 public String getUsername() {
		 return username;
	 }

	 public void setUsername(String username) {
		 this.username = username;
	 }

	 public String getPassword() {
		 return password;
	 }

	 public void setPassword(String password) {
		 this.password = password;
	 }

}