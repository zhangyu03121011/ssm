package com.common.service.service.atta.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.common.base.common.BaseLogger;
import com.common.base.model.atta.BaseAttaModel;
import com.common.service.service.atta.IUploadService;
import com.common.util.DateUtil;
import com.common.util.ExceptionUtil;
import com.common.util.FileTypeUtil;
import com.common.util.PrimaryUtil;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * 文件上传Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UploadServiceImpl extends BaseLogger implements IUploadService {

	@Value("${ftp.upload_path}")
	private String uploadPath;

	private static SimpleDateFormat formatFolder = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat formatFile = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	/**
	 * 文件上传
	 * 
	 * @param sourceType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public List<BaseAttaModel> upload(String sourceId, String sourceType, String sourceName, HttpServletRequest request)
			throws Exception {
		List<BaseAttaModel> baseAttaModels = new ArrayList<>();
		try {

			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {

				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {

					// 记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();

					// 取得上传文件
					List<MultipartFile> files = multiRequest.getFiles(iter.next());
					if (CollectionUtils.isNotEmpty(files)) {

						for (MultipartFile file : files) {

							// 取得当前上传文件的文件名称
							String fileName = file.getOriginalFilename();
							// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
							if (!StringUtils.isEmpty(fileName)) {

								logger.info("upload fileName:" + fileName);

								// 获取文件时长
								long duration = 0;
								try {
									if (FileTypeUtil.getInstance().isAudioFileType(fileName)) {
										CommonsMultipartFile cf = (CommonsMultipartFile) file;
										DiskFileItem fi = (DiskFileItem) cf.getFileItem();
										File source = fi.getStoreLocation();
										Encoder encoder = new Encoder();
										MultimediaInfo m = encoder.getInfo(source);
										long ls = m.getDuration();
										duration = ls / 1000;
										logger.info("file duration:" + duration);
									}
								} catch (Exception e1) {
									logger.info("error file type:" + fileName);
								}

								// 定义上传路径
								Date date = DateUtil.getInstance().getCurrDate();

								// 获取文件后缀
								String suffix = StringUtils.substringAfterLast(fileName, ".");
								logger.info("file suffix:" + suffix);

								if (StringUtils.isEmpty(suffix)) {
									suffix = "jpg";
								}

								// 文件路径
								String newFileName = formatFile.format(date) + "." + suffix;
								// 数据库保存路径
								String tmpPath = FILE_SEPARATOR + sourceName + FILE_SEPARATOR
										+ formatFolder.format(date);
								// 完整路径
								String fullPath = uploadPath + FILE_SEPARATOR + tmpPath;

								// 文件路径
								logger.info("file dir:" + fullPath);
								File localFolder = new File(fullPath);
								if (!localFolder.exists()) {
									localFolder.mkdirs();
									logger.info("create dir:" + fullPath);
								}

								// 文件完整路径
								String filePath = fullPath + FILE_SEPARATOR + newFileName;
								logger.info("upload filePath:" + filePath);
								File localFile = new File(filePath);
								try {
									file.transferTo(localFile);
								} catch (IllegalStateException e) {
									logger.error(e.getMessage(), e);
								} catch (IOException e) {
									logger.error(e.getMessage(), e);
								}

								// 保存上传记录信息
								BaseAttaModel baseAttaModel = new BaseAttaModel();
								baseAttaModel.setId(PrimaryUtil.getInstance().getPrimaryValue());
								baseAttaModel.setFileExtend(suffix);
								baseAttaModel.setFileName(fileName);
								baseAttaModel.setFileSize(file.getSize());
								baseAttaModel.setFileDuration(duration);
								baseAttaModel.setSourceId(sourceId);
								baseAttaModel.setSourceType(sourceType);
								baseAttaModel.setFilePath(tmpPath + FILE_SEPARATOR + newFileName);
								baseAttaModels.add(baseAttaModel);
							}

						}
					}

					logger.info("upload success");
					// 记录上传该文件后的时间
					int finaltime = (int) System.currentTimeMillis();
					logger.info("upload time:" + (finaltime - pre));
				}
			}

		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return baseAttaModels;
	}

}
