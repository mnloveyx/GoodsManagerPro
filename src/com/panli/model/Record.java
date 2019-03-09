package com.panli.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Record {
	private Scheme scheme; //彩种信息
	private Plan plan;   //计划信息
	private OpenInfo openInfo; //开奖信息
	private Placebet placebet; //投注信息
	
	private String placeType; //0 虚拟投注，1真实投注
}
