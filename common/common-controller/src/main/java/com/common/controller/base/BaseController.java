package com.common.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.base.common.BaseLogger;
import com.common.base.constant.BaseConstant;
import com.common.controller.common.StringEditor;
import com.common.util.ExceptionUtil;
import com.common.util.MapUtil;

/**
 * 公共Controller，提供一些公共功能
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
@Controller
@RequestMapping
public class BaseController extends BaseLogger implements BaseConstant {

	/**
	 * 数据类型转换
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringEditor());

		// 日期类处理
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}

	/**
	 * URL页面跳转
	 * 
	 * @param redirect
	 * @param mode
	 * @param request
	 * @return
	 */
	@RequestMapping("/redirect")
	public String redirect(String redirect, Model mode, HttpServletRequest request) {
		try {
			Map<String, String> map = MapUtil.getInstance().mapTransfer(request.getParameterMap());
			Set<String> set = map.keySet();
			if (!set.isEmpty()) {
				Iterator<String> iter = set.iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					mode.addAttribute(key, map.get(key));
				}
			}
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return redirect;
	}

}