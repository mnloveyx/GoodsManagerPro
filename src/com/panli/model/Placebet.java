package com.panli.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Placebet {
	private Date drawTime; //投注时间
	private String drawNumber;//期数
	private String lottery; //彩种; PK10JSC
	private List<Bet> bets = new ArrayList<>(); //投注号码类型
	@Override
	public String toString() {
		String b = "";
		
		for(int i =0;i<bets.size();i++)
		{
			 b+=bets.get(i).toString();
		}
		
		return "Placebet [drawTime=" + drawTime + ", drawNumber=" + drawNumber + ", lottery=" + lottery + ", bets="
				+ b+ "]";
	}
	
	public String getBetString()
	{
		String b = "";
		
		for(int i =0;i<bets.size();i++)
		{
			 b+=bets.get(i).getContents().toString();
		}
		return b;
	}
	
	public Integer getAmounts()
	{
		Integer b = 0;
		
		for(int i =0;i<bets.size();i++)
		{
			 b+=bets.get(i).getAmount();
		}
		return b;
	}
	
	
}
