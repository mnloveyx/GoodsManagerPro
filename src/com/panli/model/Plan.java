package com.panli.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plan {
	
   private String schemeName;
   private String schemeValue;
   private String name;
   private String value;
   private String startLine;
   private Integer startTime;
   private Integer endTime;
   private String jumpLine;
   private Map<String,List<String>> numMap;
   private String currentLine;
   
	public Plan(String schemeName, String schemeValue, String name, String value, String startLine, Integer startTime,
			Integer endTime, String jumpLine, String currentLine) {
		super();
		this.schemeName = schemeName;
		this.schemeValue = schemeValue;
		this.name = name;
		this.value = value;
		this.startLine = startLine;
		this.startTime = startTime;
		this.endTime = endTime;
		this.jumpLine = jumpLine;
		this.currentLine = currentLine;
	}
  
	public Plan(String schemeName, String schemeValue, String name, String value, String startLine, Integer startTime,
			Integer endTime, String jumpLine, String currentLine,Map<String,List<String>> numMap) {
		this(schemeName, schemeValue, name, value, startLine, startTime, endTime, jumpLine, currentLine);
		this.numMap = numMap;
	}
}
