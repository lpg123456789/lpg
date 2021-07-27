package gk.server.shine.data.manager;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lpg.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameConfigParser {

	private final static Logger logger = LoggerFactory.getLogger(GameConfigParser.class);

	private final static ObjectMapper objectMapper = initObjectMapper();

	private static ObjectMapper initObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		// 是否可以解析带有/* //格式的注释的json
		objectMapper.configure(Feature.ALLOW_COMMENTS, true);
		// 是否可以解析带有/* //格式的注释的json
		objectMapper.configure(Feature.ALLOW_YAML_COMMENTS, true);
		// 是否可以解析带有单引号的字符串
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		// // 是否可以解析结束语控制字符
		// objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		// // 是否可以解析以"0"为开头的数字(如: 000001),解析时则忽略0
		// objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
		// 若json的属性比对应的java类多 是否抛出异常
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 不使用get set方法 直接使用变量名进行序列化/反序列化
		objectMapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		// 注册各种自定义类的解析器
		SimpleModule module = new SimpleModule();
		// TODO
	
		
		objectMapper.registerModule(module);
		return objectMapper;
	}

	/**
	 * 对象转为json
	 * 
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toString(Object obj) throws JsonProcessingException {
		try {
			String jsonStr = objectMapper.writeValueAsString(obj);
			return jsonStr;
		} catch (JsonProcessingException e) {
			logger.error("objToJsonString error.", e);
			throw e;
		}
	}

	/**
	 * json转为对象
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @param typeReference
	 * @return
	 * @throws IOException
	 */
	public static <T> T toObject(String jsonStr, TypeReference<T> typeReference) throws IOException {
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		try {
			T obj = objectMapper.readValue(jsonStr, typeReference);
			return obj;
		} catch (IOException e) {
			logger.error("jsonToObject error", e);
			throw e;
		}
	}

	/**
	 * json转为对象
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> T toObject(String jsonStr, Class<T> clazz) throws IOException {
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		try {
			T obj = objectMapper.readValue(jsonStr, clazz);
			return obj;
		} catch (IOException e) {
			logger.error("jsonToObject error", e);
			throw e;
		}
	}

	/**
	 * map结构的json转为list
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> toList(String jsonStr, Class<T> clazz) throws IOException {
		List<T> list = new ArrayList<>();
		if (StringUtils.isEmpty(jsonStr)) {
			return list;
		}
		try {
			ObjectReader reader = objectMapper.readerFor(clazz);
			JsonNode root = reader.readTree(jsonStr);
			if (root.isNull()) {
				//
			} else if (root.isArray()) {
				MappingIterator<T> iterator = reader.readValues(jsonStr);
				while (iterator.hasNext()) {
					T obj = iterator.next();
					list.add(obj);
				}
			} else {
				Iterator<JsonNode> elements = root.elements();
				while (elements.hasNext()) {
					JsonNode node = elements.next();
					try {
						T obj = reader.readValue(node);
						list.add(obj);
					}catch (Exception e){
						e.printStackTrace();
						logger.error("error node : " +  node.toString());
					}

				}
			}
			return list;
		} catch (IOException e) {
			logger.error("jsonToObject error", e);
			throw e;
		}
	}

}
