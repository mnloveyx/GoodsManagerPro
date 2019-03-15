package com.amuse.enums.license;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
/**
 * @className: RSATester
 * @description: RSA 加密 解密 类
 * @author: lzw
 * @createTime: 2017-12-01 下午10:03:44
 */
public class RSATester {
    static String publicKey;
    static String privateKey;
    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
//        test();
         licenseCheck();
        // getConfig("");
    }
    /**
     * 公钥加密——私钥解密
     * 
     * @throws Exception
     */
    static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        Date endtime = null;
        String str1 = "";
        String str2 = "";
        String str22 = "";
        String str2type = "";
        String str3 = "";
        Boolean status = true;
        // 明文
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true) {
            System.out.println("请输入授权类型 1 or 2 :  1、客户端 2、服务端");
            str3 = SystemTool.ReadTest();
            if ("1".equals(str3) || "2".equals(str3)) {
                break;
            } else {
                System.out.println("您输入的授权类型有误，请重新输入！！！");
                continue;
            }
        }
        // System.out.println("请输入授权过期日期：格式如：2017-12-01 12:30:30");
        while (status) {
            System.out.println("请选择授权过期日期：1、一个月  2、三个月  3、六个月  4、一年  5、三年  6、自定义日期");
            str2type = SystemTool.ReadTest();
            str1 = df.format(new Date());
            if ("1".equals(str2type)) {
                endtime = SystemTool.getSomeDay(new Date(), 30);
                break;
            } else if ("2".equals(str2type)) {
                endtime = SystemTool.getSomeDay(new Date(), 90);
                break;
            } else if ("3".equals(str2type)) {
                endtime = SystemTool.getSomeDay(new Date(), 180);
                break;
            } else if ("4".equals(str2type)) {
                endtime = SystemTool.getSomeDay(new Date(), 365);
                break;
            } else if ("5".equals(str2type)) {
                endtime = SystemTool.getSomeDay(new Date(), 1095);
                break;
            } else if ("6".equals(str2type)) {
                System.out.println("请输入自定义日期 格式如：2017-12-01 12:30:30");
                str22 = SystemTool.ReadTest();
                break;
            } else {
                System.out.println("您输入的授权过期日期类型有误，请重新出入！！！");
                continue;
            }
        }
        if ("6".equals(str2type)) {
            str2 = str22;
        } else {
            str2 = df.format(endtime);
        }
        // String machineCode=SystemTool.getMachineCode();//MD5加密的机器码
        System.out.println("请输入机器码：");
        String machineCode = SystemTool.ReadTest();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("starttime", str1);
        localJSONObject.put("endtime", str2);
        localJSONObject.put("lictype", str3);
        localJSONObject.put("machinecode", machineCode);
        String source = localJSONObject.toString();
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        // byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        String encodedDatastr = bytesToString(encodedData);
        // System.out.println("加密后文字：\r\n" + new String(encodedData));
        // System.out.println("加密后经转换格式后的数据：\r\n" + encodedDatastr);
        // 签名
        // System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        // System.err.println("签名:\r" + sign);
        // 写入本地的内容 签名 + 加密后的密文
        String content = publicKey+ "$$" + sign + encodedDatastr;
        // 生成认证文件
        String path = SystemTool.getProjectPath();
        File localFile2 = new File(path + "\\xxx.lic");
        if (localFile2.exists())
            localFile2.delete();
        PrintWriter localPrintWriter2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream(localFile2)), true);
        localPrintWriter2.println(content);
        localPrintWriter2.close();
        System.out.println("xxx.lic 生成成功!目录：" + path);
    }

    public static String bytesToString(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append(arr[i]);
            } else {
                sb.append("|").append(arr[i]);
            }
        }
        return sb.toString();
    }

    public static byte[] stringToBytes(String str) {

        String[] arr = str.split("\\|");

        byte[] bytes = new byte[arr.length];
        arr[arr.length - 1] = arr[arr.length - 1].trim();
        for (int i = 0; i < arr.length; i++) {
            bytes[i] = Byte.valueOf(arr[i]);
        }
        return bytes;
    }

    /**
     * 单机验证授权 解析授权文件 xxx.lic
     * 
     * @return
     * @throws WDKException
     */
    public static boolean licenseCheck() {
        try {
            String path = SystemTool.getProjectPath();
            File localFile = new File(path + "/xxx.lic");
            if (localFile.exists()) {
                byte[] arrayOfByte = new byte[Integer.parseInt(Long.toString(localFile.length()))];
                FileInputStream localFileInputStream = new FileInputStream(localFile);
                localFileInputStream.read(arrayOfByte);
                localFileInputStream.close();
                String str = new String(arrayOfByte);
                // System.out.println("读取文件内容：" + str);
                String decodedData1 = "";
                String sign = "";
                String publickey2 = "";
                if (str.indexOf("$$") != -1) {
                    sign = str.substring(str.indexOf("$$") + 2, str.indexOf("=") + 1);// 签名
                    publickey2 = str.substring(0, str.indexOf("$$"));// 公钥
                 
                } else {
                    System.out.println("授权文件已被修改，如需继续使用本软件请重新进行授权！！！");
                    return false;
                }
                if (str.indexOf("=") != -1) {
                    decodedData1 = str.substring(str.indexOf("=") + 1, str.length() - 1);// 密文
                } else {
                    System.out.println("授权文件已被修改，如需继续使用本软件请重新进行授权！！！");
                    return false;
                }
                // System.out.println("解析后的公钥：" + publickey2);
                byte[] data2 = stringToBytes(decodedData1);// 解析后的密文（已加密数据）
                // 签名验证
                System.out.println("开始验证签名——————————————————————————————————");
                // System.out.println("解析后的签名：" + sign);
                boolean status = RSAUtils.verify(data2, publickey2, sign);
                System.err.println("签名验证结果:\r" + status);
                if (status) {// 签名通过
                    // 解密
                    JSONObject localJSONObject = new JSONObject();
                    long LIC_Frame_endtime;
                    System.out.println("开始解密——————————————————————————————————");
                    byte[] decodedData = RSAUtils.decryptByPublicKey(data2, publickey2);
                    String target = new String(decodedData); // 明文
                    // System.out.println("解密后: \r\n" + target);
                    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    localJSONObject = (JSONObject) JSONObject.parse(target);
                    String lictype = localJSONObject.getString("lictype");
                    String machinecode = localJSONObject.getString("machinecode");
                    String endtime = localJSONObject.getString("endtime");
                    String starttime = localJSONObject.getString("starttime");
                    if (SystemTool.ver_MacineCode(machinecode)) {// 机器验证通过
                        if ("1".equals(lictype)) {
                            if ((null == endtime) || ("".equals(endtime))) {
                                LIC_Frame_endtime = 0L;
                            } else {
                                LIC_Frame_endtime = ((Date) localSimpleDateFormat.parseObject(endtime)).getTime();
                                boolean bool = RSAUtils.verifyendTime(LIC_Frame_endtime);// 校验是否过期
                                System.out.println("授权到期时间:" + endtime);
                                System.err.println("授权验证结果:\r" + bool);
                                if (!bool) {
                                    System.out.println("授权信息已过期，请重新授权！！");
                                    return false;
                                }
                            }
                        } else {
                            System.out.println("授权文件类型不正确，请重新授权！！！");
                            return false;
                        }
                    } else {
                        System.out.println("机器码验证失败，请重新授权！！！");
                        return false;
                    }
                } else {
                    System.out.println("签名验证失败，请重新授权！！");
                    return false;
                }
            } else {
                System.out.println(path + "未发现授权文件xxx.lic，请检查...");
                return false;
            }
        } catch (Exception localException) {
            System.out.println("授权文件xxx.lic解析失败...");
            return false;
        }
        return true;
    }

}