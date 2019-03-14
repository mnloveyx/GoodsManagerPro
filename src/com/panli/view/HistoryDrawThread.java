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
import com.panli.model.Api;
import com.panli.model.HistoryDraw;
import com.panli.model.OpenInfo;
import com.panli.model.Scheme;
import com.panli.util.DateTypeAdapter;
import com.panli.util.HttpClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HistoryDrawThread extends Thread{
	int i =0;
	
   private  JTable table;
   private  Scheme scheme;
	Long sleepTime = 60000L;
	
	DefaultTableModel model;
	
	private int cycle =0;
	
	 private boolean flag=true;
	
    @Override
    public void run() {
    	log.debug("start opendata history query"+Thread.currentThread().getName()+" currentTime:"+System.currentTimeMillis());
    	
    	try {
    		while(flag)
    		{
    			Map<String,String> param = new HashMap<>();
				param.put("token", token);
				String result =	HttpClientUtil.get(Api.member_dresult+scheme.getValue(),null,param);
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
					model.setRowCount(0);
					openInfos.forEach(record->{
						model.addRow(record.getRowData(model.getColumnCount()));
					});
				}
				
				Thread.sleep(sleepTime);
				if(cycle!=0)
				{
					this.flag = false;
				}
				i++;
    		}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	private String token;
	public HistoryDrawThread(JTable table, Scheme scheme,String token) {
		this(table, scheme,token,0);
	}
	
	public HistoryDrawThread(JTable table, Scheme scheme, String token, int cycle) {
		super();
		this.table = table;
		this.scheme = scheme;
		this.cycle = cycle;
		this.token = token;
		this.model = ((DefaultTableModel) table.getModel());
	}
    
    
    
}
