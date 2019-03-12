package com.panli.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.panli.model.Api;
import com.panli.model.Odd;
import com.panli.model.OpenInfo;
import com.panli.model.Period;
import com.panli.model.Placebet;
import com.panli.model.Bet;
import com.panli.model.Plan;
import com.panli.model.Record;
import com.panli.model.Result;
import com.panli.model.Statis;
import com.panli.util.DateTypeAdapter;
import com.panli.util.HttpClientUtil;
import com.panli.util.ReflexObjectUtil;
import com.panli.util.SubjectUtils;
import com.sun.beans.editors.IntegerEditor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class PlaceThread  extends Thread {
	
	 private boolean flag=true;
	
	private int i = 0;
	
	private Plan plan ;
	
	private List<Record> records;
	
	private Record record = new Record();
	
	private JTable table;
  
    private Object[] gridHeader = new String[] {
			"\u6295\u6CE8\u5F69\u79CD", "\u6295\u6CE8\u65F6\u95F4", "\u6295\u6CE8\u671F\u6570", "\u65B9\u6848", "\u73A9\u6CD5", "\u91D1\u989D", "\u76C8\u4E8F", "\u6295\u6CE8", "\u5F00\u5956\u53F7\u7801", "\u8F6E\u6B21", "\u72B6\u6001", "\u4E2D\u6302", "\u8FDE\u6302", "\u8FDE\u4E2D", "\u65B9\u6848\u76C8\u4E8F"
		};
	Long sleepTime = 60000L;
	
	private Long startPlaceTime = System.currentTimeMillis();
	
	private String currentLine ;
	
	private Statis  statis ;
	
	
	

//    @Override
//    public void run() {
//    	log.debug("启动线程:"+Thread.currentThread().getName()+"当前时间:"+System.currentTimeMillis());
//    	try {
//    		while(flag)
//    		{
//    			Record record = new Record(plan);
//    			record.setDrawNumber(String.valueOf(Long.valueOf(51631557L)+i));
//    			plan.setCurrentLine("悄好");
//    			
//    			((DefaultTableModel) table.getModel()).addRow(record.getRowData(gridHeader.length));
//    			
//    			PlaceThread.sleep(sleepTime);
//    			i++;
//    		}
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
	
	 private int round=1;
	 
	 private int totalRound=0;
	 
//	 private int  
	 
	 private Integer realCount=0; //真实投注次数
	  private Double realAmount=0D;  //真实盈亏
	  private Integer virtualCount=0; //虚拟投注次数
	  private Double virtualAmount=0D;  //虚拟盈亏
	  
	  private Record preRecord = new Record();
	  
//	  private String placeStatus;  //投注状态
//	  private String winPercent;  //准确率
//	  private String drawNumber;  //投注基数
	  
//	  private String continueWin; //连中
	  
//	  private String continueLost; //连挂
	
	 @Override
	 public void run() {
		 while(true)
		 {
			 try {
				 Period p = getPeriod();
				 if(p!=null)
				 {
					 period = p;
				 }
				 Odd  o =	getOdds();
				 if(o!=null)
				 {
					 odd = o;
				 }
				DefaultTableModel dt = ((DefaultTableModel) table.getModel());
				 
				if(!p.getDrawNumber().equalsIgnoreCase(record.getDrawNumber()) &&p.getRestTime()>1000L)
				 {
					log.info("开始投注========");
					record = new Record();
					startPlaceTime = System.currentTimeMillis();
					//投注
					Placebet  placebet = new Placebet();
					int amount = plan.getAmountsList().size();
					plan.setType(plan.getType());
					if(CollectionUtils.isNotEmpty(plan.getStartContents()))
					{
						plan.getStartContents().forEach(c->{
							Bet bet = new Bet(Integer.valueOf(plan.getAmountsList().get(round%amount)),c, plan.getStartGame(),Double.valueOf(ReflexObjectUtil.getValueByKey(o, plan.getStartGame()+"_"+plan.getCurrentLine()).toString()));
							placebet.getBets().add(bet);
							
						});
					}
					placebet.setDrawTime(new Date());
					placebet.setDrawNumber(p.getDrawNumber());
					placebet.setLottery(plan.getSchemeValue());
					log.info("placebetInfo:{}",placebet.toString());
					record.setPlacebet(placebet);
					if(Api.placeType_0.equalsIgnoreCase(plan.getPlaceType())){
						record.setPlaceType(Api.placeType_0);
						virtualCount++;
//	  							statis.set
					}else{
						record.setPlaceType(Api.placeType_1);
						log.warn("这里是真实提交，敬请期待");
//	  							Map<String,String> param = new HashMap<>();
//	  							param.put("token", token);
//	  							String result =	HttpClientUtil.get(Api.placebet,null,param);
//	  							Gson gson = new GsonBuilder()
//	  							        .registerTypeAdapter(Date.class, new DateTypeAdapter())
//	  							        .create();
//	  							Result r =   gson.fromJson(result, Result.class);
						realCount++;
					}
					totalRound++;
					log.info("始投注完成========");
					statis  = SubjectUtils.getStatis();
					record.setRound(String.valueOf(totalRound));
					record.setIsWin("等待开奖");
					dt.addRow(record.getRowData(gridHeader.length));
				 }
				Thread.sleep(p.getRestTime()+1000);
				
				openInfo = 	getLastResult();
				if(!openInfo.getDrawNumber().equalsIgnoreCase(record.getPlacebet().getDrawNumber()))
				{
					Thread.sleep(1000);
					openInfo = getLastResult();
				}
				
				record.setOpenInfo(openInfo);
				record.calc();
				//切换选球
				plan.setType(plan.getType());
				if(record.getWin())
				{
					round = 1;
					Long d = new Date().getTime();
					if((d-startPlaceTime) >Long.valueOf(plan.getStartTime())*1000)
					{
						log.info("开始换线========");
						startPlaceTime = System.currentTimeMillis();
						plan.nextLine();
					}
				}{
					round++;
				}
				
				//再次刷新表格
				dt.removeRow(dt.getRowCount()-1);
				dt.addRow(record.getRowData(gridHeader.length));
				records.add(record);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
	 }
    
    private Period period;
    private Odd odd;
    
    private OpenInfo openInfo;
    
	/**
  	 * 获取开奖时间
	 * @return 
  	 */
  private  Period getPeriod(){
	  Period info =   getEntity(Api.member_period,Period.class);
	  log.info(info.getResult().toString());
	  return info.getResult();
  }
  
//  https://2164492817-hs.cp168.ws/web/rest/member/odds?lottery=LUCKYSB
  /**
   * 获取最新的赔率
   */
  private Odd  getOdds(){
	  Odd info =   getEntity(Api.member_odds,Odd.class);
	  log.info(info.getResult().toString());
	  return info.getResult();
  }
  
 // https://2164492817-hs.cp168.ws/web/rest/member/lastResult?lottery=LUCKYSB
  private  OpenInfo getLastResult()
  {
	  
	  OpenInfo info =   getEntity(Api.member_lastResult, OpenInfo.class);
	  log.info(info.toString());
	  return info;
	  
  }
  
  private  <T> T getEntity(String api,Class<T> s)
  {
	  	Map<String,String> param = new HashMap<>();
		param.put("token", token);
		String result =	HttpClientUtil.get(api,null,param);
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Date.class, new DateTypeAdapter())
		        .create();
	  return  gson.fromJson(result, s);
  }
	
	private  String  token;
	
    
	public PlaceThread(JTable table, Plan plan,String token) {
		super();
		this.plan = plan;
		this.token = token;
		this.table = table;
	}
	
}
