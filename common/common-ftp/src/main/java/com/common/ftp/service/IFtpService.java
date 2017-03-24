package com.common.ftp.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * FTP服务
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IFtpService {

    /**
     * 断开ftp连接
     * 
     * @throws Exception
     */
    public void disconnect() throws Exception ;

    public boolean mkdir(String pathname) throws Exception ;

    /**
     * 在ftp服务器端创建目录（不支持一次创建多级目录）
     * 
     * 该方法执行完后将自动关闭当前连接
     * 
     * @param pathname
     * @return
     * @throws Exception
     */
    public boolean mkdir(String pathname, String workingDirectory) throws Exception ;

    /**
     * 在ftp服务器端创建目录（不支持一次创建多级目录）
     * 
     * @param pathname
     * @param autoClose
     *            是否自动关闭当前连接
     * @return
     * @throws Exception
     */
    public boolean mkdir(String pathname, String workingDirectory, boolean autoClose) throws Exception;

    /**
     * 上传一个本地文件到远程指定文件
     * 
     * @param remoteAbsoluteFile
     *            远程文件名(包括完整路径)
     * @param localAbsoluteFile
     *            本地文件名(包括完整路径)
     * @return 成功时，返回true，失败返回false
     * @throws Exception
     */
    public boolean upload(String remoteAbsoluteFile, String localAbsoluteFile) throws Exception ;

    /**
     * 上传一个本地文件到远程指定文件
     * 
     * @param remoteAbsoluteFile
     *            远程文件名(包括完整路径)
     * @param localAbsoluteFile
     *            本地文件名(包括完整路径)
     * @param autoClose
     *            是否自动关闭当前连接
     * @return 成功时，返回true，失败返回false
     * @throws Exception
     */
    public boolean upload(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose) throws Exception;

    public boolean upload(InputStream inputStream, String remoteAbsoluteFile, boolean autoClose) throws Exception;

    /**
     * 下载一个远程文件到本地的指定文件
     * 
     * @param remoteAbsoluteFile
     *            远程文件名(包括完整路径)
     * @param localAbsoluteFile
     *            本地文件名(包括完整路径)
     * @return 成功时，返回true，失败返回false
     * @throws Exception
     */
    public boolean download(String remoteAbsoluteFile, String localAbsoluteFile) throws Exception ;

    /**
     * 下载一个远程文件到本地的指定文件
     * 
     * @param remoteAbsoluteFile
     *            远程文件名(包括完整路径)
     * @param localAbsoluteFile
     *            本地文件名(包括完整路径)
     * @param autoClose
     *            是否自动关闭当前连接
     * 
     * @return 成功时，返回true，失败返回false
     * @throws Exception
     */
    public boolean download(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose) throws Exception ;

    /**
     * 下载一个远程文件到指定的流 处理完后记得关闭流
     * 
     * @param remoteAbsoluteFile
     * @param output
     * @return
     * @throws Exception
     */
    public boolean download(String remoteAbsoluteFile, OutputStream output) throws Exception ;

    /**
     * 下载一个远程文件到指定的流 处理完后记得关闭流
     * 
     * @param remoteAbsoluteFile
     * @param output
     * @param delFile
     * @return
     * @throws Exception
     */
    public boolean download(String remoteAbsoluteFile, OutputStream output, boolean autoClose) throws Exception ;

    public InputStream download(String remoteAbsoluteFile, boolean autoClose) throws Exception ;

    /**
     * 从ftp服务器上删除一个文件 该方法将自动关闭当前连接
     * 
     * @param delFile
     * @return
     * @throws Exception
     */
    public boolean delete(String delFile) throws Exception ;

    /**
     * 从ftp服务器上删除一个文件
     * 
     * @param delFile
     * @param autoClose
     *            是否自动关闭当前连接
     * 
     * @return
     * @throws Exception
     */
    public boolean delete(String delFile, boolean autoClose) throws Exception ;

    /**
     * 批量删除 该方法将自动关闭当前连接
     * 
     * @param delFiles
     * @return
     * @throws Exception
     */
    public boolean delete(String[] delFiles) throws Exception;

    /**
     * 批量删除
     * 
     * @param delFiles
     * @param autoClose
     *            是否自动关闭当前连接
     * 
     * @return
     * @throws Exception
     */
    public boolean delete(String[] delFiles, boolean autoClose) throws Exception ;

    /**
     * 列出远程默认目录下所有的文件
     * 
     * @return 远程默认目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组
     * @throws Exception
     */
    public String[] listNames() throws Exception ;

    public String[] listNames(boolean autoClose) throws Exception ;

    /**
     * 列出远程目录下所有的文件
     * 
     * @param remotePath
     *            远程目录名
     * @param autoClose
     *            是否自动关闭当前连接
     * 
     * @return 远程目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组
     * @throws Exception
     */
    public String[] listNames(String remotePath, boolean autoClose) throws Exception ;

    public void mkdirs(String dir, boolean autoClose) throws Exception;
    
}
