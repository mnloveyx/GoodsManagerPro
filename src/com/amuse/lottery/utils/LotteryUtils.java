package com.amuse.lottery.utils;

import java.util.ArrayList;
import java.util.List;

import com.amuse.lottery.Opendata;

public class LotteryUtils {
	public static List<Opendata>  buildOpendata(String str)
	{
		if(str!=null)
		{
			List<Opendata> lists = new ArrayList<>();
			String[] s = str.split("\r\n");
			
			for(int i=0;i<s.length;i++)
			{
				lists.add(new Opendata(s[i]));
			}
			
			String nextExpect = lists.get(0).getExpect();
			
			lists.add(0,new Opendata(nextExpect.substring(0, 7)+String.format("%04d",Integer.valueOf(nextExpect.substring(7, nextExpect.length()))+1),"等待中奖码",true));
			int b = 20;
//			lists.forEach(o->{
//				i++;
//				System.out.println(o.toString());
//				
//			});
			for(int i = 0 ;i<b ;i++)
			{
				System.out.println(lists.get(i).toString());
			}
			
//			List<Opendata> lists2 = new ArrayList<>();
//			lists2.addAll(c)
//			
//			List<Opendata> lists2 = new ArrayList<>();
//			
//			for()
			
			
			
			return lists;
		}
		return null;
	}
}
