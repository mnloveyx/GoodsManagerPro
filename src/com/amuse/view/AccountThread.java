package com.amuse.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.CollectionUtils;

import com.amuse.model.Account;
import com.amuse.model.LoginMsg;
import com.amuse.model.Member;
import com.amuse.model.Record;
import com.amuse.util.Api;
import com.amuse.util.HttpClientUtil;
import com.amuse.util.SubjectUtils;
import com.google.gson.Gson;

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
	    	log.debug("start accountInfo history query:"+Thread.currentThread().getName()+" currentTime:"+System.currentTimeMillis());
	    	
	    	try {
	    		while(true)
	    		{
	    			Map<String,String> param = new HashMap<>();
	    					param.put("token",token);
	    			String result =	HttpClientUtil.get(Api.member_info,null,param);
	    			Gson gson = new Gson();
	    			Member msg =  gson.fromJson(result, Member.class);
//	    			log.debug("用户账户信息:{}",result);
	    			List<Account> accounts = msg.getResult().getAccounts();
	    			if(null!=msg && null!=msg.getResult() &&CollectionUtils.isNotEmpty(msg.getResult().getAccounts()))
	    			{
	    				account_type_0.setText(accounts.get(0).getBalance().toString());
	    				account_type_1.setText(accounts.get(0).getBetting().toString());
	    				account_type_2.setText(accounts.get(0).getResult().toString());
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
