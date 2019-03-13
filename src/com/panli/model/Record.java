package com.panli.model;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.panli.util.DateUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Record {
	private Scheme scheme; //彩种信息
	private Plan plan;   //计划信息
	private OpenInfo openInfo; //开奖信息
	
	private Placebet placebet; //投注信息
	
	private String placeType; //0 虚拟投注，1真实投注
	
	private String lotteryName; //彩种; PK10JSC
	
	private Date drawTime = new Date(); //投注时间
	
	private String drawNumber;//期数
	
    private String planName; //方案名称 
    
    private String planType; //玩法
    
    private Double amount=0D;//金额
    
    private String resultAmount; //盈亏金额
    
    private String  placeBets=""; //投注
    
    private String  resultBets; //开奖号码
    
    private String isWin;//中挂
    
    private Boolean win =false;
    
    private String round;//轮次
    
    private String continueWin; //连中
    
    private String continueLost; //连挂
    
    private String planAmount; //方案盈亏
    
    private String currentLine;//当前线路
    
    public String[] getRowData(int heardSize) {
    	String[] data = new String[heardSize];
    	data[0] =  this.getPlan().getSchemeName();
    	data[1] =  String.valueOf(DateUtils.formatDate(drawTime, DateUtils.TIME_PATTERN_HHMMSS));
    	data[2] =  this.getPlacebet().getDrawNumber();
    	data[3] =  this.getPlan().getName();
    	data[4] =  this.getPlan().getType();
    	data[5] =  String.valueOf(this.getPlacebet().getAmounts());
    	data[6] =  this.resultAmount;
    	data[7] =   String.valueOf(this.getPlacebet().getBetString());
    	data[8] =  this.resultBets;
    	data[9] =  this.round;
    	data[10] =  this.getPlan().getPlaceType();
    	data[11] =  this.isWin;
    	data[12] =  this.continueWin;
    	data[13] =  this.continueLost;
    	data[14] =  this.planAmount;
    	data[15] =  this.plan.getCurrentLine();
    	return data;
    }
    
    
    
    public void calc()
    {
    	log.debug("开奖数据:{}",openInfo.toString());
    	log.debug("投注数据:{}",placebet.toString());
    	List<Bet> bets = placebet.getBets();
    	 
    	bets.forEach(b->{
    		String key = b.getGame()+"="+b.getContents();
//    		String r  = openInfo.getMap().get(key);
    		String r = openInfo.getResult(key);
    		
    		log.info("key:{},value:{},detail:{},drwaNum:{},platNum:{}",key,r,openInfo.getDetail(),openInfo.getDrawNumber(),placebet.getDrawNumber());
    		
    		if(!StringUtils.isEmpty(r))
    		{
    			this.win = true;
    			this.isWin = "中";
    		}else {
    			this.isWin = "挂";
    		}
    		this.placeBets+=b.getContents();
    		this.amount+=b.getAmount()*b.getOdds();
    	});
    	this.drawNumber = openInfo.getDrawNumber();
    	this.resultBets = openInfo.getResult();
    	if(getWin())
    	{
    		this.resultAmount ="+"+this.amount;
    	}else {
    		this.resultAmount ="-"+this.getPlacebet().getAmounts();
    	}
    }

	public Record(Plan plan) {
		super();
		this.plan = plan;
		if(getPlan()!=null)
		{
			this.lotteryName = plan.getSchemeName();
			this.planName = plan.getName();
			this.placeType = plan.getType();
		}
	}

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
