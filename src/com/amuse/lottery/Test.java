//package com.amuse.lottery;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URLDecoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.twikkercn.platform.common.utils.StringUtils;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/test")
//public class Test {
//	
//	RestTemplate template = new RestTemplate();
//	
//	@GetMapping("/")
//	public void Test()
//	{
//		 
////		 URLEncoder.encode("xxx", "utf-8");
//		
//		UriComponentsBuilder builder = UriComponentsBuilder
//                .fromHttpUrl("http://116.31.99.59:3580/CSC.aspx")
//                .queryParam("action", "getRGTempletList")
//                .queryParam("AppName", "CSC")
//                .queryParam("Name", "53五星专区")
//                .queryParam("Path", "\\CLYLJH-SSC\\")
////                .query("Mkey={Mkey}").buildAndExpand("rusoUwz3OM5NaI5rMsgJlQ==");
//                .queryParam("Mkey", "rusoUwz3OM5NaI5rMsgJlQ==");
////		String a = "rusoUwz3OM5NaI5rMsgJlQ==";
//		 Map<String, String> vars = new HashMap<String, String>();
//		 vars.put("action", "getRGTempletList");
//         vars.put("AppName", "CSC");
//         vars.put("Name", "53五星专区");
//         vars.put("Path", "\\CLYLJH-SSC\\");
//         vars.put("Mkey", "rusoUwz3OM5NaI5rMsgJlQ==");
////		String url  = "http://116.31.99.59:3580/CSC.aspx?action={action}&AppName={AppName}&Path={Path}&Mkey={Mkey}";
////		String text = template.getForEntity("http://116.31.99.59:3580/CSC.aspx?action=getRGTempletList&AppName=CSC&Path=%5cCLYLJH-SSC%5c&Mkey=rusoUwz3OM5NaI5rMsgJlQ==", String.class).getBody();
////         URI u =   builder.toUri();
//         String u = builder.toUriString();
//         log.info(u.toString());
////         String text = template.getForEntity(u.toString(), String.class).getBody();
////         String text = template.getForEntity(url, String.class,vars).getBody();
//         String text = template.getForEntity(builder.build().toUri(), String.class).getBody();
//		log.info("return:{}",text);
//		String s = 	toStrings(text);
//		
//		log.info(s);
////		String[] array = StringUtils.split(text, "\r\n");
//		
////		String[] array = Strings.Split(text, "\r\n", -1, CompareMethod.Binary);
////		String[] array = Strings.Split(text, "\r\n", -1, CompareMethod.Binary);
////		for (int i = 0; i < array.Length; i++)
////		{
////			string text2 = array[i];
////			if (!(text2 == ""))
////			{
////				string[] array2 = text2.Split(new char[]
////				{
////					'\t'
////				});
////				string str = array2[0];
////				string text3 = array2[1];
////				if (this.CheckCodeLen(text3))
////				{
////					string key = str + "\t" + text3;
////					this.OpenDataOption.GetData++;
////					if (dictionary2.ContainsKey(key))
////					{
////						flag = (!this.mainThread.SingleMode || dictionary.Count > 0);
////						goto IL_23A;
////					}
////					if (!dictionary.ContainsKey(key))
////					{
////						dictionary[key] = "";
////						this.OpenDataOption.GetNewData++;
////					}
////				}
////			}
////		}
//	}
//	
//	public static void main(String[] args) throws Throwable {
//		new Test().Test();
//		
//		String decodeStr = URLDecoder.decode("53%e4%ba%94%e6%98%9f%e4%b8%93%e5%8c%ba", "utf-8");  
//		System.out.println("解码:" + decodeStr); 
//		
//	}
//	
//	
//	 //将字符串转换成二进制字符串，以空格相�?
//    public static String StrToBinstr(String str) {
//        char[] strChar=str.toCharArray();
//        String result="";
//        for(int i=0;i<strChar.length;i++){
//            result +=Integer.toBinaryString(strChar[i])+ " ";
//        }
//        
//        return result;
//    }
//  //将二进制字符串转换为char
//    public static char BinstrToChar(String binStr){
//        int[] temp=BinstrToIntArray(binStr);
//        int sum=0;   
//        for(int i=0; i<temp.length;i++){
//            sum +=temp[temp.length-1-i]<<i;
//        }   
//        return (char)sum;
//    }
//     //将二进制字符串转换成int数组
//    public static int[] BinstrToIntArray(String binStr) {
//        char[] temp = binStr.toCharArray();
//        int[] result = new int[temp.length];
//        for (int i = 0; i < temp.length; i++) {
//            result[i] = temp[i] - 48;
//        }
//        return result;
//    }
// 
//    public static String toStrings(String a) {
////        String[] arr = a.split("\\s+");
//    	 String[] arr = a.split("/n|/r");
//        String sss = "";
//        for (String ss : arr) {
//            sss = sss + BinstrToChar(ss);
//        }
//        return sss;
//    }
//	
//}
