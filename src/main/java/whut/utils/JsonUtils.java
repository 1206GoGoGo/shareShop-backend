package whut.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 处理json字符串工具类
 * @author chen cheng
 *
 */
public class JsonUtils {
	
	private JsonNode rootNode;
	
	public JsonUtils(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		//create tree from JSON
		try {
			rootNode = mapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
}