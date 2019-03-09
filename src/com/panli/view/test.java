package com.panli.view;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.StringUtils;

import com.panli.util.FileUtils;

import lombok.extern.slf4j.Slf4j;
import sun.nio.cs.StandardCharsets;
import sun.nio.cs.UTF_32;
import sun.nio.cs.ext.GBK;
@Slf4j
public class test {
	public static void main(String[] args) throws IOException {
		
		/**  lottery
		 * 极速赛车  PK10JSC 
		         极速飞艇 LUCKYSB
		         幸运飞艇 XYFT
		 */
//	Boolean b =	FileUtils.createFile("../Scheme/PK10JSC/方案名称.txt");
//	List<String> l = FileUtils.findChildrenList(FileUtils.getFile("../Scheme/"), true);
//	int i = 0;
//	while(i<10) {
//		try {
//			FileUtils.writeStringToFile(FileUtils.getFile("../Scheme/PK10JSC/方案1.txt"), "30988849  10,8,5,7,2,6,1,9,3,4  1551972030000\n",Charset.forName("UTF-8"),true);
//		  i++;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//		log.debug(b.toString());
//	}
		
		
		
		
//		Boolean b =	FileUtils.createFile("../Scheme");
//		log.debug(b.toString());
		
//		String a= "1,2,3,4,5,6";
//		
//		List<String> l =  Arrays.asList(StringUtils.split(a, ","));
//		
//		String b="2";
//		for(int i = l.size()-1;i>0;i--)
//		{
//			if(b.equals(l.get(i)))
//			{
//				l.set(i, "22");
//			}
//		}
//		
//		System.out.println(l.size());
		
		
		List<String> list =	FileUtils.readLines(FileUtils.getFile("../Scheme/PK10JSC/方案1.txt"), "utf-8");
		
		log.debug("1");
		
	}
}
