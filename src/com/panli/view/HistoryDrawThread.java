package com.panli.view;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.panli.model.Account;
import com.panli.model.Api;
import com.panli.model.HistoryDraw;
import com.panli.model.Member;
import com.panli.model.OpenInfo;
import com.panli.model.Plan;
import com.panli.model.Record;
import com.panli.model.Statis;
import com.panli.util.DateTypeAdapter;
import com.panli.util.HttpClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HistoryDrawThread extends Thread{
	int i =0;
	private Object [][]gridData=null;
    private Object[] gridHeader = new String[] {
			"\u6295\u6CE8\u5F69\u79CD", "\u6295\u6CE8\u65F6\u95F4", "\u6295\u6CE8\u671F\u6570", "\u65B9\u6848", "\u73A9\u6CD5", "\u91D1\u989D", "\u76C8\u4E8F", "\u6295\u6CE8", "\u5F00\u5956\u53F7\u7801", "\u8F6E\u6B21", "\u72B6\u6001", "\u4E2D\u6302", "\u8FDE\u6302", "\u8FDE\u4E2D", "\u65B9\u6848\u76C8\u4E8F"
		};
    
   private  JTable table;
   private  Plan plan;
	Long sleepTime = 60000L;
	
    @Override
    public void run() {
    	log.debug("启动线程:"+Thread.currentThread().getName()+"当前时间:"+System.currentTimeMillis());
    	
    	try {
    		while(true)
    		{
    			Map<String,String> param = new HashMap<>();
				param.put("token", token);
				String result =	HttpClientUtil.get(Api.member_dresult,null,param);
				if (StringUtils.isBlank(result)) {
					return;
				}
				 Gson gson = new GsonBuilder()
					        .registerTypeAdapter(Date.class, new DateTypeAdapter())
					        .create();
				HistoryDraw msg =  gson.fromJson(result, HistoryDraw.class);
				List<OpenInfo> openInfos = msg.getResult().getList();
				if(CollectionUtils.isNotEmpty(openInfos))
				{
					DefaultTableModel model =	((DefaultTableModel) table.getModel());
					model.setRowCount(0);
					openInfos.forEach(record->{
						model.addRow(record.getRowData(gridHeader.length));
					});
				}
				
				Thread.sleep(sleepTime);
				i++;
    		}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	private String token;
	public HistoryDrawThread(JTable table, Plan plan,String token) {
		super();
		this.table = table;
		this.plan = plan;
		this.token = token;
	}
    
    
    
}
