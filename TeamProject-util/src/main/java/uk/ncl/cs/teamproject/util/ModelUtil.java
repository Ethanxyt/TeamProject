package uk.ncl.cs.teamproject.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yantao xu
 */
public class ModelUtil {
		
	/**
	 * Dynamic set methods
	 * @param object Object of the class executing the method
	 * @param propertyName Name of the variable to be set
	 * @param value Specific variable values
	 */
	@SuppressWarnings("unchecked")
	public static void setValue(Object object, String propertyName, String value) {
		String firstLetter = propertyName.substring(0, 1).toUpperCase();
        String fieldName = propertyName;
        String setMethodName = "set" + firstLetter + propertyName.substring(1);
 
        try {  
        	Class<?> c = (Class<?>) object.getClass();
            Field f = c.getDeclaredField(fieldName);
            String fieldTypeName = f.getType().getName();
            if (fieldTypeName.equals("java.lang.Integer")) {
        		Class<?> paramsType[] = {Integer.class};
        		Object paramsValue[] = {new Integer(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue);  
            } else if (fieldTypeName.equals("java.lang.String")) {
            	Class<?> paramsType[] = {String.class};
        		Object paramsValue[] = {new String(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue); 
            } else if (fieldTypeName.equals("java.lang.Long")) {
            	Class<?> paramsType[] = {Long.class};
        		Object paramsValue[] = {new Long(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue); 
            } else if (fieldTypeName.equals("java.lang.Float")) {
            	Class<?> paramsType[] = {Float.class};
        		try {
	        		Object paramsValue[] = {new Float(value)};
	        		Method method = c.getMethod(setMethodName, paramsType);    
	                method.invoke(object, paramsValue);
    			} catch (NumberFormatException ex) {
    				System.out.println("Data floating point format conversion exception： " + value);
    				ex.printStackTrace();
    			}
            } else if (fieldTypeName.equals("java.lang.Boolean")) {
            	Class<?> paramsType[] = {Boolean.class};
        		Object paramsValue[] = {new Boolean(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue); 
            } else {
//            	if (fieldTypeName.equals("java.util.Date")) {
//					Class<?> paramsType[] = {java.util.Date.class};
//					Object paramsValue[] = {new SimpleDateFormat(Constants.DATETIME_FORMAT).parse(value)};
//					Method method = c.getMethod(setMethodName, paramsType);
//					method.invoke(object, paramsValue);
//				}
				System.out.println("\nModelUtil.setValue: Value Data type not found：" + propertyName + "/" + fieldTypeName +".\n");
            }
        } catch (NoSuchFieldException e) {  
			System.out.println("\nModelUtil.setValue：Properties not found" + fieldName +".\n");
			e.printStackTrace();
        } catch (NoSuchMethodException e) {  
        	System.out.println("\nModelUtil.setValue：Can't find method" + setMethodName +".\n");
			e.printStackTrace();
        } catch (IllegalAccessException e) {  
        	System.out.println("\nModelUtil.setValue：Incorrect parameters" + ".\n");
			e.printStackTrace();
        } catch (InvocationTargetException e) {  
        	System.out.println("\nModelUtil.setValue：Wrong target for implementation" + ".\n");
			e.printStackTrace();
        }
	}
	
	/**
	 * Dynamic get methods
	 * @param object Object of the class whose methods need to be executed
	 * @param name Name of the variable
	 * @return Variable values for get
	 */
	public static Object getValue(Object object, String name){	
		if (name == null || name.equals("") || object == null) {
			return null;
		}
		String str = "get" + name.replaceFirst(name.substring(0, 1),name.substring(0, 1).toUpperCase()) ;
		Method method;
		try {
			//TODO:
			method = object.getClass().getMethod(str);
			Object obj = method.invoke(object);
			return obj;
		} catch (NoSuchMethodException e) {
        	System.out.println("\nModelUtil.getValue：Can't find a method" + str +".\n");
			e.printStackTrace();
		} catch (IllegalAccessException e) {  
        	System.out.println("\nModelUtil.getValue：Incorrect parameters" + ".\n");
			e.printStackTrace();
        } catch (InvocationTargetException e) {  
        	System.out.println("\nModelUtil.getValue：Wrong target for implementation" + ".\n");
			e.printStackTrace();
        }  
		return null;
	}
	
	/**
	 * Special types of dynamic set
	 * @param object The object on which the set method is executed
	 * @param propertyName
	 * @param value
	 * @param propertyClazz
	 */
	public static void setValue(Object object, String propertyName, Object value, Class<?> propertyClazz) {
		String firstLetter = propertyName.substring(0, 1).toUpperCase();
        String setMethodName = "set" + firstLetter + propertyName.substring(1);        
        try {
        	@SuppressWarnings("unchecked")
			Class<?> c = (Class<?>) object.getClass();
			Class<?> paramsType[] = {propertyClazz};
    		Method method = c.getMethod(setMethodName, paramsType);    
            method.invoke(object, value);  
		} catch (NoSuchMethodException e) {  
        	System.out.println("\nModelUtil.setValue：can't find a method" + setMethodName +".\n");
			e.printStackTrace();
        } catch (IllegalAccessException e) {  
        	System.out.println("\nModelUtil.setValue：Incorrect parameters" + ".\n");
			e.printStackTrace();
        } catch (InvocationTargetException e) {  
        	System.out.println("\nModelUtil.setValue：Wrong target for implementation" + ".\n");
			e.printStackTrace();
        } 
        
	}

}