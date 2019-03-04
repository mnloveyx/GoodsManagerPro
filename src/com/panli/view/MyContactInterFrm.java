package com.panli.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class MyContactInterFrm extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyContactInterFrm frame = new MyContactInterFrm();
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
	public MyContactInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u8054\u7CFB\u6211\u4EEC");
		setBounds(100, 100, 528, 356);
		
		JLabel lblqq = new JLabel("本软件由QQ171676786提供技术支持");
		lblqq.setFont(new Font("宋体", Font.BOLD, 15));
		lblqq.setIcon(new ImageIcon(MyContactInterFrm.class.getResource("/images/smile.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(lblqq)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(111, Short.MAX_VALUE)
					.addComponent(lblqq)
					.addGap(87))
		);
		getContentPane().setLayout(groupLayout);

	}
}
