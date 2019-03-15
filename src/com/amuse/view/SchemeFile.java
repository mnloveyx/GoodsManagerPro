package com.amuse.view;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.amuse.model.Plan;
import com.amuse.model.Scheme;
import com.amuse.util.FileUtils;

public class SchemeFile {
	String schemeName,schemeValue, planName,startLine, startTime,endTime, jumpLines,planType;
	Map<String,String> numMap ;
	
	
	public void  initSchemes()
	{
//		this.schemes = new ArrayList<>();
//		List<String> schemesDir =  FileUtils.findChildrenList(FileUtils.getFile(planDir), true);
//		if(CollectionUtils.isNotEmpty(schemesDir))
//		{
//			schemesDir.forEach(s->{
//				
//				Scheme sch = new Scheme(schemeName=Scheme.valueMap.get(s),schemeValue=s);
//				List<String> plansDir =  FileUtils.findChildrenList(FileUtils.getFile(planDir+"/"+s), false);
//				
//				if(CollectionUtils.isNotEmpty(plansDir))
//				{
//					plans = new ArrayList<>();
//					plansDir.forEach(p->{
//						try {
//							planName =p.split("\\.")[0];
//							List<String> files =	FileUtils.readLines(FileUtils.getFile(planDir+"/"+s+"/"+p), Charset.forName("UTF-8"));
//							if(CollectionUtils.isNotEmpty(files)) {
//									planType = files.get(0).split("=")[1];
//									startLine = files.get(1).split("=")[1];
//									startTime = files.get(2).split("=")[1].split("-")[0];
//									endTime = files.get(2).split("=")[1].split("-")[1];
//									jumpLines = files.get(3).split("=")[1];
//									
//									numMap = new HashMap<>();
//									for(int i = 4;i<files.size();i++)
//									{
//										String numKey =	files.get(i).split("=")[0];
//										String numValue =files.get(i).split("=")[1];
//										numMap.put(Plan.numValueyMap.get(numKey), numValue);
//									}
//									
//								plans.add(new Plan(schemeName,schemeValue, planName,startLine, startTime,
//										endTime, jumpLines,numMap,planType));
//							}
//							
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					});
//					sch.setPlans(plans);
//				}
//				
//				this.schemes.add(sch);
//			});
//		}
	}
}
