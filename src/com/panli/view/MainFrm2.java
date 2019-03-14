package com.panli.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.panli.model.Api;
import com.panli.model.Item;
import com.panli.model.Plan;
import com.panli.model.Record;
import com.panli.model.Scheme;
import com.panli.model.Statis;
import com.panli.model.User;
import com.panli.util.FileUtils;
import com.panli.util.SubjectUtils;
import com.toedter.calendar.JDateChooser;

import lombok.extern.slf4j.Slf4j;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@Slf4j
//@Setter
//@Getter
public class MainFrm2 extends JFrame {

	private JPanel contentPane;
	private JTextField plan_text_1;
	private JTextField plan_text_2;
	private JTextField plan_text_3;
	private JTextField plan_text_4;
	private JTextField plan_text_5;
	private JTextField plan_text_6;
	private JTextField plan_text_7;
	private JTextField plan_text_8;
	private JTextField plan_text_9;
	private JTextField plan_text_10;
	private JTable planTable;
	private JTable table_1;
	private JTextField plan_p2_start;
	private JTextField plan_p2_jumpline;
	private JTextField plan_p2_end;
	private JComboBox plan_comboBox_1;
	private JComboBox schemeTxt;
	
	private JComboBox autoselectplan;
	private JComboBox autocalcplan;
	private JScrollPane scrollPane;
	
	private JTable table_2;
	private JPanel plan_p2;
	private  JPanel plan_p;
	private JPanel panel_plan_main;
	JPanel panel_12;
	
	private User user;
	private JTable table;
	private JTextField amountsTxt;
	private JTable table_3;
	
	private Scheme scheme;
	
	private Plan currentPlan;
	
	private List<Scheme> schemes;
	
	private static List<Plan> plans;
	
	private static String planDir="./Scheme/";
	
	private String[] plan = new String[] {"方案1", "方案2", "方案3", "方案4", "方案5"};
	
	private JLabel userName;
	
	private JLabel account_type_0; 
	
	private JLabel account_type_1;
	
	private JLabel account_type_2;
	
