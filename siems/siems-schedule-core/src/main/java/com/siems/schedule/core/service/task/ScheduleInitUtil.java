package com.siems.schedule.core.service.task;
//package com.siems.schedule.core.service.task;
//
//import java.io.File;
//import java.io.FileReader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Properties;
//import java.util.Set;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//
//import com.siems.schedule.core.service.util.ScheduleUtil;
//import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
//
///**
// * 初始化调度信息
// * 
// * @author: HuiJunLuo
// * @date:2016年4月18日
// * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
// */
//@Lazy(false)
//@Service
//public class ScheduleInitUtil implements InitializingBean, ApplicationContextAware {
//
//    private static Logger logger = Logger.getLogger(ScheduleInitUtil.class);
//
//    public final static String configFile;
//
//    private ApplicationContext applicationContext;
//
//    private static TBScheduleManagerFactory tbscheduleManagerFactory;
//
//    static {
//        configFile = ScheduleUtil.getInstance().getScheduleConfig();
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Properties p = new Properties();
//        FileReader reader = new FileReader(new File(configFile));
//        p.load(reader);
//        reader.close();
//        tbscheduleManagerFactory = new TBScheduleManagerFactory();
//        tbscheduleManagerFactory.setApplicationContext(applicationContext);
//        tbscheduleManagerFactory.init(p);
//
//        Map<String, String> map = new HashMap<>();
//        Set<Entry<Object, Object>> set = p.entrySet();
//        for (Entry<Object, Object> entry : set) {
//            map.put(entry.getKey().toString(), entry.getValue().toString());
//        }
//
//        tbscheduleManagerFactory.setZkConfig(map);
//        logger.warn("TBBPM 成功启动schedule调度引擎 ...");
//    }
//
//}
