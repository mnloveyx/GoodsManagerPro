package com.panli.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.CollectionUtils;

import com.google.gson.Gson;
import com.panli.model.Account;
import com.panli.model.Api;
import com.panli.model.LoginMsg;
import com.panli.model.Member;
import com.panli.model.Record;
import com.panli.util.HttpClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountThread extends Thread{
	
	private JLabel account_type_0; 
		
	private JLabel account_type_1;
	
	private JLabel account_type_2;
	
	private String token;
	
	Long sleepTime = 60000L;
	
	int i =0;
	  @Override
	    public void run() {
	    	log.debug("启动线程:"+Thread.currentThread().getName()+"当前时间:"+System.currentTimeMillis());
	    	
	    	try {
	    		while(true)
	    		{
	    			Map<String,String> param = new HashMap<>();
	    					param.put("token", token);
	    			String result =	HttpClientUtil.get(Api.member_info,null,param);
	    			Gson gson = new Gson();
	    			Member msg =  gson.fromJson(result, Member.class);
	    			
	    			List<Account> accounts = msg.getResult().getAccounts();
	    			if(null!=msg && null!=msg.getResult() &&CollectionUtils.isNotEmpty(msg.getResult().getAccounts()))
	    			{
	    				account_type_0.setText(accounts.get(0).balance.toString());
	    				account_type_1.setText(accounts.get(1).balance.toString());
	    				account_type_2.setText(accounts.get(1).balance.toString());
	    			}
	    			
	    			Thread.sleep(60000L);
	    			i++;
	    		}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	public AccountThread(JLabel account_type_0, JLabel account_type_1, JLabel account_type_2,String token) {
		super();
		this.account_type_0 = account_type_0;
		this.account_type_1 = account_type_1;
		this.account_type_2 = account_type_2;
		this.token = token;
	}
	  
	  
	  
}