	private String token = "0c4eed4df997248c0c737b4781002f2df4bb4620";
	
	
//	private JLabel account_type_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm2 frame = new MainFrm2(new User());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrm2(User user ) {
//		 setUser(user);
		initSchemes();
		this.user = user;
		if(null==SubjectUtils.getUser())
		{
			this.user.setOid(token);
			SubjectUtils.setUser(this.user);
		}
		setTitle("娱乐管理系统V1.0.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/goods_logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 650);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("这是一个广告图");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("这是一个广告位置");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(274)
					.addComponent(lblNewLabel)
					.addContainerGap(279, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		 schemeTxt = new JComboBox();
		DefaultComboBoxModel model  = new DefaultComboBoxModel();
//		comboBox.setModel(new DefaultComboBoxModel(new String[] {"极速极赛车", "极速飞艇", "幸运飞艇"}));
//		comboBox.setModel(new DefaultComboBoxModel(SchemeEnum.enumsToStringArray()));
		
		schemeTxt.setModel(model);
		
		if(CollectionUtils.isNotEmpty(schemes))
		{
			schemes.forEach(s->{
				model.addElement(s);
			});
		}else {
			model.addElement(new Scheme("极速极赛车","PK10JSC"));
			model.addElement(new Scheme("极速飞艇 ","LUCKYSB"));
			model.addElement(new Scheme("幸运飞艇","XYFT"));
		}
		//设置默认选中
		model.setSelectedItem(schemes.get(0));
		schemeTxt.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Scheme item = 	(Scheme) schemeTxt.getSelectedItem();
					log.debug("选中:{},key:{},value:{}",schemeTxt.getSelectedIndex(),item.getKey(),item.getValue());
					scheme = item;
					SubjectUtils.setScheme(item);
					plans = item.getPlans();
					planTable.setModel(new DefaultTableModel(
							getPlanData(),planDateHead
						));
					planTable.getSelectionModel().setSelectionInterval(0, 0);
					
					
					DefaultComboBoxModel autoselectplanmodel =	new DefaultComboBoxModel();
					if(CollectionUtils.isNotEmpty(plans))
					{
						plans.forEach(s->{
							autoselectplanmodel.addElement(s);
						});
					}
					autoselectplan.setModel(autoselectplanmodel);
					autocalcplan.setModel(autoselectplanmodel);
//					name
//					planTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				}
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("用户名");
		
		 userName = new JLabel("laomeng666");
		 
		 if(user!=null && user.getUsername()!=null)
			{
				this.userName.setText(user.getUsername());
			}
		
		JLabel label_1 = new JLabel("额度");
		
		 account_type_0 = new JLabel("0.0");
		
		JLabel label_3 = new JLabel("未结算金额");
		
		 account_type_1 = new JLabel("0.0");
		
		JLabel lblNewLabel_21 = new JLabel("今日输赢");
		
		 account_type_2 = new JLabel("0.0");
		 
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(12)
					.addComponent(schemeTxt, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userName, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(account_type_0, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(account_type_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_21)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(account_type_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(348))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(userName)
						.addComponent(label_1)
						.addComponent(account_type_0)
						.addComponent(label_3)
						.addComponent(account_type_1)
						.addComponent(lblNewLabel_21)
						.addComponent(account_type_2)
						.addComponent(schemeTxt, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_5, BorderLayout.WEST);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"这是每期的开奖记录1", "这是每期的开奖记录1", "这是每期的开奖记录1", "这是每期的开奖记录1"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		table_2 = new JTable();
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"51626630", "5,7,6,8,1,3,2,10,9,4", "15:12"},
				{"51626629", "7,8,4,2,9,10,3,6,1,5", "15:11"},
				{"51626628", "10,8,7,9,5,1,3,4,6,2", "15:10"},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\u5F00\u5956\u671F\u53F7", "\u5F00\u5956\u53F7\u7801", "\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(62);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(147);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(91);
		scrollPane_1.add(table_2);
		scrollPane_1.setViewportView(table_2);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("1.欢迎使用", null, panel_9, null);
		
		JLabel lblNewLabel_8 = new JLabel("欢迎使用");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 65));
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(198)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(112)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		panel_9.setLayout(gl_panel_9);
		
	    panel_plan_main = new JPanel();
		tabbedPane.addTab("2.方案设定", null, panel_plan_main, null);
		
		panel_12 = new JPanel();
		planTable = new JTable();
		planTable.setFillsViewportHeight(true);
		
		planTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){  
            @Override  
            public void valueChanged(ListSelectionEvent e)  
            {  
//            	scheme = (Scheme) schemeTxt.getSelectedItem();
//            	plans = scheme.getPlans();
//            	planTable.setModel(new DefaultTableModel(
//						getPlanData(),planDateHead
//					));
//            	planTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//            	planTable.getSelectionModel().setSelectionInterval(0, 0);
				int sr = planTable.getSelectedRow();
              if (sr == -1) {
                  return;
              }
//              plans = sc
              
              String play = (String) planTable.getModel().getValueAt(sr, 1);
          	if(sr==0)
          	{
//          		panel_12.remove(plan_p2);
          		TitledBorder t = (TitledBorder)plan_p.getBorder();
          		t.setTitle(play+"设置");
//          		panel_12.add(plan_p,BorderLayout.NORTH);
          		panel_12.add(plan_p,BorderLayout.SOUTH);
          	
          	}else {
          		panel_12.remove(plan_p);
//          		panel_12.add(plan_p2,BorderLayout.NORTH);
//          		panel_12.add(plan_p2);
          	}
          	TitledBorder t = (TitledBorder)plan_p2.getBorder();
          	t.setTitle(play+"跳线设置");
          	panel_12.updateUI();
      		panel_12.repaint();
      		setPlan(plans.get(sr));
          }
        });  
		
		planTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 scrollPane = new JScrollPane();
		scheme = (Scheme) schemeTxt.getSelectedItem();
		plans = scheme.getPlans();
		planDateHead = new String[] {"\u65B9\u6848", "\u73A9\u6CD5"};
		scrollPane.add(planTable);
				planTable.setModel(new DefaultTableModel(
					getPlanData(),planDateHead
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				planTable.getColumnModel().getColumn(0).setPreferredWidth(64);
				planTable.getColumnModel().getColumn(1).setPreferredWidth(64);
				planTable.setRowHeight(30);
				
				scrollPane.setViewportView(planTable);
				GroupLayout gl_panel_plan_main = new GroupLayout(panel_plan_main);
				gl_panel_plan_main.setHorizontalGroup(
					gl_panel_plan_main.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_plan_main.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_panel_plan_main.setVerticalGroup(
					gl_panel_plan_main.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_plan_main.createSequentialGroup()
							.addGroup(gl_panel_plan_main.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_12, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel_plan_main.setLayout(gl_panel_plan_main);
				
				JPanel panel_10 = new JPanel();
				tabbedPane.addTab("3.自动投注", null, panel_10, null);
				
				JPanel panel_7 = new JPanel();
				panel_7.setPreferredSize(new Dimension(400, 100));
				
				JLabel label_5 = new JLabel("方案：");
				
				autoselectplan = new JComboBox();
				DefaultComboBoxModel autoselectplanmodel =	new DefaultComboBoxModel();
				autoselectplan.setModel(autoselectplanmodel);
				if(CollectionUtils.isNotEmpty(plans))
				{
					plans.forEach(s->{
						autoselectplanmodel.addElement(s);
					});
				}else {
					 int i,c;
					 for ( i=0,c=plan.length;i<c;i++ )
						 autoselectplanmodel.addElement(plan[i]);
				}
				
				JLabel label_23 = new JLabel("金额：");
				
				amountsTxt = new JTextField();
				amountsTxt.setText("1,2,3,4,5,6,7,8,9,10");
				amountsTxt.setColumns(10);
				
				JRadioButton autoradioButton = new JRadioButton(Api.placeType_1);
				JRadioButton autoradioButton_1 = new JRadioButton(Api.placeType_0);
				autoradioButton_1.setSelected(true);
				
				ButtonGroup autogroup = new ButtonGroup();
				autogroup.add(autoradioButton);
				autogroup.add(autoradioButton_1);
				
				JButton autoStart = new JButton("开启自动投注");
//				btnNewButton.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						
//						Object[] s = 	group.getSelection().getSelectedObjects();
//						
//					}
//				});
				autoStart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String placeType = getRadioButton(autogroup);
						Plan selectPlan = (Plan) autoselectplan.getSelectedItem();
						SubjectUtils.setPlan(selectPlan);
						String amounts = amountsTxt.getText();
						selectPlan.setAmounts(amounts);
						selectPlan.setPlaceType(placeType);
						selectPlan.setStartLine(selectPlan.getStartLine());
						PlaceThread  placeThread = new PlaceThread(table,selectPlan,user.getToken());
						SubjectUtils.setStatis(new Statis());
						if(autoStart.getText().equalsIgnoreCase("启动自动投注"))
						{
							log.info("启动自动投注:方案:{},下注类型:{},金额设置:{}",selectPlan.toLogString(),placeType,amounts);
							autoStart.setText("停止自动投注");
							autoStart.setForeground(Color.GREEN);
							
							placeThread.start();
							 
							
						}else {
							
							//终止线程
							placeThread.setFlag(false);
//							try {
//								
//								Thread.sleep(60000L);
//							} catch (InterruptedException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
							autoStart.setText("启动自动投注");
							autoStart.setForeground(new Color(0, 0, 0));
							log.info("停止自动投注:方案:{},下注类型:{},金额设置:{}",selectPlan.toLogString(),placeType,amounts);
							
							Integer i = records.size();
							String current = selectPlan.getCurrentLine();
							log.info(i.toString(),current);
						}
					}
				});
				autoStart.setBackground(SystemColor.activeCaption);
				
				JLabel label_24 = new JLabel("备注：金额设定为对应的局数如：1,2,4,8,16,32,64,128,256,512");
				GroupLayout gl_panel_7 = new GroupLayout(panel_7);
				gl_panel_7.setHorizontalGroup(
					gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(label_24)
								.addGroup(gl_panel_7.createSequentialGroup()
									.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
										.addComponent(label_23)
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
										.addComponent(amountsTxt, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
										.addComponent(autoselectplan, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
										.addComponent(autoradioButton)
										.addComponent(autoradioButton_1))
									.addGap(31)
									.addComponent(autoStart)))
							.addContainerGap(141, Short.MAX_VALUE))
				);
				gl_panel_7.setVerticalGroup(
					gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(autoStart, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_7.createSequentialGroup()
									.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(label_5)
										.addComponent(autoselectplan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(autoradioButton_1))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_23)
										.addComponent(amountsTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(autoradioButton))))
							.addGap(11)
							.addComponent(label_24)
							.addContainerGap())
				);
				panel_7.setLayout(gl_panel_7);
				
				JPanel panel_11 = new JPanel();
				panel_11.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_11.setPreferredSize(new Dimension(400, 20));
				JLabel lblNewLabel_22 = new JLabel("真实下注：");
				
				JLabel realCountTxt = new JLabel("0");
				
				JLabel label_7 = new JLabel("真实盈亏：");
				
				JLabel realAmountTxt = new JLabel("0");
				
				JLabel label_9 = new JLabel("模拟下注：");
				
				JLabel virtualCountTxt = new JLabel("0");
				
				JLabel label_11 = new JLabel("模拟盈亏：");
				
				JLabel virtualAmountTxt = new JLabel("0");
				
				JLabel label_13 = new JLabel("投注记录：");
				
				JLabel placeCountTxt = new JLabel("0");
				
				JLabel label_15 = new JLabel("最大连中：");
				
				JLabel continueWinTxt = new JLabel("0");
				
				JLabel label_17 = new JLabel("最大连挂：");
				
				JLabel continueLostTxt = new JLabel("0");
				
				JLabel label_19 = new JLabel("投注状态：");
				
				JLabel placeStatusTxt = new JLabel("无");
				
				JLabel label_21 = new JLabel("准确率：");
				
				JLabel winPercentTxt = new JLabel("0%");
				
				JButton clearTable = new JButton("清除数据");
				clearTable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						model.setRowCount(0);
					}
				});
				GroupLayout gl_panel_11 = new GroupLayout(panel_11);
				gl_panel_11.setHorizontalGroup(
					gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_11.createSequentialGroup()
									.addComponent(lblNewLabel_22)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(realCountTxt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(realAmountTxt, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(virtualCountTxt, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(virtualAmountTxt, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(placeCountTxt, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(continueWinTxt, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(continueLostTxt, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_11.createSequentialGroup()
									.addComponent(label_19, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(placeStatusTxt, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_21, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(winPercentTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(186)
									.addComponent(clearTable)))
							.addContainerGap(290, Short.MAX_VALUE))
				);
				gl_panel_11.setVerticalGroup(
					gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
								.addComponent(label_17)
								.addComponent(continueLostTxt)
								.addComponent(label_15)
								.addComponent(continueWinTxt)
								.addComponent(label_13)
								.addComponent(placeCountTxt)
								.addComponent(label_11)
								.addComponent(virtualAmountTxt)
								.addComponent(label_9)
								.addComponent(virtualCountTxt)
								.addComponent(label_7)
								.addComponent(realAmountTxt)
								.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_22)
									.addComponent(realCountTxt)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_11.createSequentialGroup()
									.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
										.addComponent(placeStatusTxt, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
										.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING, false)
											.addComponent(label_19, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
												.addComponent(winPercentTxt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label_21, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
									.addGap(10))
								.addGroup(gl_panel_11.createSequentialGroup()
									.addComponent(clearTable)
									.addContainerGap())))
				);
				panel_11.setLayout(gl_panel_11);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				scrollPane_2.setPreferredSize(new Dimension(380, 300));
				table = new JTable();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setModel(new DefaultTableModel(
//					new Object[][] {
//						{"\u6781\u901F\u8D5B\u8F66", "00:58", "51626640", "\u65B9\u68481", "\u5F00\u67D0\u6295\u67D0", "10000.00", "-10000.00", "1,2,3,4,5", "5,7,6,8,1,3,2,10,9,4", "1", "\u6A21\u62DF", "\u6302", "5/5", "0/2", "-20000.00", null},
//					},
						new Object[][] {
//						{null,null,null,null,null,null,null,null,null,null,null,null, "5/5", "0/2", "-20000.00", null},
					},
					new String[] {
						"\u6295\u6CE8\u5F69\u79CD", "\u6295\u6CE8\u65F6\u95F4", "\u6295\u6CE8\u671F\u6570", "\u65B9\u6848", "\u73A9\u6CD5", "\u91D1\u989D", "\u76C8\u4E8F", "\u6295\u6CE8", "\u5F00\u5956\u53F7\u7801", "\u8F6E\u6B21", "\u72B6\u6001", "\u4E2D\u6302", "\u8FDE\u6302", "\u8FDE\u4E2D", "\u65B9\u6848\u76C8\u4E8F", "\u5F53\u524D\u7EBF\u8DEF"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(58);
				table.getColumnModel().getColumn(1).setPreferredWidth(61);
				table.getColumnModel().getColumn(2).setPreferredWidth(60);
				table.getColumnModel().getColumn(3).setPreferredWidth(38);
				table.getColumnModel().getColumn(4).setPreferredWidth(56);
				table.getColumnModel().getColumn(5).setPreferredWidth(54);
				table.getColumnModel().getColumn(6).setPreferredWidth(58);
				table.getColumnModel().getColumn(8).setPreferredWidth(126);
				table.getColumnModel().getColumn(9).setPreferredWidth(35);
				table.getColumnModel().getColumn(10).setPreferredWidth(36);
				table.getColumnModel().getColumn(11).setPreferredWidth(36);
				table.getColumnModel().getColumn(12).setPreferredWidth(34);
				table.getColumnModel().getColumn(13).setPreferredWidth(34);
				table.getColumnModel().getColumn(14).setPreferredWidth(63);
//				panel_10.add(table, BorderLayout.NORTH);
				scrollPane_2.add(table);
				scrollPane_2.setViewportView(table);
				GroupLayout gl_panel_10 = new GroupLayout(panel_10);
				gl_panel_10.setHorizontalGroup(
					gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_10.createSequentialGroup()
							.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
								.addGroup(gl_panel_10.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(panel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(panel_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)))
							.addContainerGap())
				);
				gl_panel_10.setVerticalGroup(
					gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_10.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
							.addGap(1))
				);
				panel_10.setLayout(gl_panel_10);
				JPanel panel_15 = new JPanel();
				tabbedPane.addTab("4.参考数据", null, panel_15, null);
				
				JPanel panel_14 = new JPanel();
				panel_14.setPreferredSize(new Dimension(400, 100));
				
				JLabel label_27 = new JLabel("方案：");
				
				autocalcplan = new JComboBox();
				
				autocalcplan = new JComboBox();
				autocalcplan.setModel(autoselectplanmodel);
//				if(CollectionUtils.isNotEmpty(plans))
//				{
//					plans.forEach(s->{
//						autoselectplanmodel.addElement(s);
//					});
//				}else {
//					 int i,c;
//					 for ( i=0,c=plan.length;i<c;i++ )
//						 autoselectplanmodel.addElement(plan[i]);
//				}
//				autocalcplan.setModel(new DefaultComboBoxModel(plan));
				
				JButton button_1 = new JButton("开始计算");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Plan selectPlan = (Plan) autoselectplan.getSelectedItem();
						CalcThread t = new CalcThread(table_3, selectPlan, user.getOid());
						t.start();
					}
				});
				button_1.setBackground(Color.LIGHT_GRAY);
				
				JLabel lblNewLabel_23 = new JLabel("日期：");
				
				JDateChooser  comboBox_3 = new JDateChooser(DateUtils.addDays(new Date(), -1));
				
				JLabel label_25 = new JLabel("时间：");
				
				JLabel label_26 = new JLabel("至");
				
				JSpinner spinner = new JSpinner(new SpinnerDateModel());
				spinner.setValue(DateUtils.addHours(new Date(), -1));
				//设置时间格式
		        spinner.setEditor(new JSpinner.DateEditor(spinner,
		                "HH:mm"));
		        //获得JSPinner对象
		        JSpinner spinner_1 = new JSpinner(new SpinnerDateModel());
		        spinner_1.setValue(new Date());
		        spinner_1.setEditor(new JSpinner.DateEditor(spinner_1,
		                "HH:mm"));
				
				
				GroupLayout gl_panel_14 = new GroupLayout(panel_14);
				gl_panel_14.setHorizontalGroup(
					gl_panel_14.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_14.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
								.addComponent(label_27, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_23))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
								.addComponent(autocalcplan, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_25)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_26)
							.addGap(12)
							.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(button_1)
							.addContainerGap(115, Short.MAX_VALUE))
				);
				gl_panel_14.setVerticalGroup(
					gl_panel_14.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_14.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_14.createSequentialGroup()
									.addGroup(gl_panel_14.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(label_27)
										.addComponent(autocalcplan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_14.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_23)
										.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_25)
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_26)))
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(37, Short.MAX_VALUE))
				);
				panel_14.setLayout(gl_panel_14);
				
				table_3 = new JTable();
				table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table_3.setModel(new DefaultTableModel(
					new String[][] {
//						{0, 0, 0, null, null, null, null, null, null},
					},
					new String[] {
						"\u65B9\u6848\u540D\u79F0", "\u5185\u5BB9", "\u76EE\u524D\u8FDE\u4E2D", "\u4E0A\u6B21\u8FDE\u4E2D", "\u76EE\u524D\u8FDE\u9519", "\u4E0A\u6B21\u5F00\u51FA", "\u4ECA\u65E5\u672A\u51FA", "\u6628\u65E5\u672A\u51FA", "\u4E00\u5468\u672A\u51FA"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table_3.getColumnModel().getColumn(1).setPreferredWidth(145);
				
				JScrollPane scrollPane_3 = new JScrollPane();
				scrollPane_3.setPreferredSize(new Dimension(380, 300));
				scrollPane_3.add(table_3);
				scrollPane_3.setViewportView(table_3);
				GroupLayout gl_panel_15 = new GroupLayout(panel_15);
				gl_panel_15.setHorizontalGroup(
					gl_panel_15.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_15.createSequentialGroup()
							.addGroup(gl_panel_15.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE))
							.addContainerGap())
				);
				gl_panel_15.setVerticalGroup(
					gl_panel_15.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_15.createSequentialGroup()
							.addComponent(panel_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
				);
				panel_15.setLayout(gl_panel_15);
				
				tabbedPane.addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent e) {
//		                
		                if(tabbedPane.getSelectedIndex()==1)
		                {
		                	planTable.getSelectionModel().setSelectionInterval(0, 0);
		                }
		            }
				});
				
				
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 955, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_6, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_2 = new JLabel("这是一些其它的提示信息");
		panel_6.add(lblNewLabel_2);
		
		JPanel panel_13 = new JPanel();
		
		JButton plan_b_save = new JButton("保存");
		plan_b_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String,String> num = new HashMap<>();
				num.put("1",plan_text_1.getText());
				num.put("2",plan_text_2.getText());
				num.put("3",plan_text_3.getText());
				num.put("4",plan_text_4.getText());
				num.put("5",plan_text_5.getText());
				num.put("6",plan_text_6.getText());
				num.put("7",plan_text_7.getText());
				num.put("8",plan_text_8.getText());
				num.put("9",plan_text_9.getText());
				num.put("10",plan_text_10.getText());
				
				String startLine = plan_comboBox_1.getSelectedItem().toString();
				
				scheme = (Scheme) schemeTxt.getSelectedItem();
				String schemeName = scheme.getKey();
				String schemeValue = scheme.getValue();
				
				String startTime = plan_p2_start.getText();
				
				String endTime = plan_p2_end.getText();
				
				String jumpLines = plan_p2_jumpline.getText();
				
				TableModel  tm =	planTable.getModel();
			 int selectIndex =	planTable.getSelectedRow();
			 String planName = tm.getValueAt(selectIndex, 0).toString();
			 String planType = tm.getValueAt(selectIndex, 1).toString();
			 
			 currentPlan   = new Plan(schemeName,schemeValue, planName,startLine, startTime,
					endTime, jumpLines,num,planType) ;
			 
