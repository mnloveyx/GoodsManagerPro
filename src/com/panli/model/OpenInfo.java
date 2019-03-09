package com.panli.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenInfo {
	private String detail; //开奖明细
	private String drawNumber;//期数
	private String drawTime; //开奖时间
	private Map<String,String> map;
	private String result; //开奖结果
	private String lottery; //彩种; PK10JSC
}
