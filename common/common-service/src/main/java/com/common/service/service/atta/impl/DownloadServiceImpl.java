package com.common.service.service.atta.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.base.model.atta.BaseAttaModel;
import com.common.service.service.atta.IDownloadService;
import com.common.util.DateUtil;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;

/**
 * 文件下载Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class DownloadServiceImpl extends BaseLogger implements IDownloadService {

    @Value("${ftp.download_path}")
    private String downloadPath;

    private static SimpleDateFormat formatFolder = new SimpleDateFormat("yyyyMMdd");

    private static SimpleDateFormat formatFile = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    /**
     * 文件下载
     * 
     * @param url
     * @param sourceType
     * @return
     * @throws Exception
     */
    public List<BaseAttaModel> download(String sourceId, String sourceType, String url) throws Exception {
        List<BaseAttaModel> baseAttaModels = new ArrayList<>();
        try {

            // 读取文件信息
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

            // 获取文件后缀
            String suffix = StringUtils.substringAfterLast(url, ".");

            if (StringUtils.isEmpty(suffix)) {
                suffix = "jpg";
            }

            // 文件路径
            Date date = DateUtil.getInstance().getCurrDate();
            String fileName = formatFile.format(date) + "." + suffix;
            // 数据库保存路径
            String tmpPath = FILE_SEPARATOR + sourceType + FILE_SEPARATOR + formatFolder.format(date);
            // 完整路径
            String fullImagePath = downloadPath + FILE_SEPARATOR + tmpPath;

            // 文件文件路径
            File file = new File(fullImagePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 生成文件文件
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            File outFile = new File(fullImagePath + FILE_SEPARATOR + fileName);
            try {
                // 获取文件数据流
                inputStream = connection.getInputStream();
                fileOutputStream = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                    fileOutputStream.flush();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }

            // 保存下载记录信息
            BaseAttaModel baseAttaModel = new BaseAttaModel();
            baseAttaModel.setId(PrimaryUtil.getInstance().getPrimaryValue());
            baseAttaModel.setFileExtend(suffix);
            baseAttaModel.setFileName(fileName);
            baseAttaModel.setFileSize(outFile.length());
            baseAttaModel.setSourceId(sourceId);
            baseAttaModel.setSourceType(sourceType);
            baseAttaModel.setFilePath(tmpPath + FILE_SEPARATOR + fileName);

            baseAttaModels.add(baseAttaModel);

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return baseAttaModels;
    }

}
