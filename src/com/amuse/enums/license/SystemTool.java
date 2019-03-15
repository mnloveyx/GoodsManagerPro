package com.amuse.enums.license;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;

import lombok.extern.slf4j.Slf4j;

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.widgets.DateTime;

import sun.misc.BASE64Encoder;

/**
 * @className: SystemTool
 * @description: 与系统相关的一些常用工具方法. 目前实现的有：获取MAC地址、IP地址、C硬盘序列号
 * @author: lzw
 * @createTime: 2017-12-01 下午10:03:44
 */
@Slf4j
public class SystemTool {
    /**
     * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
     */
	@Deprecated
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }
/**
 * 生成加过密的机器码   机器码组成 ：mac物理地址 ，本机cpu 序列号 C硬盘序列号
 * @return 
 * @throws IOException
 * @throws NoSuchAlgorithmException
 */
public static String getMachineCode() {
      String str4=getMAC();// mac物理地址
      String str5=getCpuNumber();//本机cpu 序列号 
      String str6=getSerialNumber("C");//C硬盘序列号
      String MachineCode= str4+str5+str6;
      String md5 = "";
	try {
		md5 = EncoderByMd5(MachineCode);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//md5加密 
	
	log.debug("mac:{},cpuNumber:{},getSerialNumber(C):{},MachineCode:{},md5:{}",str4,str5,str6,MachineCode,md5);
	
    return md5;
}

public final static String WIN_OS = "WINDOWS";
public final static String MAC_OS = "MAC";
public final static String LINUX_OS = "LINUX";
public final static String OTHER_OS = "OTHER";


public static String getOS() {
    if (SystemUtils.IS_OS_WINDOWS){
        return WIN_OS;
    }
    if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX){
        return MAC_OS;
    }
    if (SystemUtils.IS_OS_UNIX){
        return LINUX_OS;
    }
    return OTHER_OS;
}

/**
 * 对机器码进行MD5加密
 * @return 
 * @throws IOException
 * @throws NoSuchAlgorithmException
 */

public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    //确定计算方法
    MessageDigest md5=MessageDigest.getInstance("MD5");
    BASE64Encoder base64en = new BASE64Encoder();
    //加密后的字符串
    String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
    return newstr;
}
/**
 * 验证机器码
 * @param str 数据源值
 * @return
 * @throws NoSuchAlgorithmException
 * @throws IOException
 */
public static Boolean ver_MacineCode(String str) throws NoSuchAlgorithmException, IOException {
      if(str.equals(getMachineCode())){
          return true;  
      }
    return false;
}
    
/**
 * mac物理地址
 * @return
 */ @Deprecated
    public static String getMAC() {
        String os =getOSName();
        String mac = "";
        // System.out.println("OS Type:" + os);
        if (os.startsWith("windows")) {
            // 本地是windows
            mac = getWindowsMACAddress();
            // System.out.println("MAC Address:" + mac);
        } else {
            // 本地是非windows系统 一般就是unix
            mac = getUnixMACAddress();
            // System.out.println(mac);
        }
        return mac;
    }

    /**
     * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
     * 
     * @return mac地址
     */
    @Deprecated
    public static String getUnixMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡
                                                                    // 显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
                if (index >= 0) {// 找到了
                    mac = line.substring(index + "hwaddr".length() + 1).trim();// 取出mac地址并去除2边空格
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取widnows网卡的mac地址.
     * 用模式匹配方式查找MAC地址，与操作系统的语言无关
     * @return mac地址
     */
    @Deprecated
    public static String getWindowsMACAddress(){
          Pattern macPattern = Pattern.compile(".*((:?[0-9a-f]{2}[-:]){5}[0-9a-f]{2}).*", Pattern.CASE_INSENSITIVE);
         String mac = null;
            try {
                Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
      
                InputStream is = pro.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String message = br.readLine();
      
                int index = -1;
                while (message != null) {
                      Matcher matcher = macPattern.matcher(message);
                      if (matcher.matches()) {
                        mac= matcher.group(1).trim();
                        break;
                          // macAddressList.add(matcher.group(1).replaceAll("[-:]",
                          // ""));//去掉MAC中的“-”
                      }
                   /* if ((index = message.indexOf("物理地址")) > 0) {
                        mac = message.substring(index + 36).trim();
                        break;
                    }*/
                    message = br.readLine();
                }
                br.close();
                pro.destroy();
            } catch (IOException e) {
                System.out.println("Can‘t get mac address!");
                return null;
            }
            return mac;
    }

    

    /**
     * @return 本机主机名
     */
    public static String getHostName() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (ia == null) {
            return "some error..";
        } else
            return ia.getHostName();
    }

    /**
     * @return 本机IP 地址
     */
    @Deprecated
    public static String getIPAddress() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (ia == null) {
            return "some error..";
        } else
            return ia.getHostAddress();
    }
    
 // 获取mac地址
    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                    continue;
                } else {
                    mac = netInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        if (sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("get MAC address fail", e);
        }
        return "";
    }

    // 获取ip地址
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("get IP address fail", e);
        }
        return "";
    }

    /**
     * @return 本机cpu 序列号
     * @throws IOException
     */
    public static String getCpuNumber() {
        Process process;
		try {
			process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
	        process.getOutputStream().close();
	        Scanner sc = new Scanner(process.getInputStream());
	        String property = sc.next();
	        String serial = sc.next();
	        return serial;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return null;
        
    }
    /**
     * 获取硬盘序列号
     * @param drive
     * @return
     */

    public static String getSerialNumber(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
    /**
     * InputStreamReader和BufferedReader方法 
     * 优点: 可以获取键盘输入的字符串 
     * 缺点: 如何要获取的是int,float等类型的仍然转码
     */
    
     public static String ReadTest(){ 
            System.out.println("Please input Data:"); 
            String name = "";
            InputStreamReader is = new InputStreamReader(System.in);  
            BufferedReader br = new BufferedReader(is); 
            try{
              name = br.readLine(); 
             
            } 
            catch(IOException e){ 
              e.printStackTrace(); 
            } 
            return name;  
          }
     /** 
       * Scanner类中的方法
       * 优点: 可以获取键盘输入的字符串 
       * 优点: 有现成的获取int,float等类型数据，非常强大，也非常方便 
       */
     public static void ScannerTest(){ 
            Scanner sc = new Scanner(System.in); 
            System.out.println("ScannerTest, Please Enter Name:"); 
            String name = sc.nextLine(); 
            System.out.println("ScannerTest, Please Enter Age:"); 
            int age = sc.nextInt();    
            System.out.println("ScannerTest, Please Enter Salary:"); 
            float salary = sc.nextFloat(); 
            System.out.println("Your Information is as below:"); 
            System.out.println("Name:" + name +"\n" + "Age:"+age + "\n"+"Salary:"+salary); 
          } 
     /**
          * @param date
          * @param day 想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
           * @return
           */
          public static Date getSomeDay(Date date, int day){
             Calendar calendar = Calendar.getInstance();
              calendar.setTime(date);
              calendar.add(Calendar.DATE, day);
             return calendar.getTime();
         }
          /**
           * 取工程绝对路径如 e://
           * @return
           */
          public static String getProjectPath() {
              try {
                  return URLDecoder.decode(System.getProperty("user.dir"), "UTF-8");
              } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
              }
              return System.getProperty("user.dir").replace("20%", " ");
          }
    /**
     * 测试用的main方法.
     * 
     * @param argc
     *            运行参数.
     * @throws Exception 
     */
    public static void main(String[] argc) throws Exception {
        getSomeDay(new Date(),60);
        //getWindowsMACAddress();
    }
}