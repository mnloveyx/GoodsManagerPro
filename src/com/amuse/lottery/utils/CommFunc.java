package com.amuse.lottery.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CommFunc {
	// IntelligentPlanning.CommFunc
		public static String SortString(String pStr)
		{
			List<String> list = CommFunc.ConvertSameListString(pStr);
			list.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			return StringUtils.join(list,"");
		}
		
		
		// IntelligentPlanning.CommFunc
		public static List<String> ConvertSameListString(String pStr)
		{
			List<String> list = new ArrayList<>();
			for (int i = 0; i < pStr.length(); i++)
			{
				list.add(String.valueOf(pStr.charAt(i)));
			}
			return list;
		}
}
