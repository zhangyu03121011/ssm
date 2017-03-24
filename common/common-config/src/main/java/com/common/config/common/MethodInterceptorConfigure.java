package com.common.config.common;

import java.util.List;

import org.springframework.util.PathMatcher;

/**
 * 自定义方法拦截器
 * 
 * @author: HuiJunLuo
 * @date:2016年5月18日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class MethodInterceptorConfigure {

    /**
     * 包含路径
     */
    private List<String> includePatterns;

    /**
     * 排除路径
     */
    private List<String> excludePatterns;

    /**
     * 路径匹配
     */
    private PathMatcher pathMatcher;

    public boolean matches(String lookupPath) {

        boolean excludeFlag = false;
        boolean includeFlag = false;
        boolean excludeLinkFlag = false;
        boolean includeLinkFlag = false;

        // 匹配排除路径
        if (this.excludePatterns != null) {
            for (String pattern : this.excludePatterns) {
                if (pathMatcher.match(pattern, lookupPath)) {
                    excludeFlag = true;

                    // 匹配相似度
                    if (pattern.equalsIgnoreCase(lookupPath) || pattern.concat("/").equalsIgnoreCase(lookupPath)) {
                        excludeLinkFlag = true;
                        break;
                    }

                }
            }
        }

        // 匹配包含路径
        if (this.includePatterns != null) {
            for (String pattern : this.includePatterns) {
                if (pathMatcher.match(pattern, lookupPath)) {
                    includeFlag = true;

                    // 匹配相似度
                    if (pattern.equalsIgnoreCase(lookupPath) || pattern.concat("/").equalsIgnoreCase(lookupPath)) {
                        includeLinkFlag = true;
                        break;
                    }

                }
            }
        }

        // 如果都匹配，则以匹配度高的优先
        if (includeFlag && excludeFlag) {
            if (includeLinkFlag) {
                return true;
            }
            if (excludeLinkFlag) {
                return false;
            }
        }
        // 都不匹配，则默认都要拦截
        if (includeFlag == false && excludeFlag == false) {
            return true;
        }
        // 排除匹配
        if (excludeFlag) {
            return false;
        }
        // 包含匹配
        if (includeFlag) {
            return true;
        }

        return true;
    }

    public List<String> getIncludePatterns() {
        return includePatterns;
    }

    public void setIncludePatterns(List<String> includePatterns) {
        this.includePatterns = includePatterns;
    }

    public List<String> getExcludePatterns() {
        return excludePatterns;
    }

    public void setExcludePatterns(List<String> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
}
