package com.panli.view;

import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.panli.model.Plan;
import com.panli.model.Record;
import com.panli.model.Statis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Getter
//@Setter
public class PlaceThread  extends Thread {
	
	private int i = 0;
	
	private Plan plan ;
	
	private List<Record> records;
	
	private JTable table;
	
	private Statis statis;
	
	private Object [][]gridData=null;
  
    private Object[] gridHeader = new String[] {
			"\u6295\u6CE8\u5F69\u79CD", "\u6295\u6CE8\u65F6\u95F4", "\u6295\u6CE8\u671F\u6570", "\u65B9\u6848", "\u73A9\u6CD5", "\u91D1\u989D", "\u76C8\u4E8F", "\u6295\u6CE8", "\u5F00\u5956\u53F7\u7801", "\u8F6E\u6B21", "\u72B6\u6001", "\u4E2D\u6302", "\u8FDE\u6302", "\u8FDE\u4E2D", "\u65B9\u6848\u76C8\u4E8F"
		};
	Long sleepTime = 60000L;
	
//	private String 

    @Override
    public void run() {
    	log.debug("启动线程:"+Thread.currentThread().getName()+"当前时间:"+System.currentTimeMillis());
    	
    	try {
    		while(true)
    		{
    			Record record = new Record(plan);
    			record.setDrawNumber(String.valueOf(Long.valueOf(51631557L)+i));
    			plan.setCurrentLine("悄好");
    			
    			((DefaultTableModel) table.getModel()).addRow(record.getRowData(gridHeader.length));
    			
    			PlaceThread.sleep(sleepTime);
    			i++;
    		}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
  //生成表格数据
    /**
     * @return
     */
    public Object[][] getPlanData(){
    	int r = records.size();
    	
    	gridData=new Object[r][gridHeader.length];

        for(int i=0;i<r;i++){
            for(int j=0;j<gridData.length;j++){
            	gridData[i][0]=records.get(i).getLotteryName();
            	gridData[i][1]=records.get(i).getDrawTime();
            }
        }
        return gridData;
    }
    
public PlaceThread(JTable table,Plan plan, List<Record> records) {
	this(table, plan, records, null);
}



	public PlaceThread(JTable table, Plan plan, List<Record> records,Statis statis) {
		super();
		this.plan = plan;
		this.records = records;
		this.table = table;
		this.statis = statis;
	}
	
}
