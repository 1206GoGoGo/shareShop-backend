package whut.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 处理json字符串工具类
 * @author chen cheng
 *
 */
public class JsonUtils {
	// 定义jackson对象
    private static final ObjectMapper mapper = new ObjectMapper();
	
	private JsonNode rootNode;
	
	public JsonUtils(String jsonString) {
		try {
			rootNode = mapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JsonNode getJsonRoot() {
		return rootNode;
	}
	
	public int getIntValue(String key) {
		return rootNode.findValue(key).asInt();
	}
	
	/**
	 * 传递空字符串null会被认为是null
	 * @param key
	 * @return
	 */
	public String getStringValue(String key) {
		if(rootNode.findValue(key).asText().equals("null")) {
			return null;
		}else {
			return rootNode.findValue(key).asText();
		}
	}
	
	public Double getDoubleValue(String key) {
		return rootNode.findValue(key).asDouble();
	}
	
	 /**
     * 将对象转换成json字符串。
     */
    public static String objectToJson(Object data) {
    	try {
			String string = mapper.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	
	/**
     * 将json数据转换成pojo对象list
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = mapper.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
	
}