package com.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanUtils {

	public static void copyProperties(Object from ,Object to) {
		Class<? extends Object> class1 = from.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				Method getMethod = class1.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
				Object invoke = getMethod.invoke(from);
				if(invoke != null) {
					Class<? extends Object> class2 = to.getClass();
					Method setMethod = class2.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), field.getType());
					setMethod.invoke(to, invoke);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		
	}
}