//			 FileUtils.
			 
			 String fileName = planDir+schemeValue+"/"+planName+".txt";
			 
//			 Boolean b =	FileUtils.createFile("descDirName");
			 try {
				FileUtils.writeStringToFile(FileUtils.getFile(fileName), currentPlan.toFileString(), Charset.forName("UTF-8"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 if(CollectionUtils.isEmpty(plans))
			 {
				 plans =  new ArrayList<>();
				 plans.add(currentPlan);
			 }else {
				 for(int i = plans.size()-1;i>=0;i--)
				 {
					 Plan p = plans.get(i);
					 if(currentPlan.getType().equals(p.getType()))
					 {
						 plans.set(i, currentPlan);
					 }
				 }
			 }
			 
			//刷新现有数据
			 DefaultComboBoxModel autoselectplanmodel =	new DefaultComboBoxModel();
				if(CollectionUtils.isNotEmpty(plans))
				{
					plans.forEach(s->{
						autoselectplanmodel.addElement(s);
					});
				}
				autoselectplan.setModel(autoselectplanmodel);
				autocalcplan.setModel(autoselectplanmodel);
				log.debug("refulsh:autoselectplan and autocalcplan,plan:{}",currentPlan.toLogString());
			}
		});
//		plan_b_save.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
//		});
		panel_13.add(plan_b_save);
		
		JButton plan_b_update = new JButton("修改");
		plan_b_update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				plan_text_1.setText("2");
			}
		});
		panel_12.setLayout(new BorderLayout(0, 0));
		plan_p = new JPanel();
		plan_p.setBorder(new TitledBorder(null, "\u8BA1\u5212\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plan_p.setPreferredSize(new Dimension(400, 180));
				
				JLabel lblNewLabel_4 = new JLabel("一号");
				
				plan_text_1 = new JTextField();
				plan_text_1.setText("1,2,3,4,5");
				plan_text_1.setColumns(10);
				
				JLabel lblNewLabel_5 = new JLabel("二号");
				
				plan_text_2 = new JTextField();
				plan_text_2.setText("2,3,4,5,6");
				plan_text_2.setColumns(10);
				
				JLabel lblNewLabel_6 = new JLabel("三号");
				
				plan_text_3 = new JTextField();
				plan_text_3.setText("3,4,5,6,7");
				plan_text_3.setColumns(10);
				
				JLabel lblNewLabel_9 = new JLabel("四号");
				
				JLabel lblNewLabel_10 = new JLabel("九号");
				
				JLabel lblNewLabel_11 = new JLabel("五号");
				
				plan_text_4 = new JTextField();
				plan_text_4.setText("4,5,6,7,8");
				plan_text_4.setColumns(10);
				
				JLabel lblNewLabel_12 = new JLabel("六号");
				
				JLabel lblNewLabel_13 = new JLabel("七号");
				
				JLabel lblNewLabel_14 = new JLabel("八号");
				
				plan_text_5 = new JTextField();
				plan_text_5.setText("5,6,7,8,9");
				plan_text_5.setColumns(10);
				
				plan_text_6 = new JTextField();
				plan_text_6.setText("6,7,8,9,10");
				plan_text_6.setColumns(10);
				
				plan_text_7 = new JTextField();
				plan_text_7.setText("7,8,9,10,1");
				plan_text_7.setColumns(10);
				
				plan_text_8 = new JTextField();
				plan_text_8.setText("8,9,10,1,2");
				plan_text_8.setColumns(10);
				
				plan_text_9 = new JTextField();
				plan_text_9.setText("9,10,1,2,3");
				plan_text_9.setColumns(10);
				
				JLabel lblNewLabel_7 = new JLabel("十号");
				
				plan_text_10 = new JTextField();
				plan_text_10.setText("10,1,2,3,4");
				plan_text_10.setColumns(10);
				GroupLayout gl_plan_p = new GroupLayout(plan_p);
				gl_plan_p.setHorizontalGroup(
					gl_plan_p.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_plan_p.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_plan_p.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addGap(4)
									.addComponent(plan_text_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(4)
									.addComponent(plan_text_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addGap(4)
									.addComponent(plan_text_3, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addComponent(lblNewLabel_9)
									.addGap(4)
									.addComponent(plan_text_4, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addComponent(lblNewLabel_11)
									.addGap(4)
									.addComponent(plan_text_5, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
							.addGap(39)
							.addGroup(gl_plan_p.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_plan_p.createSequentialGroup()
									.addComponent(lblNewLabel_12)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(plan_text_6, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addGroup(gl_plan_p.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_10)
										.addComponent(lblNewLabel_14)
										.addComponent(lblNewLabel_13)
										.addComponent(lblNewLabel_7))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
										.addComponent(plan_text_10, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addComponent(plan_text_8, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addComponent(plan_text_7, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addComponent(plan_text_9, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(40, Short.MAX_VALUE))
				);
				gl_plan_p.setVerticalGroup(
					gl_plan_p.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_plan_p.createSequentialGroup()
							.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_plan_p.createSequentialGroup()
									.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_plan_p.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNewLabel_4))
										.addComponent(plan_text_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(6)
									.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_plan_p.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNewLabel_5))
										.addComponent(plan_text_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(10)
									.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_plan_p.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNewLabel_6))
										.addGroup(gl_plan_p.createParallelGroup(Alignment.BASELINE)
											.addComponent(plan_text_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_14)
											.addComponent(plan_text_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addGroup(gl_plan_p.createParallelGroup(Alignment.BASELINE)
										.addComponent(plan_text_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_12))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_plan_p.createParallelGroup(Alignment.BASELINE)
										.addComponent(plan_text_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_13))))
							.addGap(6)
							.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_plan_p.createSequentialGroup()
									.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_plan_p.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNewLabel_9))
										.addComponent(plan_text_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(10)
									.addGroup(gl_plan_p.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_plan_p.createSequentialGroup()
											.addGap(3)
											.addComponent(lblNewLabel_11))
										.addComponent(plan_text_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_plan_p.createSequentialGroup()
									.addGroup(gl_plan_p.createParallelGroup(Alignment.BASELINE)
										.addComponent(plan_text_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_10))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_plan_p.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_7)
										.addComponent(plan_text_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(19, Short.MAX_VALUE))
				);
				plan_p.setLayout(gl_plan_p);
				panel_12.add(plan_p, BorderLayout.SOUTH);
		
		 plan_p2 = new JPanel();
		 plan_p2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8DF3\u7EBF\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 plan_p2.setPreferredSize(new Dimension(400, 180));
		 JLabel lblNewLabel_18 = new JLabel("开始线路");
		 
		 plan_comboBox_1 = new JComboBox();
		 plan_comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		 
		 JLabel lblNewLabel_15 = new JLabel("时间范围");
		 
		 plan_p2_start = new JTextField();
		 plan_p2_start.setText("50");
		 plan_p2_start.setColumns(10);
		 
		 JLabel lblNewLabel_16 = new JLabel("-");
		 
		 plan_p2_jumpline = new JTextField();
		 plan_p2_jumpline.setText("1,2,3,4,5,6");
		 plan_p2_jumpline.setColumns(10);
		 
		 JLabel lblNewLabel_17 = new JLabel("跳   线");
		 
		 plan_p2_end = new JTextField();
		 plan_p2_end.setText("60");
		 plan_p2_end.setColumns(10);
		 
		 JLabel label = new JLabel("备注：时间范围单位为：分钟，跳线设置如:1,2,3,4,5,6");
		 GroupLayout gl_plan_p2 = new GroupLayout(plan_p2);
		 gl_plan_p2.setHorizontalGroup(
		 	gl_plan_p2.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_plan_p2.createSequentialGroup()
		 			.addGap(29)
		 			.addGroup(gl_plan_p2.createParallelGroup(Alignment.LEADING)
		 				.addComponent(label)
		 				.addGroup(gl_plan_p2.createSequentialGroup()
		 					.addComponent(lblNewLabel_18)
		 					.addGap(18)
		 					.addComponent(plan_comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		 				.addGroup(gl_plan_p2.createSequentialGroup()
		 					.addComponent(lblNewLabel_17)
		 					.addGap(24)
		 					.addComponent(plan_p2_jumpline, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
		 				.addGroup(gl_plan_p2.createSequentialGroup()
		 					.addComponent(lblNewLabel_15)
		 					.addGap(18)
		 					.addComponent(plan_p2_start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.UNRELATED)
		 					.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(plan_p2_end, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		 			.addGap(100))
		 );
		 gl_plan_p2.setVerticalGroup(
		 	gl_plan_p2.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_plan_p2.createSequentialGroup()
		 			.addGap(8)
		 			.addGroup(gl_plan_p2.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(lblNewLabel_18)
		 				.addComponent(plan_comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		 			.addGap(18)
		 			.addGroup(gl_plan_p2.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(lblNewLabel_15)
		 				.addComponent(plan_p2_start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(lblNewLabel_16)
		 				.addComponent(plan_p2_end, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		 			.addGap(19)
		 			.addGroup(gl_plan_p2.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(lblNewLabel_17)
		 				.addComponent(plan_p2_jumpline, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		 			.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
		 			.addComponent(label)
		 			.addContainerGap())
		 );
		 plan_p2.setLayout(gl_plan_p2);
		 panel_12.add(plan_p2);
		panel_13.add(plan_b_update);
		panel_12.add(panel_13, BorderLayout.NORTH);
		
		
		
		AccountThread accountThread = new AccountThread(account_type_0, account_type_1, account_type_2, user.getToken());
		 accountThread.start();
		 
		 HistoryDrawThread drawThread = new HistoryDrawThread(table_2, scheme,user.getToken());
		 drawThread.start();
//		table_3 = new JTable();
//		tabbedPane.addTab("New tab", null, table_3, null);
	}
	
	protected void setPlan(Plan p)
	{
		log.debug("updatePlanPanel");
		
		plan_text_1.setText(p.getNumMap("1"));
		plan_text_2.setText(p.getNumMap("2"));
		plan_text_3.setText(p.getNumMap("3"));
		plan_text_4.setText(p.getNumMap("4"));
		plan_text_5.setText(p.getNumMap("5"));
		plan_text_6.setText(p.getNumMap("6"));
		plan_text_7.setText(p.getNumMap("7"));
		plan_text_8.setText(p.getNumMap("8"));
		plan_text_9.setText(p.getNumMap("9"));
		plan_text_10.setText(p.getNumMap("10"));
		
		plan_comboBox_1.setSelectedIndex(Integer.sum(Integer.valueOf(p.getStartLine()),-1));
		
		plan_p2_start.setText(p.getStartTime());
		plan_p2_end.setText(p.getEndTime());
		plan_p2_jumpline.setText(p.getJumpLine());
//		TableModel  tm =	planTable.getModel();
//		 int selectIndex =	planTable.getSelectedRow();
//		 tm.setValueAt(p.getName(), selectIndex, 0);
//		 tm.setValueAt(p.getType(), selectIndex, 1);
//		 
//		 panel_plan_main.updateUI();
//		 panel_plan_main.repaint();
	}
	
	
	String schemeName,schemeValue, planName,startLine, startTime,endTime, jumpLines,planType;
	Map<String,String> numMap ;
	protected void  initSchemes()
	{
		this.schemes = new ArrayList<>();
		List<String> schemesDir =  FileUtils.findChildrenList(FileUtils.getFile(planDir), true);
		if(CollectionUtils.isNotEmpty(schemesDir))
		{
			schemesDir.forEach(s->{
				
				Scheme sch = new Scheme(schemeName=Scheme.valueMap.get(s),schemeValue=s);
				List<String> plansDir =  FileUtils.findChildrenList(FileUtils.getFile(planDir+"/"+s), false);
				
				if(CollectionUtils.isNotEmpty(plansDir))
				{
					plans = new ArrayList<>();
					plansDir.forEach(p->{
						try {
							planName =p.split("\\.")[0];
							List<String> files =	FileUtils.readLines(FileUtils.getFile(planDir+"/"+s+"/"+p), Charset.forName("UTF-8"));
							if(CollectionUtils.isNotEmpty(files)) {
									planType = files.get(0).split("=")[1];
									startLine = files.get(1).split("=")[1];
									startTime = files.get(2).split("=")[1].split("-")[0];
									endTime = files.get(2).split("=")[1].split("-")[1];
									jumpLines = files.get(3).split("=")[1];
									
									numMap = new HashMap<>();
									for(int i = 4;i<files.size();i++)
									{
										String numKey =	files.get(i).split("=")[0];
										String numValue =files.get(i).split("=")[1];
										numMap.put(Plan.numValueyMap.get(numKey), numValue);
									}
									
								plans.add(new Plan(schemeName,schemeValue, planName,startLine, startTime,
										endTime, jumpLines,numMap,planType));
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					sch.setPlans(plans);
				}
				
				this.schemes.add(sch);
			});
		}
	}
	
	  private Object [][]planData=null;
	  private String planDateHead[]=null;
	  
	  private Statis statis;
	  
	  private List<Record> records = new ArrayList<>();
	  
	  private Record record; 
	
	//生成表格数据
    /**
     * @return
     */
    public Object[][] getPlanData(){
    	List<Plan> list = plans;
        planData=new Object[list.size()][planDateHead.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<planData.length;j++){
            	planData[i][0]=list.get(i).getName();
            	planData[i][1]=list.get(i).getType();
            }
        }
        return planData;
    }
    
    /**
     * @Title: getButtonSelect   
     * @Description: 取得所选按钮
     * @param: @param buttonGroup
     * @param: @return      
     * @return: JRadioButton      
     * @throws
     */
    protected  String getRadioButton(ButtonGroup buttonGroup) {
    	
    	Enumeration<AbstractButton> radioBtns=buttonGroup.getElements();
		while (radioBtns.hasMoreElements()) {
			AbstractButton btn = radioBtns.nextElement();
			if(btn.isSelected()){
				return btn.getText();
			}
		}
		return null;
    }
}
