package com.panli.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.panli.util.DateUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenInfo{
	private String detail; //开奖明细
	private String drawNumber;//期数
	private Date drawTime; //开奖时间
	private Map<String,String> map = new HashMap<>();
	private String result; //开奖结果
	private String lottery; //彩种; PK10JSC
	
	/**
	 * 组装开奖结果
	 * @Title: getMap   
	 * @Description: TODO
	 * @param: @return      
	 * @return: Map<String,String>      
	 * @throws
	 */
	private Map<String,String> getMap()
	{
		if(StringUtils.isNoneBlank(result))
		{
			String[] r = result.split(",");
			for(int i = 0;i<r.length;i++)
			{
				Integer j = Integer.valueOf(r[i]);
				String k = "";
				String v = String.valueOf(j);
				if(j%2==0)
				{
					k = "DS"+(i+1)+"="+"S";
					map.put(k,v);
				}else {
					k = "DS"+(i+1)+"="+"D";
					map.put(k,v);
				}
				if(j>5)
				{
					k = "DX"+(i+1)+"="+"D";
					map.put(k,v);
				}else {
					k = "DX"+(i+1)+"="+"X";
					map.put(k,v);
				}
				map.put("B"+(i+1)+"="+v, v);
			}
			
			return map;
		}
		return this.map;
	}
	
	public String getResult(String key)
	{
		if(MapUtils.isNotEmpty(this.map))
		{
			return this.map.get(key);
		}else {
			return this.getMap().get(key);
		}
	}
	
	
	 public String[] getRowData(int heardSize) {
	    	String[] data = new String[heardSize];
	    	data[0] =  this.drawNumber;
	    	data[1] =  this.result;
	    	data[2] =  String.valueOf(DateUtils.formatDate(drawTime, DateUtils.TIME_PATTERN_HHMMSS));
	    	return data;
	    }


	@Override
	public String toString() {
		return "OpenInfo [detail=" + detail + ", drawNumber=" + drawNumber + ", drawTime=" + String.valueOf(DateUtils.formatDate(drawTime, DateUtils.TIME_PATTERN_DEFAULT)) + ", map=" + map
				+ ", result=" + result + ", lottery=" + lottery + "]";
	}
	 
	 
}
