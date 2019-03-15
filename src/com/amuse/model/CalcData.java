package com.amuse.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalcData {
	private Plan plan = new Plan();
//	private String planName;
//	private String content;
	private String currentConWin="0";
	private String currentConLost="0";
	private String preConWin="0";
	private String preOpen="0";
	private String todayNoOpen="0";
	private String yesterdayNoOpen="0";
	private String weekNoOpen="0";
	
	  public String[] getRowData(int heardSize) {
	    	String[] data = new String[heardSize];
	    	data[0] =  this.getPlan().getName();
	    	data[1] =  this.getPlan().getStartLine();
	    	data[2] =  this.currentConWin;
	    	data[3] =  this.currentConLost;
	    	data[4] =  this.preConWin;
	    	data[5] =  this.preOpen;
	    	data[6] =  this.todayNoOpen;
	    	data[7] =  this.yesterdayNoOpen;
	    	data[8] =  this.weekNoOpen;
	    	return data;
	    }

	public CalcData(Plan plan) {
		super();
		this.plan = plan;
	}
	
}
