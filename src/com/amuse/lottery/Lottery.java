package com.amuse.lottery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JTable;
import com.amuse.lottery.utils.DesUtil;
import com.amuse.lottery.utils.LotteryUtils;
import com.panli.util.HttpUtils;

import javax.swing.JTextPane;
import java.awt.List;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Lottery extends JFrame {
	
	  private static final String SKEY = "CSC88888";
	  private static final Charset CHARSET = Charset.forName("UTF-8");

	/**   
	 * @Fields serialVersionUID : TODO
	 */   
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		String url = "http://116.31.99.59:3580/CSC.aspx";
//		
//		 Map<String, String> vars = new HashMap<String, String>();
//		 vars.put("action", "getRGTemplet");
//         vars.put("AppName", "CSC");
//         vars.put("Name", "53五星专区");
//         vars.put("Path", "\\CLYLJH-SSC\\");
//         vars.put("Mkey", "rusoUwz3OM5NaI5rMsgJlQ==");
//		
//         String  result = 	HttpUtils.get(url, vars);
         
         Map<String, String> vars2 = new HashMap<String, String>();
         vars2.put("action", "getOpenData");
         vars2.put("App", "CXGCLYL");
         vars2.put("LotteryID", "TXFFC");
         vars2.put("LastExpect", "181114-0862");
         vars2.put("ExpectCount", "4");
         vars2.put("Mkey", "rusoUwz3OM5NaI5rMsgJlQ==");
         
//        System.out.println(Integer.valueOf("d"));
         
         String url2 = " http://116.31.99.59:8888/CSC.aspx";
         
         String  result2 = 	HttpUtils.get(url2, vars2);
         
       String der =   DesUtil.decrypt(result2,CHARSET, SKEY);
       
//     String  der = "181114-0937\t31451\r\n181114-0936\t25991\r\n181114-0935\t65789\r\n181114-0934\t83135\r\n181114-0933\t64638\r\n181114-0932\t86478\r\n181114-0931\t80549\r\n181114-0930\t91820\r\n181114-0929\t98131\r\n181114-0928\t30233\r\n181114-0927\t43469\r\n181114-0926\t89730\r\n181114-0925\t00984\r\n181114-0924\t36053\r\n181114-0923\t85592\r\n181114-0922\t25175\r\n181114-0921\t20274\r\n181114-0920\t68887";
       
//       System.out.println(der);
         
       java.util.List<Opendata>  ls = LotteryUtils.buildOpendata(der);
         
//         http://116.31.99.59:8888//CSC.aspx?action=getOpenData&App=CXGCLYL&LotteryID=TXFFC&LastExpect=181112-1008&ExpectCount=4&Mkey=rusoUwz3OM5NaI5rMsgJlQ==
//         http://116.31.99.59:8888//CSC.aspx?action=getOpenData&App=CXGCLYL&LotteryID=TXFFC&LastExpect=181112-1008&ExpectCount=4&Mkey=rusoUwz3OM5NaI5rMsgJlQ==
         
//         System.out.println("解密后："+DesUtil.decrypt(result,CHARSET, SKEY));
//         System.out.println("解密后1："+DesUtil.decrypt(result2,CHARSET, SKEY));
//         System.out.println("解密后2："+DesUtil.decrypt("RDcUQ0HX0XgqhFjwLJ7O1vP8qxMhyo0k",CHARSET, SKEY));
//         System.out.println("解密后3："+DesUtil.decrypt("RDcUQ0HX0XiThM5Wqd1va7YTbD1CodhGfC0f/6ELZJTDHiWxg+IL/Q==",CHARSET, SKEY));
//         System.out.println("解密后4："+DesUtil.decrypt("RDcUQ0HX0XiThM5Wqd1va0Q6Lv0RcJIS",CHARSET, SKEY));
         
      
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lottery frame = new Lottery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the frame.
	 */
	public Lottery() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("登录");
		contentPane.add(button, BorderLayout.SOUTH);
		
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane, BorderLayout.WEST);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
	}
	

}
