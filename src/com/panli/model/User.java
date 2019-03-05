package com.panli.model;
/**
 * 管理员t_user表的User实体类
 * @author Peter
 *
 */
public class User {
	private int id;//id字段
	private String userName;//userName字段
	private String password;//password字段
	private String code; //验证码
	private String token;
	
	//继承父类的构造方法
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 重载父类的构造方法带2个参数
	 * @param userName
	 * @param password
	 */
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password,String code ) {
		super();
		this.userName = userName;
		this.password = password;
		this.code = code;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
