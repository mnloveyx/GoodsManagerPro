package com.panli.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员t_user表的User实体类
 * @author Peter
 *
 */
@Getter
@Setter
public class User extends Result<User> {
	
	private String id;//id字段
	private String code; //验证码
	private String cryptograph;
	private String password;
//	private String token ="c3440b1ec1cf95c43852c2c729bc6b866d2ffef6";
	private String token;
	private Boolean changePassword;
	private Date created;
	private Boolean gameEnable;
	private String ip;
	private String ipAddress;
	private Date lastLogin;
	private Date loginTime;
	private Integer lv;
	private Boolean main;
	private String oid;
	private Boolean online;
	private String parent;
	private String platform;
	private String range;
	private Integer resetType;
	private String server;
	private Integer shareMode;
//	private Integer status;
	private Integer type;
	private Date updated;
	private String userKey;
	private String username;
	private String userpass;
	private Integer wechatEnabled;
	
	public User(String username, String password,String code, String cryptograph) {
		super();
		this.code = code;
		this.cryptograph = cryptograph;
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	} 

	public String getToken()
	{
		return this.oid;
	}
}
