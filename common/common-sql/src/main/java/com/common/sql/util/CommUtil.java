package com.common.sql.util;

import java.util.Collection;
import java.util.Map;

/**
 * 工具类
 * 
 * @author 枫之叶
 * 
 */
public abstract class CommUtil {
	/**
	 * Map对象是否为空
	 * 
	 * @param <T>
	 * @param map
	 * @return
	 */
	public static <T> boolean isNotEmpty(Map<String, T> map) {
		boolean flag = false;
		try {
			if (map != null && map.size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Collection对象是否为空
	 * 
	 * @param <T>
	 * @param collection
	 * @return
	 */
	public static <T> boolean isNotEmpty(Collection<T> collection) {
		boolean flag = false;
		try {
			if (collection != null && collection.size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 数组是否为空
	 * 
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] array) {
		boolean flag = false;
		try {
			if (array != null && array.length > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 对象是否为空
	 * 
	 * @param <T>
	 * @param obj
	 * @return
	 */
	public static <T> boolean isNotEmpty(T obj) {
		boolean flag = false;
		try {
			if (obj != null) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 字符串对象是否为空
	 * 
	 * @param <T>
	 * @param obj
	 * @return
	 */
	public static <T> boolean isNotEmpty(String str) {
		boolean flag = false;
		try {
			if (str != null && !"".equals(str.toString().trim())
					&& str.toString().trim().length() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
