package com.amuse.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.StringUtils;

import com.amuse.model.HistoryDraw;
import com.amuse.model.LastOpenResult;
import com.amuse.model.Member;
import com.amuse.model.Odd;
import com.amuse.model.OpenInfo;
import com.amuse.model.Period;
import com.amuse.model.Result;
import com.amuse.model.User;
import com.amuse.util.Api;
import com.amuse.util.DateTypeAdapter;
import com.amuse.util.DateUtils;
import com.amuse.util.FileUtils;
import com.amuse.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;
import sun.nio.cs.StandardCharsets;
import sun.nio.cs.UTF_32;
import sun.nio.cs.ext.GBK;
@Slf4j
public class test {
//	laomeng666
//	Aa321123
	/**  lottery
	 * 极速赛车  PK10JSC 
	         极速飞艇 LUCKYSB
	         幸运飞艇 XYFT
	 */
//	public static void main(String[] args) throws Exception {
//
//	}
	
	public void test() {

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
		
		
//		List<String> list =	FileUtils.readLines(FileUtils.getFile("../Scheme/PK10JSC/方案1.txt"), "utf-8");
		
//		log.debug("1");
//		testMember();
//		testUser();
		
//		getPeriod();
//		getOdds();
//		getLastResult();
		
//		int i = 7;
//		System.out.println(0%6);
//		calcResult();
		
		DecimalFormat df=new DecimalFormat("0.00");
		
//		Integer i = XMathUtil.divide(new BigDecimal(3), new BigDecimal(0)).multiply(new BigDecimal(100)).toBigInteger().intValue();
		BigDecimal i = new BigDecimal(1).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
		System.out.println(i.toString());
		BigDecimal j = i.multiply(new BigDecimal(100));
		System.out.println(j.intValue());
		String a= j.setScale(2).toString();//.divideToIntegralValue(new BigDecimal(10)).toString();//.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).toString()+"%";
//		System.out.println(df.format((double)3/4)*100));
		System.out.println(a.toString());
//		testCachedThreadPool();
	}
	
	
	
	
	public static void testCachedThreadPool() throws Exception {
		// 声明一个线程池
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 4; i++) {
			final int a = i;
			// 每一次调用execute方法，都会向线程池中放入一个对象
			es.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						System.err.println("测试...."+ a +">"+
								Thread.currentThread().getName()+","+
								Thread.currentThread().isDaemon());
						try {
							Thread.sleep(1000);
							return;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
 
				}
			});
		}
		
		es.shutdown();
		es.awaitTermination(10000, TimeUnit.MILLISECONDS);
	}
	
	
	public static void testMember()
	{
		String result ="{\"message\":\"成功检索\",\"result\":{\"accounts\":[{\"balance\":20.0,\"maxLimit\":20.0,\"type\":0},{\"balance\":0.0,\"maxLimit\":0.0,\"type\":1},{\"balance\":0.0,\"maxLimit\":0.0,\"type\":2},{\"balance\":0.0,\"maxLimit\":0.0,\"type\":3}],\"atypes\":[0],\"lotterys\":{\"0\":[{\"accountType\":0,\"balls\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\"],\"canBack\":true,\"drawCount\":10,\"id\":\"SGFT\",\"lotterySubType\":0,\"maxBall\":10,\"minBall\":1,\"name\":\"SG飞艇\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"PK10\",\"type\":1},{\"accountType\":0,\"balls\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\"],\"canBack\":true,\"drawCount\":10,\"id\":\"BJPK10\",\"lotterySubType\":0,\"maxBall\":10,\"minBall\":1,\"name\":\"北京赛车\\u0028PK10\\u0029\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"PK10\",\"type\":1},{\"accountType\":0,\"balls\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"],\"canBack\":true,\"drawCount\":5,\"id\":\"CQSSC\",\"lotterySubType\":0,\"maxBall\":9,\"minBall\":0,\"name\":\"重庆时时彩\",\"repeatable\":true,\"sortResult\":false,\"tb\":0,\"template\":\"SSC\",\"type\":1},{\"accountType\":0,\"balls\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\"],\"canBack\":true,\"drawCount\":10,\"id\":\"PK10JSC\",\"lotterySubType\":4,\"maxBall\":10,\"minBall\":1,\"name\":\"极速赛车\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"PK10\",\"type\":1},{\"accountType\":0,\"balls\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"],\"canBack\":true,\"drawCount\":5,\"id\":\"SSCJSC\",\"lotterySubType\":4,\"maxBall\":9,\"minBall\":0,\"name\":\"极速时时彩\",\"repeatable\":true,\"sortResult\":false,\"tb\":0,\"template\":\"SSC\",\"type\":1},{\"accountType\":0,\"balls\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\"],\"canBack\":true,\"drawCount\":10,\"id\":\"LUCKYSB\",\"lotterySubType\":4,\"maxBall\":10,\"minBall\":1,\"name\":\"极速飞艇\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"PK10\",\"type\":1},{\"accountType\":0,\"balls\":[\"01\",\"02\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\"],\"canBack\":true,\"drawCount\":8,\"id\":\"XYNC\",\"lotterySubType\":0,\"maxBall\":20,\"minBall\":1,\"name\":\"重庆幸运农场\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"KLSF\",\"type\":1},{\"accountType\":0,\"balls\":[\"01\",\"02\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\"],\"canBack\":true,\"drawCount\":8,\"id\":\"GDKLSF\",\"lotterySubType\":0,\"maxBall\":20,\"minBall\":1,\"name\":\"广东快乐十分\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"KLSF\",\"type\":1},{\"accountType\":0,\"balls\":[\"01\",\"02\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\",\"21\",\"22\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"30\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"40\",\"41\",\"42\",\"43\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"50\",\"51\",\"52\",\"53\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"60\",\"61\",\"62\",\"63\",\"64\",\"65\",\"66\",\"67\",\"68\",\"69\",\"70\",\"71\",\"72\",\"73\",\"74\",\"75\",\"76\",\"77\",\"78\",\"79\",\"80\"],\"canBack\":true,\"drawCount\":20,\"id\":\"KL8\",\"lotterySubType\":0,\"maxBall\":80,\"minBall\":1,\"name\":\"北京快乐8\",\"repeatable\":false,\"sortResult\":true,\"tb\":0,\"template\":\"KL8\",\"type\":1},{\"accountType\":0,\"balls\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\"],\"canBack\":true,\"drawCount\":10,\"id\":\"XYFT\",\"lotterySubType\":0,\"maxBall\":10,\"minBall\":1,\"name\":\"幸运飞艇\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"PK10\",\"type\":1},{\"accountType\":0,\"balls\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\"],\"canBack\":true,\"drawCount\":5,\"id\":\"AULUCKY5\",\"lotterySubType\":3,\"maxBall\":9,\"minBall\":0,\"name\":\"澳洲幸运5\",\"repeatable\":true,\"sortResult\":false,\"tb\":0,\"template\":\"SSC\",\"type\":1},{\"accountType\":0,\"balls\":[\"01\",\"02\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\"],\"canBack\":true,\"drawCount\":8,\"id\":\"AULUCKY8\",\"lotterySubType\":3,\"maxBall\":20,\"minBall\":1,\"name\":\"澳洲幸运8\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"KLSF\",\"type\":1},{\"accountType\":0,\"balls\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\"],\"canBack\":true,\"drawCount\":10,\"id\":\"AULUCKY10\",\"lotterySubType\":3,\"maxBall\":10,\"minBall\":1,\"name\":\"澳洲幸运10\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"PK10\",\"type\":1},{\"accountType\":0,\"balls\":[\"01\",\"02\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"20\",\"21\",\"22\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"30\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"40\",\"41\",\"42\",\"43\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"50\",\"51\",\"52\",\"53\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"60\",\"61\",\"62\",\"63\",\"64\",\"65\",\"66\",\"67\",\"68\",\"69\",\"70\",\"71\",\"72\",\"73\",\"74\",\"75\",\"76\",\"77\",\"78\",\"79\",\"80\"],\"canBack\":true,\"drawCount\":20,\"id\":\"AULUCKY20\",\"lotterySubType\":3,\"maxBall\":80,\"minBall\":1,\"name\":\"澳洲幸运20\",\"repeatable\":false,\"sortResult\":false,\"tb\":0,\"template\":\"KL8\",\"type\":1}]},\"user\":{\"created\":1551006418000,\"gameEnable\":false,\"id\":\"hs008-laomeng666\",\"ip\":\"61.141.72.2\",\"ipAddress\":\"广东省深圳市龙岗区 电信\",\"lastLogin\":1552315266408,\"loginTime\":1552315266408,\"lv\":5,\"oid\":\"c3440b1ec1cf95c43852c2c729bc6b866d2ffef6\",\"online\":false,\"parent\":\"ak4455\",\"platform\":\"react\",\"range\":\"B\",\"resetType\":0,\"server\":\"2164492817-hs.cp168.ws\",\"shareMode\":0,\"status\":0,\"type\":1,\"updated\":1551201853000,\"userKey\":\"hs008\",\"username\":\"laomeng666\",\"userpass\":\"laomeng666\",\"wechatEnabled\":0}},\"status\":\"success\",\"statusCode\":0};";
	
		Gson gson = new Gson();
		Member msg =  gson.fromJson(result, Member.class);
		
		
		System.out.println(msg);
	
	}
	
	public static void calcResult()
	{
		Map<String,String> map = new HashMap<>();
		
		String d = "10,9,5,4,3,2,7,8,6,1";
		String[] r = d.split(",");
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
		
		String f = "";
		 for (Map.Entry<String, String> entry : map.entrySet()) {
     		f+=entry.getKey()+"|"+entry.getValue()+",";
		 }
		log.info(f);
	}
	
	public static void testUser() {
		String result = "{\"message\":\"成功检索\",\"result\":{\"changePassword\":false,\"created\":1551006418000,\"gameEnable\":false,\"id\":\"hs008-laomeng666\",\"ip\":\"113.87.12.192\",\"ipAddress\":\"广东省深圳市 电信\",\"lastLogin\":1552376719762,\"loginTime\":1552376719762,\"lv\":5,\"main\":true,\"oid\":\"15dffa24e739e6faa73662aa9841d29dd2673358\",\"online\":false,\"parent\":\"ak4455\",\"platform\":\"react\",\"range\":\"B\",\"resetType\":0,\"server\":\"2164492817-hs.cp168.ws\",\"shareMode\":0,\"type\":1,\"updated\":1551201853000,\"userKey\":\"hs008\",\"username\":\"laomeng666\",\"userpass\":\"laomeng666\",\"wechatEnabled\":0},\"status\":\"success\",\"statusCode\":0}";
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Date.class, new DateTypeAdapter())
		        .create();
		
		User msg =  gson.fromJson(result, User.class);
		
		System.out.println(msg);
	
	}
	
	
