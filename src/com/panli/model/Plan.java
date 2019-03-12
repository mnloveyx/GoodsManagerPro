package com.panli.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Plan {
	
   private String schemeName;
   private String schemeValue;
   private String name;
   private String value;
   private String startLine;
   private String  startTime;
   private String endTime;
   private List<String> jumpLines;
   private String jumpLine;
   private Map<String,List<String>> numMap; //号码映射
   private String numMapStr="";
   private Map<String,String> numMap_sor;
   private String currentLine;
   private String jumpHistory;
   private String type;
   private String  amounts;  //投注金额设置
   private String  placeType; //投注类型   模拟投注，真实投注;
   
   private String startGame;
   
   private List<String> startContents = new ArrayList<>();
   
   
   public void setStartLine(String startLine)
   {
	   this.startLine = startLine;
	   this.currentLine = startLine;
   }
   
   public void nextLine()
   {
	  for(int i = 0 ;i<jumpLines.size();i++)
	  {
		  String  s = jumpLines.get(i);
		  if(currentLine.equalsIgnoreCase(s))
		  {
			  String l = jumpLines.get(i+1%jumpLines.size());
			  log.info("当前线路：{},切换线路：{}"+currentLine,l);
			  
			 setStartLine(l);
		  }
			  
	  }
   }
   
   public void setType(String type)
   {
	   this.type  = type;
	   if(type.equalsIgnoreCase("开某投某")){
		   this.startGame = "B"+currentLine;
		   this.startContents = numMap.get(startLine);
	   }else if(type.equalsIgnoreCase("双单"))
	   {
		   this.startGame = "DS"+currentLine;
		   if(startContents.contains("S"))
		   {
			   startContents.add("D");
			   startContents.remove("S");

		   }else {
			   startContents.add("S");
			   startContents.remove("D");
		   }
		   
	   }else if(type.equalsIgnoreCase("单双"))
	   {
		   this.startGame = "DS"+currentLine;
		   
		   if(startContents.contains("D"))
		   {
			   startContents.add("S");
			   startContents.remove("D");

		   }else {
			   startContents.add("D");
			   startContents.remove("S");
		   }
		   
	   }else if(type.equalsIgnoreCase("大小"))
	   {
		   this.startGame = "DX"+currentLine;
		    if(startContents.contains("D"))
			   {
				   startContents.add("X");
				   startContents.remove("D");
	
			   }else {
				   startContents.add("D");
				   startContents.remove("X");
			   }
	   }else if(type.equalsIgnoreCase("小大"))
	   {
		   this.startGame = "DX"+currentLine;
		   if(startContents.contains("X"))
		   {
			   startContents.add("D");
			   startContents.remove("X");

		   }else {
			   startContents.add("X");
			   startContents.remove("D");
		   }
	   }
	   
   }
   
   public void setNextContents()
   {
	   
   }
   
   private List<String> amountsList;
   
   
   public void setAmounts(String amounts)
   {
	   this.amounts = amounts;
	   
	   if(CollectionUtils.isEmpty(amountsList))
	   {
		   this.amountsList = Arrays.asList(StringUtils.split(amounts, ","));
	   }
   }
   
   
   public void  setJumpLine(String jumpLine)
   {
	   this.jumpLine = jumpLine;
	   if(CollectionUtils.isEmpty(jumpLines))
	   {
		   this.jumpLines = Arrays.asList(StringUtils.split(jumpLine, ","));
	   }
   }
   
   public String getNumMap(String key) {
	   return numMap_sor.get(key);
   }
   
   public void setNumMap_sor(Map<String, String> map)
   {
	   this.numMap_sor = map;
	   if(MapUtils.isEmpty(numMap))
	   {
		   numMap = new HashMap<>();
        for (Map.Entry<String, String> entry : numMap_sor.entrySet()) {
        		numMap.put(entry.getKey(), Arrays.asList(StringUtils.split(entry.getValue(), ",")));
        		numMapStr+=numKeyMap.get(entry.getKey())+"="+entry.getValue()+"\n";
		 }
	   }
   }
   
	public Plan(String schemeName, String schemeValue, String name, String startLine, String startTime,
			String endTime, String jumpLine,String type) {
		super();
		this.schemeName = schemeName;
		this.schemeValue = schemeValue;
		this.name = name;
		this.startLine = startLine;
		this.startTime = startTime;
		this.endTime = endTime;
		setJumpLine(jumpLine);
		this.type = type;
	}
  
	public Plan(String schemeName, String schemeValue, String name, String startLine, String startTime,
			String endTime, String jumpLine,Map<String,String> numMap_sor,String type) {
		this(schemeName, schemeValue, name, startLine, startTime, endTime, jumpLine,type);
		setNumMap_sor(numMap_sor);
	}

	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public static Map<String,String> numKeyMap =  new HashMap<>();
	
	
	static {
		numKeyMap.put("1", "一号");
		numKeyMap.put("2", "二号");
		numKeyMap.put("3", "三号");
		numKeyMap.put("4", "四号");
		numKeyMap.put("5", "五号");
		numKeyMap.put("6", "六号");
		numKeyMap.put("7", "七号");
		numKeyMap.put("8", "八号");
		numKeyMap.put("9", "九号");
		numKeyMap.put("10", "十号");
	}
	
public static Map<String,String> numValueyMap =  new HashMap<>();
	
	
	static {
		numValueyMap.put("一号","1");
		numValueyMap.put("二号","2");
		numValueyMap.put("三号","3");
		numValueyMap.put("四号","4");
		numValueyMap.put("五号","5");
		numValueyMap.put("六号","6");
		numValueyMap.put("七号","7");
		numValueyMap.put("八号","8");
		numValueyMap.put("九号","9");
		numValueyMap.put("十号","10");
	}
	
	public String toFileString() {
		
//		String numMap = "";
//		
//        for (Map.Entry<Integer, String> entry : numMap.entrySet()) {
//            //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
//            //entry.getKey() ;entry.getValue(); entry.setValue();
//            //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
//            System.out.println("key= " + entry.getKey() + " and value= "
//                    + entry.getValue());
//        }
		
		
		return "玩法="+this.type+"\n"
				+"开始路线="+this.startLine+"\n"
				+"时间范围="+this.startTime+"-"+this.endTime+"\n"
				+"跳线="+StringUtils.join(jumpLines, ",")+"\n"
				+numMapStr
				;
	}
	
	 /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return name;
    }
    
	public String toLogString() {
		return "Plan [schemeName=" + schemeName + ", name=" + name + ", startLine=" + startLine + ", startTime="
				+ startTime + ", endTime=" + endTime + ", jumpLine=" + jumpLine + ", numMapStr=" + numMapStr
				+ ", currentLine=" + currentLine + ", jumpHistory=" + jumpHistory + ", type=" + type + "]";
	}
	
}
