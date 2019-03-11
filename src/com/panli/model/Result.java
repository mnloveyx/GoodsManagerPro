package com.panli.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T>{
	private String message;
	private String status;
	private String statusCode;
	private T result; // 返回数据
	
}
