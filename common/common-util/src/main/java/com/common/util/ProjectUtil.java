
/**     
 * @FileName: ProjectUtil.java   
 * @Package:com.ceacsz.pms.common   
 * @Description: 工程工具类  
 * @author: ZhiYong.Li    
 * @date:2014年10月10日   
 */

package com.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.common.base.common.BaseLogger;

/**
 * 项目信息工具类
 * 
 * @author: HuiJunLuo
 * @date:2016年2月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProjectUtil extends BaseLogger {

    private static ProjectUtil projectUtil = null;

    public static final String jsVerison = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

    public synchronized static ProjectUtil getInstance() {
        if (projectUtil == null) {
            projectUtil = new ProjectUtil();
        }
        return projectUtil;
    }

    private ProjectUtil() {
    }

    /**
     * 获取JS版本号
     * 
     * @param request
     * @return
     */
    public String getJsVersion(HttpServletRequest request) {
        String versionNo = ProjectUtil.jsVerison;
        logger.info("[ProjectUtil][version=" + versionNo + "]");
        return versionNo;
    }

}
