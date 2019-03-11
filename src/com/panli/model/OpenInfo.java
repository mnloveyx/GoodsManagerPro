package com.panli.model;

import java.util.Date;
import java.util.Map;

import com.panli.util.DateUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenInfo {
	private String detail; //开奖明细
	private String drawNumber;//期数
	private Date drawTime; //开奖时间
	private Map<String,String> map;
	private String result; //开奖结果
	private String lottery; //彩种; PK10JSC
	
	
	 public String[] getRowData(int heardSize) {
	    	String[] data = new String[heardSize];
	    	data[0] =  this.drawNumber;
	    	data[1] =  this.result;
	    	data[2] =  String.valueOf(DateUtils.formatDate(drawTime, DateUtils.TIME_PATTERN_HHMMSS));
	    	return data;
	    }
}
