package com.panli.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.panli.model.Api;
import com.panli.model.Code;
import com.panli.model.LoginMsg;
import com.panli.model.User;
import com.panli.util.DateTypeAdapter;
import com.panli.util.HttpClientUtil;
import com.panli.util.StringUtil;
import com.panli.util.SubjectUtils;

import lombok.extern.slf4j.Slf4j;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 登录页面视图层
 * @author Peter
 *
 */
@Slf4j
public class LoginFrm extends JFrame {

	/**   
	 * @Fields serialVersionUID : TODO
	 */   
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JTextField codeTxt;
	private JLabel codeimage;
	
	private String codeData = "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";
	private String cryptograph;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		deGetCode();
		//该表系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrm.class.getResource("/images/goods_logo.png")));
		setTitle("娱乐辅助系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("管理员登录界面");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/goods_logo.png")));
		
		JLabel label_1 = new JLabel("用户名");
		label_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/user.png")));
		
		JLabel label_2 = new JLabel("密  码");
		label_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/password.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed(arg0);
			}
		});
		btnNewButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/login.png")));
		
		JButton button = new JButton("重置");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValueActionPerformed(arg0);
			}
		});
		button.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/reset.png")));
		
		JLabel lblNewLabel = new JLabel("验证码");
		lblNewLabel.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/code.png")));
		
		codeTxt = new JTextField();
		codeTxt.setColumns(10);
		 codeimage = new JLabel("");
		 codeimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateCode();
			}
		});
//		codeimage.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/goods_logo.png")));
//		codeimage.setIcon(new ImageIcon(Base64.getDecoder().decode("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")));
		codeimage.setIcon(new ImageIcon(Base64.getDecoder().decode(codeData)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(183)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(label_2)
						.addComponent(label_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordTxt, 167, 167, 167)
						.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.addComponent(button, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(codeTxt, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(codeimage, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addGap(92))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(btnNewButton))
					.addContainerGap(145, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(label)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(codeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(codeimage, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		//居中显示
		this.setLocationRelativeTo(null);
	}
	
	private void  deGetCode()
	{
		String  result2 = 	HttpClientUtil.get(Api.codeeurl);
		 Gson gson = new Gson();
		Code code =  gson.fromJson(result2, Code.class);
		this.codeData = code.getCaptchImageData();
		this.cryptograph = code.getCryptograph();
		
	}
	
	private void updateCode()
	{
		deGetCode();
		codeimage.setIcon(new ImageIcon(Base64.getDecoder().decode(codeData)));
		codeimage.updateUI();
		codeimage.repaint();
	}

	/**
	 * 管理员登录
	 * @param arg0
	 */
	private void loginActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String userName = this.userNameTxt.getText();
		String password = String.valueOf(this.passwordTxt.getPassword());
		String code = new String(this.codeTxt.getText());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "用户名不能为空!");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空!");
			return;
		}
		if(StringUtil.isEmpty(code)){
			JOptionPane.showMessageDialog(null, "验证码不能为空!");
			return;
		}
		User user = new User(userName, password,code,cryptograph);
		try {
			
			Gson g = new Gson();
			String params = 	g.toJson(user);
			String  result = 	HttpClientUtil.post(Api.login, params);
			 Gson gson = new GsonBuilder()
				        .registerTypeAdapter(Date.class, new DateTypeAdapter())
				        .create();
			LoginMsg msg =  gson.fromJson(result, LoginMsg.class);
//			User currentUser = new User("123", "222", "22");//userDao.login(conn, user);
			if(msg!=null && msg.getToken()!=null){
				Map<String,String> headMap = new HashMap<>();
				headMap.put("token", msg.getToken());
				String  result2 = 	HttpClientUtil.get(Api.member_userInfo,null,headMap);
				user = gson.fromJson(result2, User.class);
				
				if(user!=null && null!= user.getResult())
				{	
					user = user.getResult();
					SubjectUtils.setUser(user);
					dispose();
					new MainFrm2(user).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "登录失败!");
					updateCode();
				}
			
			}else{
				JOptionPane.showMessageDialog(null, "登录失败!");
				updateCode();
			}
			
		} catch (Exception e) {
			log.error("loginerror:{}",e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "登录失败!");
			updateCode();
		}finally{
			
		}
		
	}

	/**
	 * 重置事件
	 * @param arg0
	 */
	private void resetValueActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.resetValue();
	}
	/**
	 * 重置表单事件
	 */
	private void resetValue(){
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
		this.codeTxt.setText("");
	}
}