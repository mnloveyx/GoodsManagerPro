package com.amuse.view;

import java.awt.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.HashAttributeSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.amuse.model.Bet;
import com.amuse.model.LastOpenResult;
import com.amuse.model.Odd;
import com.amuse.model.OpenInfo;
import com.amuse.model.Period;
import com.amuse.model.Placebet;
import com.amuse.model.Plan;
import com.amuse.model.Record;
import com.amuse.model.Result;
import com.amuse.model.Statis;
import com.amuse.util.Api;
import com.amuse.util.DateTypeAdapter;
import com.amuse.util.DateUtils;
import com.amuse.util.HttpClientUtil;
import com.amuse.util.ReflexObjectUtil;
import com.amuse.util.SubjectUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public  class  PlaceAction  implements Runnable{
	
	 private boolean flag=true;
	
	private int i = 0;
	
	private Plan plan ;
	
	private List<Record> records = new ArrayList<>();
	
	private Record record = new Record();
	
	private JTable table;
	
//	Long sleepTime = 60000L;
	
	private Long startPlaceTime = System.currentTimeMillis();
	
	private Long placeRunTime =  System.currentTimeMillis();
	
	private String currentLine ;
	
	private Statis  statis ;
	
	 private int round=1;
	 
	 private int planTotalCount=0;
	 
	 private Integer realCount=0; //真实投注次数
	  private BigDecimal realAmount=new BigDecimal(0);  //真实盈亏
	  private Integer virtualCount=0; //虚拟投注次数
	  private BigDecimal virtualAmount= new BigDecimal(0);  //虚拟盈亏
	  private String realWinPercent;  //准确率
	  
	  private DefaultTableModel tableModel;
	  
	  private String placeStatus;  //投注状态
//	  private String drawNumber;  //投注基数
	  
	  private int  continueWin=0; //连中
	  
	  private int continueLost=0; //连挂
	  
	  private int  continueWinMax=0; //连中
	  
	  private int continueLostMax=0; //连挂
	  
	  private int wincount=0; //赢次
	  
//	  private  ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	  private  ScheduledExecutorService executor;
	  
	  private Long restOpenTime = 0L;
	  
	  private Long  restPlaceTime=0L;
	 @Override
	 public void run() {
		 try { 
		 Future<Long> restTime = executor.submit(new Callable<Long>() {
			 public Long call() throws Exception {
				 Period p = getPeriod();
				 if(p!=null)
				 {
					 period = p;
				 }
//				 Odd  o =	getOdds();
//				 if(o!=null)
//				 {
//					 odd = o;
//				 }
//				 if("开某投某".equalsIgnoreCase(plan.getType()))
//				 {
//					 openInfo  = getLastResult();
//					 log.debug("pre plantype,opendata:{}",openInfo.toString());
//				 }
				 restOpenTime = p.getRestOpenTime();
				 
				 return p.getRestPlaceTime();
			 };}
		 );
		
		restPlaceTime  = restTime.get();
		 
		 if(restPlaceTime<0)
		 {
			 log.debug("3333restPlaceTime:{},currentTime:{}",restPlaceTime,System.currentTimeMillis());
			 restPlaceTime = Math.abs(restPlaceTime);
				
		 }else if(restPlaceTime>5000L){
			 restPlaceTime = 1000L;
		 }
		 repaintPanelData("placeStatus", "等待投注");
		 Thread.sleep(restPlaceTime);
//		 executor.scheduleWithFixedDelay(new Runnable() {
		 ScheduledFuture<?> future = executor.scheduleAtFixedRate(new Runnable() {
					@Override
					public void run() {
						
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
							 if("开某投某".equalsIgnoreCase(plan.getType()))
							 {
								 openInfo  = getLastResult();
								 log.debug("pre plantype,opendata:{}",openInfo.toString());
//								 Thread.sleep(500);
							 }
							 restPlaceTime = p.getRestPlaceTime();
							 restOpenTime  =p.getRestOpenTime();
							 
							 //1.表格没有数据 1.已经投注过不再投注。2.未开奖不再投注。3投注时间必须大于1秒
							 
							if((tableModel.getRowCount()==0||(!p.getDrawNumber().equalsIgnoreCase(tableModel.getValueAt(0,2).toString())) || StringUtils.isNoneBlank((String)tableModel.getValueAt(0, 8))) &&restPlaceTime>1000L)
							 {
								log.info("start place========");
								record = new Record(plan);
								//投注
								Placebet  placebet = new Placebet();
								int amount = plan.getPlanPlaceAmountsList().size();
								if(CollectionUtils.isEmpty(plan.getStartContents()))
								{
//									plan.setType(plan.getType());
									plan.setPlaceInfo(plan.getType(), openInfo.getResult());
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
									if(StringUtils.isBlank(result))
									{
										log.debug("place fail stop place action!!!!");
										repaintPanelData("placeStatus", "投注失败");
									}
									
									realCount++;
								}
								planTotalCount++;
								log.info("placecomplete========");
								statis  = SubjectUtils.getStatis();
								record.setRound(String.valueOf(round));
								record.setRound(String.valueOf(round));
								record.setPlanTotalRound(String.valueOf(planTotalCount));
								placeStatus = "等待开奖";
								repaintPanelData("placeStatus", "等待开奖");
								record.setIsWin(placeStatus);
								
//								tableModel.addRow(record.getRowData(tableModel.getColumnCount()));
								tableModel.insertRow(0,record.getRowData(tableModel.getColumnCount()));
							 }
							log.debug("restPlaceTime:{},currentTime:{}",restPlaceTime,System.currentTimeMillis());
							if(restPlaceTime<0) {
								log.debug("2222restPlaceTime:{},currentTime:{}",restPlaceTime,System.currentTimeMillis());
								restPlaceTime = Math.abs(restPlaceTime);
								repaintPanelData("placeStatus", "等待投注");
							}
							Thread.sleep(restPlaceTime+10000);
							
							openInfo = 	getLastResult();
							if(record.getPlacebet()==null)
							{
								log.debug("return in here");
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
							//计算连续数据
							if(record.getWin())
							{
								continueWin++;
								continueLost=0;
								wincount++;
								if(continueWin>continueWinMax)
								{
									continueWinMax = continueWin;
								}
							}else {
								continueLost++;
								continueWin=0;
								if(continueLost>continueLostMax)
								{
									continueLostMax = continueLost;
								}
							}
							if(Api.placeType_0.equalsIgnoreCase(record.getPlaceType()))
							{
								setVirtualAmount(virtualAmount.add(record.getResultAmount()));
							}else {
								setRealAmount(realAmount.add(record.getResultAmount()));
							}
							
							plan.setContinueLost(continueLost);
							plan.setContinueWin(continueWin);
							
							//再次刷新表格
//							tableModel.removeRow(tableModel.getRowCount()-1);
//							tableModel.addRow(record.getRowData(tableModel.getColumnCount()));
							if(tableModel.getRowCount()>0)
							{
								tableModel.removeRow(0);
							}
							//此处应该添加行数据匹配查找
							tableModel.insertRow(0,record.getRowData(tableModel.getColumnCount()));
//							records.add(record);
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
//								if(DateUtils.addMinutes(new Date(placeRunTime),4).getTime()>d)
//								Long addPlaceRunTime = DateUtils.addHours(new Date(placeRunTime),4).getTime();
//								if(addPlaceRunTime<d)
//								{
//									log.debug("interrupt stop place,addPlaceRunTime:{},currentTime:{}",addPlaceRunTime,d);
//									return ;
//								}
							}else{
								round++;
							}
							//切换选球
//							plan.setType(plan.getType());
							 
							plan.setPlaceInfo(plan.getType(),openInfo.getResult());
							
							log.debug("this is staticInfo:{}",getStaticString());
							statis = new Statis(String.valueOf(realCount), String.valueOf(realAmount), String.valueOf(virtualCount), String.valueOf(virtualAmount), String.valueOf(0), String.valueOf(planTotalCount), String.valueOf(placeStatus), getRealWinPercent(), String.valueOf(continueWinMax), String.valueOf(continueLostMax), String.valueOf(wincount));
							repaintPanelData();
							repaintPanelData("placeStatus", "等待投注");
//							Thread.sleep(10000L);
							
						} catch (InterruptedException e) {
							repaintPanelData("placeStatus", "等待投注");
							log.warn(" schedule runing  thread interrupt:{}",e.getMessage());
						}
					 }
						
				}, 0, restOpenTime, TimeUnit.MILLISECONDS);
		 
		 try {
			    future.get().toString();
			} catch (ExecutionException e) {
			    Throwable cause = e.getCause();
			    cause.printStackTrace();
			}
		 
		 
		 }catch (Exception e) {
			 repaintPanelData("placeStatus", "等待投注");
			 log.warn("scheduleAtFixedRate schedule thread interrupt:{}",e.getMessage());
		}
		
//		 while(flag)
//		 {
//			 try {
//				 Period p = getPeriod();
//				 if(p!=null)
//				 {
//					 period = p;
//				 }
//				 Odd  o =	getOdds();
//				 if(o!=null)
//				 {
//					 odd = o;
//				 }
//				 if("开某投某".equalsIgnoreCase(plan.getType()))
//				 {
//					 openInfo  = getLastResult();
//					 log.debug("pre plantype,opendata:{}",openInfo.toString());
//				 }
//				 Long restPlaceTime = p.getRestPlaceTime();
//				if(!p.getDrawNumber().equalsIgnoreCase(record.getDrawNumber()) &&restPlaceTime>1000L)
//				 {
//					log.info("start place========");
//					record = new Record(plan);
//					//投注
//					Placebet  placebet = new Placebet();
//					int amount = plan.getPlanPlaceAmountsList().size();
//					if(CollectionUtils.isEmpty(plan.getStartContents()))
//					{
////						plan.setType(plan.getType());
//						plan.setPlaceInfo(plan.getType(), openInfo.getResult());
//					}
//					log.info("place plan choose========");
//					log.debug("place plan choose========{}",plan.toLogString());
//					if(CollectionUtils.isNotEmpty(plan.getStartContents()))
//					{
//						int i = (round-1)%amount;
//						log.debug("getPlaceAmount:{}",String.valueOf(i));
//						plan.getStartContents().forEach(c->{
//							Bet bet = new Bet(Integer.valueOf(plan.getPlanPlaceAmountsList().get(i)),c, plan.getStartGame(),Double.valueOf(ReflexObjectUtil.getValueByKey(o, plan.getContents()).toString()));
//							placebet.getBets().add(bet);
//							
//						});
//					}
//					placebet.setDrawTime(new Date());
//					placebet.setDrawNumber(p.getDrawNumber());
//					placebet.setLottery(plan.getSchemeValue());
//					log.debug("placebetInfo:{}",placebet.toString());
//					record.setPlacebet(placebet);
//					if(Api.placeType_0.equalsIgnoreCase(plan.getPlaceType())){
//						log.info("startVirtualPlace==============");
//						record.setPlaceType(Api.placeType_0);
//						virtualCount++;
//					}else{
//						record.setPlaceType(Api.placeType_1);
//						log.info("startRealPlace==============");
//						Map<String,String> param = new HashMap<>();
//						param.put("token", token);
//						Gson gson = new GsonBuilder()
//								.registerTypeAdapter(Date.class, new DateTypeAdapter())
//								.create();
//						String placeBetStr = gson.toJson(placebet);
//						String result =	HttpClientUtil.post(Api.placebet,placeBetStr,param);
//						log.debug("realPlaceResult:{}",result);
//						Result r =   gson.fromJson(result, Result.class);
//						if(StringUtils.isBlank(result))
//						{
//							log.debug("place fail stop place action!!!!");
//							repaintPanelData("placeStatus", "投注失败");
//						}
//						
//						realCount++;
//					}
//					planTotalCount++;
//					log.info("placecomplete========");
//					statis  = SubjectUtils.getStatis();
//					record.setRound(String.valueOf(round));
//					record.setRound(String.valueOf(round));
//					record.setPlanTotalRound(String.valueOf(planTotalCount));
//					placeStatus = "等待开奖";
//					record.setIsWin(placeStatus);
//					
////					tableModel.addRow(record.getRowData(tableModel.getColumnCount()));
//					tableModel.insertRow(0,record.getRowData(tableModel.getColumnCount()));
//				 }
//				log.debug("restPlaceTime:{},currentTime:{}",restPlaceTime,System.currentTimeMillis());
//				if(restPlaceTime<0) {
//					restPlaceTime = Math.abs(restPlaceTime);
//					repaintPanelData("placeStatus", "等待投注");
//				}
//				Thread.sleep(restPlaceTime+10000);
//				
//				openInfo = 	getLastResult();
//				if(record.getPlacebet()==null)
//				{
//					return;
//				}
//				if(!openInfo.getDrawNumber().equalsIgnoreCase(record.getPlacebet().getDrawNumber()))
//				{
//					Thread.sleep(10000);
//					openInfo = getLastResult();
//					
//					log.info("drawNumbers:{},placeNumbers:{}",openInfo.getDrawNumber(),record.getPlacebet().getDrawNumber());
//				}
//				
//				record.setOpenInfo(openInfo);
//				record.calc();
//				//计算连续数据
//				if(record.getWin())
//				{
//					continueWin++;
//					continueLost=0;
//					if(continueWin>continueWinMax)
//					{
//						continueWinMax = continueWin;
//					}
//					wincount++;
//				}else {
//					continueLost++;
//					continueWin=0;
//					if(continueLost>continueLostMax)
//					{
//						continueLostMax = continueWin;
//					}
//				}
//				if(Api.placeType_0.equalsIgnoreCase(record.getPlaceType()))
//				{
//					setVirtualAmount(virtualAmount.add(record.getResultAmount()));
//				}else {
//					setRealAmount(realAmount.add(record.getResultAmount()));
//				}
//				
//				plan.setContinueLost(continueLost);
//				plan.setContinueWin(continueWin);
//				
//				//再次刷新表格
////				tableModel.removeRow(tableModel.getRowCount()-1);
////				tableModel.addRow(record.getRowData(tableModel.getColumnCount()));
//				if(tableModel.getRowCount()>0)
//				{
//					tableModel.removeRow(0);
//				}
//				tableModel.insertRow(0,record.getRowData(tableModel.getColumnCount()));
////				records.add(record);
//				//跳转线路
//				if(record.getWin())
//				{
//					round = 1;
//					Long d = System.currentTimeMillis();
//					log.info("placeStartTime：startPlaceTime:{},currentTime:{},placeSettingTime:{} minute",new Date(startPlaceTime),new Date(d),plan.getStartTime());
//					if((d-startPlaceTime) >Long.valueOf(plan.getStartTime())*60000)
//					{
//						log.debug("placeStartTime：startPlaceTime:{},currentTime:{},placeSettingTime:{} minute",new Date(startPlaceTime),new Date(),plan.getStartTime());
//						log.info("start change line========");
//						startPlaceTime = System.currentTimeMillis();
//						plan.nextLine();
//						
//					}
////					if(DateUtils.addMinutes(new Date(placeRunTime),4).getTime()>d)
//					Long addPlaceRunTime = DateUtils.addHours(new Date(placeRunTime),4).getTime();
//					if(addPlaceRunTime<d)
//					{
//						log.debug("interrupt stop place,addPlaceRunTime:{},currentTime:{}",addPlaceRunTime,d);
//						return ;
//					}
//					
//					
//				}else{
//					round++;
//					continueLost++;
//					continueWin=0;
//				}
//				//切换选球
////				plan.setType(plan.getType());
//				plan.setPlaceInfo(plan.getType(),openInfo.getResult());
//				
//				log.debug("this is staticInfo:{}",this.getStaticString());
//				this.statis = new Statis(String.valueOf(realCount), String.valueOf(realAmount), String.valueOf( virtualCount), String.valueOf( virtualAmount), String.valueOf(0), String.valueOf( planTotalCount), String.valueOf( placeStatus), getRealWinPercent(), String.valueOf( continueWinMax), String.valueOf( continueLostMax), String.valueOf( wincount));
//				repaintPanelData();
//				repaintPanelData("placeStatus", "等待投注");
//				Thread.sleep(10000L);
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 }
	 }
	 
    
    private Period period;
    private Odd odd;
    
    private OpenInfo openInfo = new OpenInfo();
    
	/**
  	 * getlastPeriodTime
	 * @return 
  	 */
  private  Period getPeriod(){
	  Period info =   getEntity(Api.member_period+plan.getSchemeValue(),Period.class);
	  log.info(info.getResult().toString());
	  return info.getResult();
  }
  
  public String getRealWinPercent()
  {
	  try {
		  return new BigDecimal(wincount).divide(new BigDecimal(planTotalCount),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue()+"%";
	  }catch (Exception e) {
		return "0%";
	}
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
	  Gson gson = new GsonBuilder()
			  .registerTypeAdapter(Date.class, new DateTypeAdapter())
			  .create();
	  try {
		  String result =	HttpClientUtil.get(api,null,param);
		return  gson.fromJson(result, s);
	} catch (Exception e) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.error("getEntity error:{},api:{}",e.getMessage(),api);
		 String result =	HttpClientUtil.get(api,null,param);
			return  gson.fromJson(result, s);
	}
//	  return  gson.fromJson(result, s);
  }
	
	private  String  token;
	
    
	public PlaceAction(JTable table, Plan plan,String token) {
		super();
		this.plan = plan;
		this.token = token;
		this.table = table;
		this.tableModel = (DefaultTableModel) table.getModel();
	}

	public String getStaticString() {
		return "planTotalCount=" + planTotalCount + ", realCount=" + realCount + ", realAmount=" + realAmount
				+ ", virtualCount=" + virtualCount + ", virtualAmount=" + virtualAmount + ", continueWinMax="
				+ continueWinMax + ", continueLostMax=" + continueLostMax+",realWinPercent="+getRealWinPercent()+",wincount="+wincount;
	}
	
	
	
	JPanel panel;
	
	Component[] cb ;
	
	Map<String,Component> componentsMap = new HashMap<>();
	
	public PlaceAction(JPanel panel,JTable table, Plan plan,String token,ScheduledExecutorService executor) {
		super();
		this.plan = plan;
		this.token = token;
		this.table = table;
		this.tableModel = (DefaultTableModel) table.getModel();
		this.panel = panel;
		this.cb = panel.getComponents();
		setComponentsMap(panel.getComponents());
		this.executor = executor;
	}
	
//	private Map<String,>
	
	private void setComponentsMap(Component[] cb)
	{
		if(cb!=null && cb.length>0)
		{
			componentsMap = new HashMap<>();
			for (Component component : cb) {               //遍历
				String name = component.getName();
		    	if(StringUtils.isNotBlank(name))
		    	{
		    		componentsMap.put(name,component);
		    	}
	      	}
		}
	}
	
	
	public void repaintPanelData()
	{
		for (Component component : cb) {               //遍历
		      if(component instanceof JLabel)
		      {
		    	  JLabel cb = (JLabel) component;   //强制转换
		    		String name = cb.getName();
		    		if(StringUtils.isNoneBlank(name))
		    		{
		    			log.debug("repaintPanelData component name:{}",name);
		    			cb.setText(ReflexObjectUtil.getValueByKey(this.statis,name).toString());
		    		}
		      }
	      	}
	}
	
	public void repaintPanelData(String componentName,String value)
	{
		Component c = componentsMap.get(componentName);
		if(c!=null)
		{
			if(c instanceof JLabel)
		      {
				 JLabel cb = (JLabel) c;   //强制转换
				 cb.setText(value);
		      }
		}else {
			log.debug("can not find component name:{},will set component text:{}",componentName,value);
		}
	} 
}
