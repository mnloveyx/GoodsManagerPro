package com.panli.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Statis {
  private String realCount; //真实投注次数
  private String realAmount;  //真实盈亏
  private String virtualCount; //虚拟投注次数
  private String virtualAmount;  //虚拟盈亏
  private String totalCount;   //投注记录
  
  private String planTotalCount;   //方案投注记录
  private String placeStatus;  //投注状态
  private String realWinPercent;  //准确率
  private String drawNumber;  //投注基数
  
  private String continueWinMax; //最大连中
  
  private String continueLostMax; //最大连挂
  
  private String  wincount;

public Statis() {
	super();
	// TODO Auto-generated constructor stub
}

public Statis(String realCount, String realAmount, String virtualCount, String virtualAmount, String totalCount,
		String planTotalCount, String placeStatus, String realWinPercent, String continueWinMax, String continueLostMax,
		String wincount) {
	super();
	this.realCount = realCount;
	this.realAmount = realAmount;
	this.virtualCount = virtualCount;
	this.virtualAmount = virtualAmount;
	this.totalCount = totalCount;
	this.planTotalCount = planTotalCount;
	this.placeStatus = placeStatus;
	this.realWinPercent = realWinPercent;
	this.continueWinMax = continueWinMax;
	this.continueLostMax = continueLostMax;
	this.wincount = wincount;
}
  
//  private List<Record> records;
  
  
  
}