//  https://2164492817-hs.cp168.ws/web/rest/member/period?lottery=LUCKYSB
    
  	/**
  	 * 获取开奖时间
  	 */
  private static void getPeriod(){
	  Period info =   getEntity(Api.member_period,Period.class);
	  log.info(info.getResult().toString());
  }
  
//  https://2164492817-hs.cp168.ws/web/rest/member/odds?lottery=LUCKYSB
  /**
   * 获取最新的赔率
   */
  private static void getOdds(){
	  Odd info =   getEntity(Api.member_odds,Odd.class);
	  log.info(info.getResult().toString());
  }
  
 // https://2164492817-hs.cp168.ws/web/rest/member/lastResult?lottery=LUCKYSB
  private static void getLastResult()
  {
	  
	  LastOpenResult info =   getEntity(Api.member_lastResult, LastOpenResult.class);
	  log.info(info.getResult().toString());
	  
  }
  
  private static <T> T getEntity(String api,Class<T> s)
  {
	  	Map<String,String> param = new HashMap<>();
		param.put("token", token);
		String result =	HttpClientUtil.get(api,null,param);
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Date.class, new DateTypeAdapter())
		        .create();
	  return  gson.fromJson(result, s);
  }
	
	static String  token = "8a266a1150dbf8e83ac51d8566f80453f209dc5b";
	
	
}
