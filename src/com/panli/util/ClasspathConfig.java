//package com.panli.util;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//public class ClasspathConfig {
//	private static Logger log = LoggerFactory.getLogger(ClasspathConfig.class);
//
//	private static Map<String, String> config = new HashMap<String, String>();
//	private static final String location = "classpath:config-*.properties";
//	static{
//		init();
//	}
//	public static void init() {
//		long startTime = System.currentTimeMillis();
//		log.info(">>> start load classpath config! <<<");
//		PathMatchingResourcePatternResolver resolover = new PathMatchingResourcePatternResolver();
//		Resource[] resources = null;
//		try {
//			resources = resolover.getResources(location);
//			if(resources != null){
//				for(Resource re : resources){
//					Properties p = new Properties();
//					p.load(re.getInputStream());
//					for (Map.Entry<Object, Object> entry : p.entrySet()) {
//						String key = entry.getKey().toString().trim();
//						String value = entry.getValue().toString().trim();
//						
//						if(config.containsKey(key)){
//							throw new Exception(String.format("文件%s中键值%s已经被定义过重复", re.getFilename(), entry.getKey()));
//						}
//						
//						if(StringUtils.isNoneBlank(key) && StringUtils.isNoneBlank(value)){
//							config.put(key, value);
//						}
//				    } 
//				}
//			}
//			log.info(">>> end load config! time consume " + (System.currentTimeMillis() - startTime) + "ms<<<");
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException("配置文件加载异常");
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new RuntimeException("配置文件加载异常");
//		}
//	}
//
//	public static String getProperty(String key, String defaultValue) {
//		String value = config.get(key);
//		return value != null ? value : defaultValue;
//	}
//
//	public static String getProperty(String key) {
//		return config.get(key);
//	}
//
//	
//	public static Integer getInteger(String key, Integer defaultValue) {
//		String value = config.get(key);
//		return value != null ? Integer.parseInt(value) : defaultValue;
//	}
//}
