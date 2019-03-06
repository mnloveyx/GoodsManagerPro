//package com.amuse.lottery;
//
//import java.nio.charset.Charset;
//
//import io.netty.handler.codec.base64.Base64;
//
//public class Test2 {
//
//    private static final String SKEY = "CSC88888";
//    private static final Charset CHARSET = Charset.forName("UTF-8");
//
//    public static void main(String[] args) {
//        // 待加密内�?
//        String password = "abcd";
//        String encryptPassword = DesUtil.encrypt(password, CHARSET, SKEY);
//        System.out.println("原来的：" + password);
//        System.out.println("加密后："+encryptPassword);
//        // 直接将如上内容解�?
//        String decryPassword;
//        String a = "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";
//        String b = "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";
//        String c = "fWXORyrVKnwiLt1e2Uz/Z5Sb4v4E+AB35wNsUNKi6zUhRnerXsILjk+Fb4im9+/p+whhQnJ3LAnfXffIvy5y7BFM2TVpsgm7UBEuFEm+emlr5Il5oLuR32cZNLAuLkU5WuVgxRTP1WcAVLXT9zH/82jMyoiqsdl6IhI2MtSzqlihPdfhwVHmjXAlJAADaJMLKkhvTmguY22wbsQpzlFrIjf1cBEcVZt/+gLKHEJtoQm6+lLcEMqUXlgaNxy8tAO9FU8G0bpYhR3V4nPu/AnYuISSOrcSGDvCGJ7ebf5sAJFiDfp/ZZY6TsFney1Con24+V8RfgMUmjvBgP2AHvzz+v/alleIR6lVwenKEIPFrs1Ke1ja+LaozguHuWjIihVZeLb7PfNhdU58EoYJvwqzO2feeQtlKiqJDzr1ypDn1YD37LGqtOKgMvBCcVhDg6Jn7N43qX2oXxe2iBR/HTo0ThI64cecExyCAJYS+KHkSxCWGwhOIRbqpwFHua9AIFzynV04Uq5tIRJUDoYlof/MbamuMncV90yvoPR1ituX0zUUYTL59jPox1K0huRR50idb2Eoz3kAKcaAZ7U5HoNuU7ibsSzbiAxR+1Ce/54mQc4=";
//        
//        
//        try {
//            decryPassword = DesUtil.decrypt(encryptPassword, CHARSET, SKEY);
//            System.out.println("解密后："+decryPassword);
//            String ad = DesUtil.decrypt(a, CHARSET, SKEY);
//            System.out.println("解密后："+ad);
//            String ab = DesUtil.decrypt(b, CHARSET, SKEY);
//            System.out.println("解密后："+ab);
//            String ac = DesUtil.decrypt(c, CHARSET, SKEY);
//            System.out.println("解密后："+ac);
//            System.out.println("解密后："+DesUtil.decrypt("uVX7LpZeyeI2G7uI0XEuzWgdYrtdfMCR",CHARSET, SKEY));
//            System.out.println("解密后："+DesUtil.decrypt("RDcUQ0HX0Xin7sxkPkIaY3NDo/ZHhes8",CHARSET, SKEY));
//            
//           
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}