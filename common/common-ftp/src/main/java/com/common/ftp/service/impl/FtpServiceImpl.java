package com.common.ftp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.common.base.common.BaseLogger;
import com.common.ftp.service.IFtpService;

/**
 * FTP服务
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class FtpServiceImpl extends BaseLogger implements IFtpService {

    // ---------------------------------------------------------------------
    // Instance data
    // ---------------------------------------------------------------------
    private ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<FTPClient>();

    private String host;

    private int port;

    private String username;

    private String password;

    private boolean binaryTransfer = true;

    private boolean passiveMode = true;

    private String encoding = "UTF-8";

    private int clientTimeout = 1000 * 30;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBinaryTransfer() {
        return binaryTransfer;
    }

    public void setBinaryTransfer(boolean binaryTransfer) {
        this.binaryTransfer = binaryTransfer;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getClientTimeout() {
        return clientTimeout;
    }

    public void setClientTimeout(int clientTimeout) {
        this.clientTimeout = clientTimeout;
    }

    // ---------------------------------------------------------------------
    // private method
    // ---------------------------------------------------------------------
    /**
     * 返回一个FTPClient实例
     * 
     * @throws Exception
     */
    private FTPClient getFTPClient() throws Exception {
        if (ftpClientThreadLocal.get() != null && ftpClientThreadLocal.get().isConnected()) {
            return ftpClientThreadLocal.get();
        } else {
            FTPClient ftpClient = new FTPClient(); // 构造一个FtpClient实例
            ftpClient.setControlEncoding(encoding); // 设置字符集

            connect(ftpClient); // 连接到ftp服务器

            // 设置为passive模式
            if (passiveMode) {
                ftpClient.enterLocalPassiveMode();
            }
            setFileType(ftpClient); // 设置文件传输类型

            try {
                ftpClient.setSoTimeout(clientTimeout);
            } catch (SocketException e) {
                throw new Exception("Set timeout error.", e);
            }
            ftpClientThreadLocal.set(ftpClient);
            return ftpClient;
        }
    }

    /**
     * 设置文件传输类型
     * 
     * @throws Exception
     * @throws IOException
     */
    private void setFileType(FTPClient ftpClient) throws Exception {
        try {
            if (binaryTransfer) {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            } else {
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
            }
        } catch (IOException e) {
            throw new Exception("Could not to set file type.", e);
        }
    }

    /**
     * 连接到ftp服务器
     * 
     * @param ftpClient
     * @return 连接成功返回true，否则返回false
     * @throws Exception
     */
    private boolean connect(FTPClient ftpClient) throws Exception {
        try {

            ftpClient.connect(host, port);

            // 连接后检测返回码来校验连接是否成功
            int reply = ftpClient.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                // 登陆到ftp服务器
                if (ftpClient.login(username, password)) {
                    setFileType(ftpClient);
                    return true;
                }
            } else {
                ftpClient.disconnect();
                throw new Exception("FTP server refused connection.");
            }
        } catch (IOException e) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect(); // 断开连接
                } catch (IOException e1) {
                    throw new Exception("Could not disconnect from server.", e1);
                }

            }
            throw new Exception("Could not connect to server.", e);
        }
        return false;
    }

    // ---------------------------------------------------------------------
    // public method
    // ---------------------------------------------------------------------
    /**
     * 断开ftp连接
     * 
     * @throws Exception
     */
    public void disconnect() throws Exception {
        try {
            FTPClient ftpClient = getFTPClient();
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
                ftpClient = null;
            }
        } catch (IOException e) {
            throw new Exception("Could not disconnect from server.", e);
        }
    }

    public boolean mkdir(String pathname) throws Exception {
        return mkdir(pathname, null);
    }

    /**
     * 在ftp服务器端创建目录（不支持一次创建多级目录）
     * 
     * 该方法执行完后将自动关闭当前连接
     * 
     * @param pathname
     * @return
     * @throws Exception
     */
    public boolean mkdir(String pathname, String workingDirectory) throws Exception {
        return mkdir(pathname, workingDirectory, true);
    }

    /**
     * 在ftp服务器端创建目录（不支持一次创建多级目录）
     * 
     * @param pathname
     * @param autoClose
     *            是否自动关闭当前连接
     * @return
     * @throws Exception
     */
    public boolean mkdir(String pathname, String workingDirectory, boolean autoClose) throws Exception {
        try {
            getFTPClient().changeWorkingDirectory(workingDirectory);
            return getFTPClient().makeDirectory(pathname);
        } catch (IOException e) {
            throw new Exception("Could not mkdir.", e);
        } finally {
            if (autoClose) {
                disconnect(); // 断开连接
            }
        }
    }

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
    public boolean upload(String remoteAbsoluteFile, String localAbsoluteFile) throws Exception {
        return upload(remoteAbsoluteFile, localAbsoluteFile, true);
    }

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
    public boolean upload(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose) throws Exception {
        InputStream input = null;
        try {
            // 处理传输
            input = new FileInputStream(localAbsoluteFile);
            getFTPClient().storeFile(remoteAbsoluteFile, input);
            logger.info("upload " + localAbsoluteFile);
            return true;
        } catch (FileNotFoundException e) {
            throw new Exception("local file not found.", e);
        } catch (IOException e) {
            throw new Exception("Could not put file to server.", e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                throw new Exception("Couldn't close FileInputStream.", e);
            }
            if (autoClose) {
                disconnect(); // 断开连接
            }
        }
    }

    public boolean upload(InputStream inputStream, String remoteAbsoluteFile, boolean autoClose) throws Exception {
        try {
            // 处理传输
            getFTPClient().storeFile(remoteAbsoluteFile, inputStream);
            logger.info("upload " + remoteAbsoluteFile);
            return true;
        } catch (FileNotFoundException e) {
            throw new Exception("local file not found.", e);
        } catch (IOException e) {
            throw new Exception("Could not put file to server.", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                throw new Exception("Couldn't close FileInputStream.", e);
            }
            if (autoClose) {
                disconnect(); // 断开连接
            }
        }
    }

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
    public boolean download(String remoteAbsoluteFile, String localAbsoluteFile) throws Exception {
        return download(remoteAbsoluteFile, localAbsoluteFile, true);
    }

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
    public boolean download(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose) throws Exception {
        OutputStream output = null;
        try {

            if (StringUtils.isNotEmpty(localAbsoluteFile)) {
                localAbsoluteFile = localAbsoluteFile.trim();
                File file = new File(localAbsoluteFile.substring(0, localAbsoluteFile.lastIndexOf("/")));
                if (!file.exists()) {
                    file.mkdirs();
                }
            }

            output = new FileOutputStream(localAbsoluteFile);
            return download(remoteAbsoluteFile, output, autoClose);
        } catch (FileNotFoundException e) {
            throw new Exception("local file not found.", e);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new Exception("Couldn't close FileOutputStream.", e);
            }
        }
    }

    /**
     * 下载一个远程文件到指定的流 处理完后记得关闭流
     * 
     * @param remoteAbsoluteFile
     * @param output
     * @return
     * @throws Exception
     */
    public boolean download(String remoteAbsoluteFile, OutputStream output) throws Exception {
        return download(remoteAbsoluteFile, output, true);
    }

    /**
     * 下载一个远程文件到指定的流 处理完后记得关闭流
     * 
     * @param remoteAbsoluteFile
     * @param output
     * @param delFile
     * @return
     * @throws Exception
     */
    public boolean download(String remoteAbsoluteFile, OutputStream output, boolean autoClose) throws Exception {
        try {
            FTPClient ftpClient = getFTPClient();
            // 处理传输
            return ftpClient.retrieveFile(remoteAbsoluteFile, output);
        } catch (IOException e) {
            throw new Exception("Couldn't get file from server.", e);
        } finally {
            if (autoClose) {
                disconnect(); // 关闭链接
            }
        }
    }

    public InputStream download(String remoteAbsoluteFile, boolean autoClose) throws Exception {
        try {
            FTPClient ftpClient = getFTPClient();
            // 处理传输
            return ftpClient.retrieveFileStream(remoteAbsoluteFile);
        } catch (IOException e) {
            throw new Exception("Couldn't get file from server.", e);
        } finally {
            if (autoClose) {
                disconnect(); // 关闭链接
            }
        }
    }

    /**
     * 从ftp服务器上删除一个文件 该方法将自动关闭当前连接
     * 
     * @param delFile
     * @return
     * @throws Exception
     */
    public boolean delete(String delFile) throws Exception {
        return delete(delFile, true);
    }

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
    public boolean delete(String delFile, boolean autoClose) throws Exception {
        try {
            getFTPClient().deleteFile(delFile);
            return true;
        } catch (IOException e) {
            throw new Exception("Couldn't delete file from server.", e);
        } finally {
            if (autoClose) {
                disconnect(); // 关闭链接
            }
        }
    }

    /**
     * 批量删除 该方法将自动关闭当前连接
     * 
     * @param delFiles
     * @return
     * @throws Exception
     */
    public boolean delete(String[] delFiles) throws Exception {
        return delete(delFiles, true);
    }

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
    public boolean delete(String[] delFiles, boolean autoClose) throws Exception {
        try {
            FTPClient ftpClient = getFTPClient();
            for (String s : delFiles) {
                ftpClient.deleteFile(s);
            }
            return true;
        } catch (IOException e) {
            throw new Exception("Couldn't delete file from server.", e);
        } finally {
            if (autoClose) {
                disconnect(); // 关闭链接
            }
        }
    }

    /**
     * 列出远程默认目录下所有的文件
     * 
     * @return 远程默认目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组
     * @throws Exception
     */
    public String[] listNames() throws Exception {
        return listNames(null, true);
    }

    public String[] listNames(boolean autoClose) throws Exception {
        return listNames(null, autoClose);
    }

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
    public String[] listNames(String remotePath, boolean autoClose) throws Exception {
        try {
            String[] listNames = getFTPClient().listNames(remotePath);
            return listNames;
        } catch (IOException e) {
            throw new Exception("列出远程目录下所有的文件时出现异常", e);
        } finally {
            if (autoClose) {
                disconnect(); // 关闭链接
            }
        }
    }

    public void mkdirs(String dir, boolean autoClose) throws Exception {
        try {
            if (StringUtils.isEmpty(dir)) {
                return;
            }
            FTPClient client = getFTPClient();
            dir = dir.replace("//", "/");
            String[] dirs = dir.split("/");
            for (int i = 0; i < dirs.length; i++) {
                dir = dirs[i];
                if (!StringUtils.isEmpty(dir)) {
                    client.mkd(dir);
                    client.changeWorkingDirectory(dir);
                }
            }
        } catch (IOException e) {
            throw new Exception("创建文件夹时出现异常", e);
        } finally {
            if (autoClose) {
                disconnect(); // 关闭链接
            }
        }
    }

    public static void main(String[] args) throws Exception, InterruptedException {

        FtpServiceImpl ftp = new FtpServiceImpl();
        ftp.setHost("192.168.0.160");
        ftp.setPort(21);
        ftp.setUsername("ttjob_ftp");
        ftp.setPassword("8Td9SDfPkwRsg");
        ftp.setBinaryTransfer(false);
        ftp.setPassiveMode(false);
        ftp.setEncoding("utf-8");

        boolean ret = ftp.upload("超时.txt", "/home/huijunluo/Desktop/headhunterlog.log");
        System.out.println(ret);

        ftp.disconnect();
        ftp.mkdir("user-upload1");
        ftp.disconnect();

        String[] aa = { "超时.txt", "c.t" };
        ftp.delete(aa);

        ftp.mkdir("/a/b/c");

        ftp.mkdirs("/a/b", true);

        String[] str = ftp.listNames();
        for (String string : str) {
            System.out.println(string);
        }

    }
}
