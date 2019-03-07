///**
// * 
// */
//package com.panli.util;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Map;
//
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.twikkercn.tool.util.http.exception.ServiceException;
//
///**
// * JSON转换工具类
// * 
// */
//public class JSONUtils {
//
//    private static final ObjectMapper mapper = initCommonDefaultFeatures(new ObjectMapper());
//    
//    private static ObjectMapper initCommonDefaultFeatures(ObjectMapper mapper) {
//     // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行序列化
//        // Include.Include.ALWAYS 默认
//        // Include.NON_DEFAULT 属性为默认值不序列化
//        // Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
//        // Include.NON_NULL 属性为NULL 不序列化
//        mapper.setSerializationInclusion(Include.NON_NULL);// 对map不起作用
//        
//        //JSON 字符串转成Java Bean时，没有对应的属性时，不抛出异常
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        
////        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        
//        return mapper;
//    }
//    
//    /**
//     * 获取系统默认的JSON转换器，注意：应避免对全局对象进行重配置（影响所有）
//     * @return
//     */
//    public static ObjectMapper getDefaultObjectMapper() {
//        return mapper;
//    }
//    
//    /**
//     * 返回一个新的ObjectMapper对象，配置和默认的ObjectMapper一样
//     * <br>
//     * <br>
//     * 示例：return JsonUtils.cloneDefaultObjectMapper().configure(MapperFeature.USE_ANNOTATIONS, false);
//     * @return
//     */
//    public static ObjectMapper cloneDefaultObjectMapper() {
//        return initCommonDefaultFeatures(new ObjectMapper());
//    }
//    
//    /**
//     * 转json字符串，不开启注解（即对象中的json注解不生效）
//     * 
//     * @param data
//     * @return
//     */
////    public static String toNonAnnoString(Object data) {
////        try {
////            return nonAnnoMapper.writeValueAsString(data);
////        }
////        catch (Exception e) {
////            throw new ServiceException(e);
////        }
////    }
//    
//    /**
//     * 转json字符串
//     * 
//     * @param data
//     * @return
//     */
//    public static String toString(Object data) {
//        try {
//            return mapper.writeValueAsString(data);
//        }
//        catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    /**
//     * 转为Map
//     * @param json
//     * @param clazz
//     * @return
//     */
//    public static Map<String,Object> from(String json) {
//        try {
//            return from(json, new TypeReference<Map<String,Object>>() {});
//        }
//        catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//    
//    /**
//     * 转为pojo
//     * @param json
//     * @param clazz
//     * @return
//     */
//    public static <T> T from(String json, Class<T> clazz) {
//        try {
//            return mapper.readValue(json, clazz);
//        }
//        catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//    
//    /**
//     * 转为List对象
//     * @param json
//     * @param clazz
//     * @return
//     */
//    public static <T> List<T> fromList(String json, Class<T> clazz) {
//        try {
//            return mapper.readValue(json, mapper.getTypeFactory().constructParametrizedType(List.class, List.class, clazz));
//        }
//        catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//    
//    /**
//     * <pre>
//     * String json = "[{\"name\":\"person1\", \"age\":100},{\"name\":\"person2\", \"age\":99},{\"name\":\"person3\", \"age\":88}]";
//     * List&lt;Person&gt; perosnList = JsonUtils.from(json, new TypeReference&lt;List&lt;Person&gt;&gt;(){});
//     * </pre>
//     * @param json
//     * @param valueTypeRef 泛型参数配置
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//    public static <T> T from(String json, TypeReference valueTypeRef) {
//        try {
//            return mapper.readValue(json, valueTypeRef);
//        }
//        catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//    
//    /**
//     * <pre>
//     * String json = "[{\"name\":\"person1\", \"age\":100},{\"name\":\"person2\", \"age\":99},{\"name\":\"person3\", \"age\":88}]";
//     * List&lt;Person&gt; perosnList = JsonUtils.from(json, new TypeReference&lt;List&lt;Person&gt;&gt;(){});
//     * </pre>
//     * @param json
//     * @param valueTypeRef 泛型参数配置
//     * @return
//     */
//    public static <T> T from(String json, JavaType javaType) {
//        try {
//            return mapper.readValue(json, javaType);
//        }
//        catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//    
//    /**
//     * jackson的类型转换工厂
//     * @return
//     */
//    public static TypeFactory getTypeFactory() {
//        return mapper.getTypeFactory();
//    }
//}
