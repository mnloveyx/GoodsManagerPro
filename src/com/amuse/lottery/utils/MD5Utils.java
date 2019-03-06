package com.amuse.lottery.utils;

import java.security.MessageDigest;

/**
 * @ClassName:  MD5Utils   
 * @Description:TODO   
 * @author: luzw    
 * @date:   2018年11月14日 下午3:00:05     
 * @Copyright: 2018 www.twikkercn.com. All rights reserved. 
 *
 */
public class MD5Utils {

    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    /**
     * MD5加密
     * @param origin 字符
     * @param charsetname 编码
     * @return
     */
    public static String MD5Encode(String origin, String charsetname){
        String resultString = null;
        try{
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(null == charsetname || "".equals(charsetname)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
//            sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder(); 
//            resultString = baseEncoder.encode(md.digest(resultString.getBytes(charsetname)));
        }catch (Exception e){
        }
        return resultString;
    }


    public static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return (hexDigIts[d1] + hexDigIts[d2]).toUpperCase();
    }
    
    
    public static String md5(String str, String charsetname) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] r  =  null;
            
          if(null == charsetname || "".equals(charsetname)){
        	  r = str.getBytes(charsetname);
          }else {
        	r = str.getBytes();
          }
          md.update(r);
            
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }

}
