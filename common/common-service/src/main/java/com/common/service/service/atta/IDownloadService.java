package com.common.service.service.atta;

import java.util.List;

import com.common.base.constant.BaseConstant;
import com.common.base.model.atta.BaseAttaModel;

/**
 * 文件下载Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IDownloadService extends BaseConstant {

	/**
	 * 文件下载
	 * 
	 * @param sourceType
	 * @return
	 * @throws Exception
	 */
	public List<BaseAttaModel> download(String sourceId, String sourceType, String url) throws Exception;

}
