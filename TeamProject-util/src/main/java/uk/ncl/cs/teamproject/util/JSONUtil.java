package uk.ncl.cs.teamproject.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
	/**
	 * Property types that do not use JSONObject to from
	 */
	public static final String TYPES = "java.lang.String; java.lang.Integer; "
			+ "java.lang.Long; java.lang.Float; java.lang.Boolean;";

	/**
	 * JSON填参
	 * 
	 * @param json
	 *            JSONObject objects
	 * @param key
	 *            Parameter name
	 * @param object
	 *            Parameter values
	 * @return The JSONObject object after filling in the parameters
	 */
	public static JSONObject putObject(JSONObject json, String key, Object object) {
		try {
			if (TYPES.indexOf(object.getClass().getName()) >= 0) {
				json.put(key, object);
			} else {
				json.put(key, JSONObject.fromObject(object));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Get a JSON object of a class based on parameters
	 * 
	 * @param object
	 *            class
	 * @param selector
	 * 	This is a custom selector with the following selection criteria.
	 * 	"all" means all parameters of the current class, in other cases they are passed according to the current parameters, all parameters need to have a corresponding get function The parameters are separated by spaces, e.g.
	 * 	"name password" means that the value of the current class getName and getPassword is taken.
	 * 	and the values are stored in the JSON file under the keys "name" and "password", in addition if the parameters are somewhat separated, it means that the current parameter is a class
	 * 	For example, "user.name" means that the return value of the getName method is taken from the return value of the getUser method (User class object) For example
	 * 	"params user.name user.test.param1 user.test.param2"
	 * 	When the hierarchy "." For convenience, when there are many levels of ".", we also provide a quick way to add a "parameter" to the . followed by a uniform "{" and "}" for the parameters.
	 * 	and the first character inside the brackets is the separator e.g. "{,params,user.name,user.test.param1,user.test.param2}"
	 * 	but the separator must not be "." , "{", "}", "*" and 26 letters, and it can't be a parent directory, otherwise it will report an error
	 * 	The multi-level JSON match above can be simply written as
	 *            "{ params user.{,name,test.{:param1:param2}}}"
	 * @return JSON profile
	 */
	public static JSONObject fromObject(Object object, String selector) {
		JSONObject json = new JSONObject();
		String cut = " ";
		try {
			if (selector.startsWith("{") && selector.endsWith("}")) {
				cut = selector.substring(1, 2);
				if (cut.matches("[a-zA-Z]|[}]|[{]|[.]|[*]|[!]")) {
					// The separator cannot be a specific symbol
					throw new Exception("wrong cut char : " + cut);
				}
				selector = selector.substring(2, selector.length() - 1);
			}
			String[] strs = selector.split(cut);
			Map<String, String> parms = new HashMap<String, String>();
			for (String str : strs) {
				if (str == null) {
					continue;
				} else if (str.equals("*")) {
					json = JSONObject.fromObject(object);
					continue;
				} else if (str.indexOf(".") < 0) {
					if (str.startsWith("!")) {
						String key = str.substring(1);
						if (json.get(key) == null) {
							continue;
						}
						json.remove(key);
					} else {
						Object obj = ModelUtil.getValue(object, str);
						json = putObject(json, str, obj);
					}
				} else {
					int i = str.indexOf(".");
					String key = str.substring(0, i);
					String value = str.substring(i + 1);
					if (parms.get(key) != null) {
						value = parms.get(key) + " " + value;
					}
					parms.put(key, value);
				}
			}
			// Cycle to next level
			for (String key : parms.keySet()) {
				json.put(key, fromObject(ModelUtil.getValue(object, key), parms.get(key)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Get a JSONArray of a collection based on parameters
	 * 
	 * @param list
	 *            List
	 * @param selector
	 *    This is a custom selector with the following selection criteria.
	 * 	  "all" means all parameters of the current class, in other cases they are passed according to the current parameters, all parameters need to have a corresponding get function The parameters are separated by spaces, e.g.
	 * 	  "name password" means that the value of the current class getName and getPassword is taken.
	 * 	  and the values are stored in the JSON file under the keys "name" and "password", in addition if the parameters are somewhat separated, it means that the current parameter is a class
	 * 	  For example, "user.name" means that the return value of the getName method is taken from the return value of the getUser method (User class object) For example
	 * 	  "params user.name user.test.param1 user.test.param2"
	 * 	   When the hierarchy "." For convenience, when there are many levels of ".", we also provide a quick way to add a "parameter" to the . followed by a uniform "{" and "}" for the parameters.
	 * 	   and the first character inside the brackets is the separator e.g. "{,params,user.name,user.test.param1,user.test.param2}"
	 * 	   but the separator must not be "." , "{", "}", "*" and 26 letters, and it can't be a parent directory, otherwise it will report an error
	 * 	   The multi-level JSON match above can be simply written as
	 *            "{ params user.{,name,test.{:param1:param2}}}"
	 * @return JSON profile
	 */
	public static JSONArray fromList(List<?> list, String selector) {
		JSONArray array = new JSONArray();
		if (list != null) {
			for (Object object : list) {
				array.add(JSONUtil.fromObject(object, selector));
			}
		}
		return array;
	}

	public interface DoInItem<T> {
		void doing(T t, JSONObject item);
	}

	public static <T> JSONArray fromList(List<T> list, String selector, DoInItem<T> doInItem) {
		JSONArray array = new JSONArray();
		if (list != null) {
			for (T object : list) {
				JSONObject item = JSONUtil.fromObject(object, selector);
				doInItem.doing(object, item);
				array.add(item);
			}
		}
		return array;
	}
	
	public static JSONObject fromXml(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer(); 
        JSON json = xmlSerializer.read(xml);
        return (JSONObject)json;
	}
	
//	public static void main(String args[]) {
//		System.out.println(fromXml("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str><sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></xml>"));
//	}

}
