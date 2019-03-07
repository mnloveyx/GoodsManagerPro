package com.panli.view;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.CharSet;

import com.panli.util.FileUtils;

import lombok.extern.slf4j.Slf4j;
import sun.nio.cs.StandardCharsets;
import sun.nio.cs.UTF_32;
import sun.nio.cs.ext.GBK;
@Slf4j
public class test {
	public static void main(String[] args) {
		
		/**  lottery
		 * 极速赛车  PK10JSC 
极速飞艇 LUCKYSB
幸运飞艇 XYFT
		 */
	Boolean b =	FileUtils.createFile("../Scheme/PK10JSC/方案名称.txt");
	List<String> l = FileUtils.findChildrenList(FileUtils.getFile("../Scheme/PK10"), false);
	int i = 0;
	while(i<10) {
		try {
			FileUtils.writeStringToFile(FileUtils.getFile("../Scheme/PK10JSC/方案名称.txt"), "30988849  10,8,5,7,2,6,1,9,3,4  1551972030000\n",Charset.forName("UTF-8"),true);
		  i++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		log.debug(b.toString());
	}
}
