package com.panli.model;


import java.math.BigDecimal;
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
    
//    private String resultAmountTxt; //盈亏金额
    
    private String  placeBets=""; //投注
    
    private String  resultBets; //开奖号码
    
    private String isWin;//中挂
    
    private Boolean win =false;
    
    private String round;//轮次
    
//    private String continueWin="0"; //连中
//    
//    private String continueLost="0"; //连挂
//    
//    private String planAmount; //方案盈亏
    
    private String currentLine;//当前线路
    
    private BigDecimal resultAmount = new BigDecimal(0);
    
    public String getResultAmountTxt()
    {
    	BigDecimal b =getResultAmount();
    	if(b.doubleValue()>0)
    	{
    		return "+"+b.toString();
    	}else {
    		return b.toString();
    	}
    }
    

    
    public String[] getRowData(int heardSize) {
    	String[] data = new String[heardSize];
    	data[0] =  this.getPlan().getSchemeName();
    	data[1] =  String.valueOf(DateUtils.formatDate(drawTime, DateUtils.TIME_PATTERN_HHMMSS));
    	data[2] =  this.getPlacebet().getDrawNumber();
    	data[3] =  this.getPlan().getName();
    	data[4] =  this.getPlan().getType();
    	data[5] =  String.valueOf(this.getPlacebet().getPlaceAmounts());
    	data[6] =  this.getResultAmountTxt();
    	data[7] =   String.valueOf(this.getPlacebet().getBetString());
    	data[8] =  this.resultBets;
    	data[9] =  this.getRound();
    	data[10] =  this.getPlan().getPlaceType();
    	data[11] =  this.isWin;
    	data[12] =  this.getPlan().getContinueWin();
    	data[13] =  this.getPlan().getContinueLost();
    	data[14] =  this.plan.getPlanAmountTxt();
    	data[15] =  this.plan.getCurrentLine();
    	return data;
    }
    
    /**
     * 
     * @Title: calc   
     * @Description: calcOpenresult
     * @param:       
     * @return: void      
     * @throws
     */
    public void calc()
    {
    	log.debug("opendata:{}",openInfo.toString());
    	log.debug("placedata:{}",placebet.toString());
    	List<Bet> bets = placebet.getBets();
    	 
    	bets.forEach(b->{
    		String key = b.getGame()+"="+b.getContents();
    		String r = openInfo.getResult(key);
    		
    		log.info("key:{},value:{},detail:{},drwaNum:{},platNum:{}",key,r,openInfo.getDetail(),openInfo.getDrawNumber(),placebet.getDrawNumber());
    		
    		if(!StringUtils.isEmpty(r))
    		{
    			this.win = true;
    			resultAmount = resultAmount.add(b.getWinAmount());
    		}
    		resultAmount = resultAmount.subtract(b.getPlaceAmount());
    		
    	});
    	this.drawNumber = openInfo.getDrawNumber();
    	this.resultBets = openInfo.getResult();
    	if(getWin())
    	{
    		this.isWin = "中";
    	}else {
    		this.isWin = "挂";
    	}
    	//calcPlanAmount
    	plan.calcPlanAmount(getResultAmount());
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


//
//	public void setRound(String round) {
//		this.plan.setRound(round);
//	}
//	
	public void setPlanTotalRound(String round) {
		this.plan.setRound(round);
	}

//	public void setContinueWin(int continueWin) {
//		this.continueWin = String.valueOf(continueWin);
//	}
//
//	public void setContinueLost(int continueLost) {
//		this.continueLost = String.valueOf(continueLost);
//	}
    
}
