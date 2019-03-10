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
  
  private String placeCount;   //投注记录
  private String placeStatus;  //投注状态
  private String winPercent;  //准确率
  private String drawNumber;  //投注基数
  
  private String continueWin; //连中
  
  private String continueLost; //连挂
  
  private List<Record> records;
  
}
