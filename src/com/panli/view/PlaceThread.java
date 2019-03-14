package com.panli.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.collections.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.panli.model.Api;
import com.panli.model.Odd;
import com.panli.model.OpenInfo;
import com.panli.model.Period;
import com.panli.model.Placebet;
import com.panli.model.Bet;
import com.panli.model.LastOpenResult;
import com.panli.model.Plan;
import com.panli.model.Record;
import com.panli.model.Result;
import com.panli.model.Statis;
import com.panli.util.DateTypeAdapter;
import com.panli.util.HttpClientUtil;
import com.panli.util.ReflexObjectUtil;
import com.panli.util.SubjectUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public  class  PlaceThread  extends Thread {
	
	 private boolean flag=true;
	
	private int i = 0;
	
	private Plan plan ;
	
	private List<Record> records = new ArrayList<>();
	
	private Record record = new Record();
	
	private JTable table;
	
	Long sleepTime = 60000L;
	
	private Long startPlaceTime = new Date().getTime();
	
	private String currentLine ;
	
	private Statis  statis ;
	
	 private int round=1;
	 
	 private int totalRound=0;
	 
	 private Integer realCount=0; //真实投注次数
	  private Double realAmount=0D;  //真实盈亏
	  private Integer virtualCount=0; //虚拟投注次数
	  private Double virtualAmount=0D;  //虚拟盈亏
	  
	  private Record preRecord = new Record();
	  
	  private DefaultTableModel tableModel;
	  
//	  private String placeStatus;  //投注状态
//	  private String winPercent;  //准确率
//	  private String drawNumber;  //投注基数
	  
	  private int  continueWin; //连中
	  
	  private int continueLost; //连挂
	
	 @Override
	 public void run() {
		 while(flag)
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
				 Long restPlaceTime = p.getRestTime();
				if(!p.getDrawNumber().equalsIgnoreCase(record.getDrawNumber()) &&restPlaceTime>1000L)
				 {
					log.info("start place========");
					record = new Record();
					record.setPlan(plan);
					//投注
					Placebet  placebet = new Placebet();
					int amount = plan.getPlanPlaceAmountsList().size();
					if(CollectionUtils.isEmpty(plan.getStartContents()))
					{
						plan.setType(plan.getType());
					}
					log.info("place plan choose========");
					log.debug("place plan choose========{}",plan.toLogString());
					if(CollectionUtils.isNotEmpty(plan.getStartContents()))
					{
						int i = (round-1)%amount;
						log.debug("getPlaceAmount:{}",String.valueOf(i));
						plan.getStartContents().forEach(c->{
							Bet bet = new Bet(Integer.valueOf(plan.getPlanPlaceAmountsList().get(i)),c, plan.getStartGame(),Double.valueOf(ReflexObjectUtil.getValueByKey(o, plan.getContents()).toString()));
							placebet.getBets().add(bet);
							
						});
					}
					placebet.setDrawTime(new Date());
					placebet.setDrawNumber(p.getDrawNumber());
					placebet.setLottery(plan.getSchemeValue());
					log.debug("placebetInfo:{}",placebet.toString());
					record.setPlacebet(placebet);
					if(Api.placeType_0.equalsIgnoreCase(plan.getPlaceType())){
						log.info("startVirtualPlace==============");
						record.setPlaceType(Api.placeType_0);
						virtualCount++;
//	  							statis.set
					}else{
						record.setPlaceType(Api.placeType_1);
						log.info("startRealPlace==============");
						Map<String,String> param = new HashMap<>();
						param.put("token", token);
						Gson gson = new GsonBuilder()
								.registerTypeAdapter(Date.class, new DateTypeAdapter())
								.create();
						String placeBetStr = gson.toJson(placebet);
						String result =	HttpClientUtil.post(Api.placebet,placeBetStr,param);
						log.debug("realPlaceResult:{}",result);
						Result r =   gson.fromJson(result, Result.class);
						realCount++;
					}
					totalRound++;
					log.info("placecomplete========");
					statis  = SubjectUtils.getStatis();
					record.setRound(String.valueOf(round));
					record.setIsWin("等待开奖");
//					tableModel.addRow(record.getRowData(tableModel.getColumnCount()));
					tableModel.insertRow(0,record.getRowData(tableModel.getColumnCount()));
				 }
				log.debug("restPlaceTime:{},currentTime:{}",restPlaceTime,System.currentTimeMillis());
				if(restPlaceTime<0) return;
				Thread.sleep(p.getRestTime()+10000);
				
				openInfo = 	getLastResult();
				if(record.getPlacebet()==null)
				{
					return;
				}
				if(!openInfo.getDrawNumber().equalsIgnoreCase(record.getPlacebet().getDrawNumber()))
				{
					Thread.sleep(10000);
					openInfo = getLastResult();
					
					log.info("drawNumbers:{},placeNumbers:{}",openInfo.getDrawNumber(),record.getPlacebet().getDrawNumber());
				}
				
				record.setOpenInfo(openInfo);
				record.calc();
				//再次刷新表格
//				tableModel.removeRow(tableModel.getRowCount()-1);
//				tableModel.addRow(record.getRowData(tableModel.getColumnCount()));
				if(tableModel.getRowCount()>0)
				{
					tableModel.removeRow(0);
				}
				tableModel.insertRow(0,record.getRowData(tableModel.getColumnCount()));
//				records.add(record);
				//跳转线路
				if(record.getWin())
				{
					round = 1;
					Long d = System.currentTimeMillis();
					log.info("placeStartTime：startPlaceTime:{},currentTime:{},placeSettingTime:{} minute",new Date(startPlaceTime),new Date(d),plan.getStartTime());
					if((d-startPlaceTime) >Long.valueOf(plan.getStartTime())*60000)
					{
						log.debug("placeStartTime：startPlaceTime:{},currentTime:{},placeSettingTime:{} minute",new Date(startPlaceTime),new Date(),plan.getStartTime());
						log.info("start change line========");
						startPlaceTime = System.currentTimeMillis();
						plan.nextLine();
					}
				}else{
					round++;
				}
				//切换选球
				plan.setType(plan.getType());
				
				Thread.sleep(10000L);
				
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
  	 * getlastPeriodTime
	 * @return 
  	 */
  private  Period getPeriod(){
	  Period info =   getEntity(Api.member_period+plan.getSchemeValue(),Period.class);
	  log.info(info.getResult().toString());
	  return info.getResult();
  }
  
//  https://2164492817-hs.cp168.ws/web/rest/member/odds?lottery=LUCKYSB
  /**
   * 获取最新的赔率
   */
  private Odd  getOdds(){
	  Odd info =   getEntity(Api.member_odds+plan.getSchemeValue(),Odd.class);
	  log.info(info.getResult().toString());
	  return info.getResult();
  }
  
 // https://2164492817-hs.cp168.ws/web/rest/member/lastResult?lottery=LUCKYSB
  private  OpenInfo getLastResult()
   {
	  LastOpenResult info =   getEntity(Api.member_lastResult+plan.getSchemeValue(), LastOpenResult.class);
	  return info.getResult();
	  
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
		this.tableModel = (DefaultTableModel) table.getModel();
	}
	
}
