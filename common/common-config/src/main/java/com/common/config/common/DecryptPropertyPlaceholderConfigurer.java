package com.common.config.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PathMatcher;
import org.springframework.util.PropertiesPersister;

/**
 * 资源文件读取，区分window/linux
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 资源文件表达式
     */
    private String filePattern;

    /**
     * 文件编码
     */
    private String fileEncoding;

    /**
     * Linux的路径
     */
    private String linuxSystePath;

    /**
     * windows的路径
     */
    private String windowsSystemPath;

    /**
     * 通用配置文件路径
     */
    private String commonSystePath;

    /**
     * 加载属性文件
     */
    public void loadProperties(Properties props) throws IOException {

        if (StringUtils.isNotEmpty(filePattern)) {
            PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
            InputStream is = null;

            boolean flag = false;

            // 判断是否是Linux,否则取window路径
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {

                File configDir = new File(windowsSystemPath);
                if (StringUtils.isNotEmpty(windowsSystemPath) && configDir.exists()) {

                    logger.info("[loadProperties][path=" + windowsSystemPath + "]load win path config file...");

                    // 判断是否匹配通配符*
                    PathMatcher matcher = new AntPathMatcher();

                    // 文件列表
                    File[] files = configDir.listFiles();
                    for (File file : files) {
                        if (file.isDirectory()) {
                            continue;
                        }

                        if (matcher.match(filePattern, file.getName())) {

                            logger.info("[loadProperties]" + file.getName());

                            is = new FileInputStream(file);
                            loadProperties(is, props, propertiesPersister);

                        }

                    }

                    // 加载通用配置文件
                    loadCommon(is, props, propertiesPersister);

                } else {
                    flag = true;
                }

            } else {

                File configDir = new File(linuxSystePath);
                if (StringUtils.isNotEmpty(linuxSystePath) && configDir.exists()) {

                    logger.info("[loadProperties][path=" + linuxSystePath + "]load linux path config file...");

                    // 判断是否匹配通配符*
                    PathMatcher matcher = new AntPathMatcher();

                    // 文件列表
                    File[] files = configDir.listFiles();
                    for (File file : files) {
                        if (file.isDirectory()) {
                            continue;
                        }
                        if (matcher.match(filePattern, file.getName())) {

                            logger.info("[loadProperties]" + file.getName());

                            is = new FileInputStream(file);
                            loadProperties(is, props, propertiesPersister);

                        }

                    }

                    // 加载通用配置文件
                    loadCommon(is, props, propertiesPersister);

                } else {
                    flag = true;
                }

            }

            // 没有指定配置文件时，读取当前classes路径配置
            if (flag) {

                logger.info("[loadProperties][classes]load classes config file...");

                // 加载表达式中的属性文件
                ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
                try {
                    Resource[] metaInfResources = resourcePatternResolver.getResources("classpath*:" + filePattern);
                    for (Resource resource : metaInfResources) {

                        is = resource.getInputStream();
                        loadProperties(is, props, propertiesPersister);

                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }

            }

            // 加载数据库文件
            String datasourceType = props.getProperty("datasource.type");
            if (StringUtils.isNotEmpty(datasourceType)) {
                try {
                    // 加载属性文件
                    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
                    Resource[] metaInfResources = resourcePatternResolver
                            .getResources("classpath*:" + "datasource-" + datasourceType.trim() + ".properties");
                    for (Resource resource : metaInfResources) {

                        is = resource.getInputStream();
                        loadProperties(is, props, propertiesPersister);

                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public void setLinuxSystePath(String linuxSystePath) {
        this.linuxSystePath = linuxSystePath;
    }

    public void setWindowsSystemPath(String windowsSystemPath) {
        this.windowsSystemPath = windowsSystemPath;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public void setCommonSystePath(String commonSystePath) {
        this.commonSystePath = commonSystePath;
    }

    /**
     * 加载属性文件
     * 
     * @param is
     * @param props
     * @param propertiesPersister
     * @throws Exception
     */
    private void loadProperties(InputStream is, Properties props, PropertiesPersister propertiesPersister)
            throws IOException {
        try {

            // 读取属性文件信息
            if (StringUtils.isNotEmpty(fileEncoding)) {
                propertiesPersister.load(props, new InputStreamReader(is, fileEncoding));
            } else {
                propertiesPersister.load(props, is);
            }

        } catch (Exception e) {

            logger.error(e.getMessage(), e);
            throw new IOException();

        } finally {

            // 关闭流
            if (is != null) {
                is.close();
            }

        }
    }

    /**
     * 加载通用配置文件
     * 
     * @param is
     * @param props
     * @param propertiesPersister
     * @throws IOException
     */
    private void loadCommon(InputStream is, Properties props, PropertiesPersister propertiesPersister)
            throws IOException {
        File configDir = new File(commonSystePath);
        if (StringUtils.isNotEmpty(commonSystePath) && configDir.exists()) {

            logger.info("[loadProperties][path=" + commonSystePath + "]load common config file...");

            // 判断是否匹配通配符*
            PathMatcher matcher = new AntPathMatcher();

            // 文件列表
            File[] files = configDir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    continue;
                }
                if (matcher.match(filePattern, file.getName())) {

                    logger.info("[loadProperties]" + file.getName());

                    is = new FileInputStream(file);
                    loadProperties(is, props, propertiesPersister);

                }

            }

        }
    }

}
