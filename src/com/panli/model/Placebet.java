package com.panli.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
			Bet  t  = bets.get(i);
			String key = t.getGame().substring(0,2)+"_"+t.getContents();
			 b+=(StringUtils.isEmpty(betMap.get(key))?t.getContents():betMap.get(key))+",";
		}
		return b.substring(0,b.length()-1);
	}
	
	private static Map<String,String> betMap = new HashMap<>();
	
	static {
		betMap.put("DX_D","大");
		betMap.put("DX_X","小");
		betMap.put("DS_D", "单");
		betMap.put("DS_S", "双");
	}
	
	
	public Integer getPlaceAmounts()
	{
		Integer b = 0;
		
		for(int i =0;i<bets.size();i++)
		{
			 b+=bets.get(i).getAmount();
		}
		return b;
	}
	
	
}
