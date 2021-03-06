package com.amuse.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.CollectionUtils;

import com.amuse.model.Account;
import com.amuse.model.CalcData;
import com.amuse.model.Member;
import com.amuse.model.Plan;
import com.amuse.util.Api;
import com.amuse.util.HttpClientUtil;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalcThread extends Thread{
	
	private JTable table;
	
	private String token;
	
	private Plan plan;
	
    private DefaultTableModel tableModel;
    
    private CalcData calcData;
    
	private int i =0;
	
	  @Override
	    public void run() {
	    	log.debug("stat static function:"+Thread.currentThread().getName()+"currentTime:"+System.currentTimeMillis());
//	    	try {
	    		if(tableModel.getRowCount()>0)
	    		{
	    			tableModel.removeRow(0);
	    		}
	    		tableModel.addRow(calcData.getRowData(tableModel.getColumnCount()));
	    			i++;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    }
	public CalcThread(JTable table,Plan plan ,String token) {
		super();
		this.table = table;
		this.token = token;
		this.tableModel = (DefaultTableModel) table.getModel();
		this.calcData = new CalcData(plan);
	}
	  
}
