package com.amuse.lottery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.amuse.lottery.utils.CommFunc;
import com.amuse.lottery.utils.MD5Utils;

import lombok.extern.slf4j.Slf4j;

public class Opendata {
	private  String Expect;

	private String Code;

	private Date Time;

	private List<String> CodeList;
	
	private String value;
	
	private static int numberCount=2;
	
	private boolean isShowThreeNumber = false;
	
	private int threeNumberCount =0;
	
	private boolean isWin =false;

	public String getExpect() {
		return Expect;
	}

	public void setExpect(String expect) {
		Expect = expect;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}

	public List<String> getCodeList() {
		return CodeList;
	}

	public void setCodeList(List<String> codeList) {
		CodeList = codeList;
	}

	public Opendata() {
	}
	
	public Opendata(String str) {
		if(null!=str)
		{
			String[] s = str.split("\t");
			this.Expect =s[0];
			this.Code = s[1];
			if(this.Expect!=null)
			{
				this.value = getRandomValue(Expect);
				if(!this.Code.contains("等待"))
				{
					for(int i=0; i<this.Code.length();i++)
					{
						boolean b = false;
						for(int j =0;j<this.value.length();j++)
						{
							if(this.Code.charAt(i)==this.value.charAt(j))
							{
								this.isWin = true;
								b = true;
								break;
							}
						}
						if(b)
						{
							break;
						}
					}
				}
				
			}
		}
	}
	
	public static String getRandomValue(String Expect)
	{
		String pValue ="";
		String md5 = MD5Utils.MD5Encode("腾讯分分彩五星专区公式1"+Expect+"五星双胆CLYLJH","utf8");
//		String md5 = MD5Utils.md5("腾讯分分彩五星专区公式1"+this.Expect+"五星双胆CLYLJH","utf8");
		
		for (int i = 0; i < numberCount; i++)
		{
			int num = md5.charAt(i) %10;
			while (pValue.contains(String.valueOf(num)))
			{
				num = (num + 1) % 10;
			}
			pValue += num;
		}
		pValue = CommFunc.SortString(pValue);
		return pValue;
	}

	
	public Opendata(String expect, String code,boolean isShowThreeNumber) {
		this(expect+"\t"+code);
//		Expect = expect;
//		Code = code;
		this.isShowThreeNumber = isShowThreeNumber;
//		this.value = getRandomValue(Expect);
	}

	@Override
	public String toString() {
		
		Date d = new Date();
		
		 SimpleDateFormat aDate=new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
		 
		 String a = "时间:【"+aDate.format(d)+"】 期数:【"+Expect+"】 五星双胆 【"+value+"】 中奖码 :【"+Code+"】";
		 
		 if(isShowThreeNumber)
		 {
			 String  three = getThreeNumber(value);
			 a=three+"\r\n 三星数字:总共:【"+threeNumberCount+"组】\r\n" +a ;
		 }
		return  a;
	}
	
   
	public String getThreeNumber(String value)
	{
		List<String> list   = getThreeNumberList(value);
		
		String a ="";
		if(!list.isEmpty())
		{
			this.threeNumberCount = list.size();
			for(int i =0 ;i<list.size();i++)
			{
				String s =" ";
				if(i%20==19)
				{
					s ="\r\n";
				}
					a+=list.get(i)+s;
			}
		}
		
		return a;
	}
	
	public List<String> getThreeNumberList(String value)
	{
		List<String> list = new ArrayList<>();
		if(value==null) return list;
		String a  = value;
		int b = 1000;
		for(int i = 0;i<b;i++)
		{
			int k = 0;
			for(int j =0;j<a.length();j++)
			{
				String e = String.format("%03d",i);
				if(e.contains(String.valueOf(a.charAt(j))) && k==0)
				{
					k++;
					list.add(e);
				}
			}
		}
		return list;
	}

}
