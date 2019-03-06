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
import javax.swing.AbstractListModel;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

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
	private JTextField plan_p2_changeline;
	private JTextField textField_end;
	private JTable table_2;
	private JPanel plan_p2;
	final JPanel plan_p;
	
//	private JPanel panel_14 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm2 frame = new MainFrm2();
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
	public MainFrm2() {
		setTitle("娱乐管理系统V1.0.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/goods_logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"极速赛车", "极速飞艇", "幸运飞艇"}));
		
		JLabel lblNewLabel_3 = new JLabel("用户名");
		
		JLabel lblNewLabel_19 = new JLabel("laomeng666");
		
		JLabel label_1 = new JLabel("额度");
		
		JLabel label_2 = new JLabel("0.0");
		
		JLabel label_3 = new JLabel("未结算金额");
		
		JLabel lblNewLabel_20 = new JLabel("0.0");
		
		JLabel lblNewLabel_21 = new JLabel("今日输赢");
		
		JLabel label_4 = new JLabel("0.0");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_19, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_20, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_21)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(348))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_19)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(lblNewLabel_20)
						.addComponent(lblNewLabel_21)
						.addComponent(label_4))
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
				false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(62);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(129);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(41);
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
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("2.方案设定", null, panel_8, null);
		
		final JPanel panel_12 = new JPanel();
		planTable = new JTable();
		planTable.setFillsViewportHeight(true);
		
		planTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){  
            @Override  
            public void valueChanged(ListSelectionEvent e)  
            {  
				int sr = planTable.getSelectedRow();
              if (sr == -1) {
                  return;
              }
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
          }
        });  
		
		planTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table.setselect
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(planTable);
		//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				planTable.setModel(new DefaultTableModel(
					new Object[][] {
						{"\u65B9\u68481", "\u5F00\u67D0\u6295\u67D0"},
						{"\u65B9\u68482", "\u53CC\u5355"},
						{"\u65B9\u68483", "\u5355\u53CC"},
						{"\u65B9\u68484", "\u5927\u5C0F"},
						{"\u65B9\u68485", "\u5C0F\u5927"},
					},
					new String[] {
						"\u65B9\u6848", "\u73A9\u6CD5"
					}
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
				GroupLayout gl_panel_8 = new GroupLayout(panel_8);
				gl_panel_8.setHorizontalGroup(
					gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_panel_8.setVerticalGroup(
					gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_12, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel_8.setLayout(gl_panel_8);
				
				JPanel panel_10 = new JPanel();
				tabbedPane.addTab("3.自动投注", null, panel_10, null);
				
				JPanel panel_15 = new JPanel();
				tabbedPane.addTab("4.参考数据", null, panel_15, null);
				
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
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_6, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_2 = new JLabel("这是一些其它的提示信息");
		panel_6.add(lblNewLabel_2);
		
		JPanel panel_13 = new JPanel();
		
		JButton plan_b_save = new JButton("保存");
		plan_b_save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_13.add(plan_b_save);
		
		JButton plan_b_update = new JButton("修改");
		plan_b_update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		panel_12.setLayout(new BorderLayout(0, 0));
		plan_p = new JPanel();
		plan_p.setBorder(new TitledBorder(null, "\u8BA1\u5212\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plan_p.setPreferredSize(new Dimension(400, 180));
		//		plan_p.setBounds(20, 20, 20, 20);
		//		plan_p.setb
		//		JPanel panel_14 = new JPanel();
		//		panel_12.add(panel_14);
				
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
		 
		 JComboBox plan_comboBox_1 = new JComboBox();
		 plan_comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		 
		 JLabel lblNewLabel_15 = new JLabel("时间范围");
		 
		 plan_p2_start = new JTextField();
		 plan_p2_start.setText("50");
		 plan_p2_start.setColumns(10);
		 
		 JLabel lblNewLabel_16 = new JLabel("-");
		 
		 plan_p2_changeline = new JTextField();
		 plan_p2_changeline.setText("1,2,3,4,5,6");
		 plan_p2_changeline.setColumns(10);
		 
		 JLabel lblNewLabel_17 = new JLabel("跳   线");
		 
		 textField_end = new JTextField();
		 textField_end.setText("60");
		 textField_end.setColumns(10);
		 
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
		 					.addComponent(plan_p2_changeline, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
		 				.addGroup(gl_plan_p2.createSequentialGroup()
		 					.addComponent(lblNewLabel_15)
		 					.addGap(18)
		 					.addComponent(plan_p2_start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.UNRELATED)
		 					.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(textField_end, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
		 				.addComponent(textField_end, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		 			.addGap(19)
		 			.addGroup(gl_plan_p2.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(lblNewLabel_17)
		 				.addComponent(plan_p2_changeline, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		 			.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
		 			.addComponent(label)
		 			.addContainerGap())
		 );
		 plan_p2.setLayout(gl_plan_p2);
		 panel_12.add(plan_p2);
		panel_13.add(plan_b_update);
		panel_12.add(panel_13, BorderLayout.NORTH);
	}
}
