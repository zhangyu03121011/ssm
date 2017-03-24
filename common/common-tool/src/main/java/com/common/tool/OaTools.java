package com.common.tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class OaTools {

    private static final String LOGON_SITE = "http://oa.ceacsz.com.cn";

    private static final int LOGON_PORT = 80;

    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);

        // 登录页面
        PostMethod post = new PostMethod("http://oa.ceacsz.com.cn/Account/LoginUser.html");
        post.addRequestHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");

        NameValuePair url = new NameValuePair("url", "/Account/LoginUser.html");

        NameValuePair username = new NameValuePair("username", "xiangyu.wang");
        NameValuePair password = new NameValuePair("password", "123456");

        post.setRequestBody(new NameValuePair[] { url, username, password });
        client.executeMethod(post);
        System.out.println("******************************登录******************************");
        Cookie[] cookies = client.getState().getCookies();
        client.getState().addCookies(cookies);
        System.out.println(post.getStatusText());
        post.releaseConnection();

        System.out.println("******************************输出Cookies******************************");
        System.out.println("==========Cookies============");
        int i = 0;
        for (Cookie c : cookies) {
            System.out.println(++i + ":   " + c);
        }
        client.getState().addCookies(cookies);
        post.releaseConnection();

        // post.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
        // DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy",
        // CookiePolicy.DEFAULT);

        System.out.println("******************************输出用户信息******************************");
        String newUrl = "http://oa.ceacsz.com.cn/Account/GetCurrentUserInfo.html";
        post = new PostMethod(newUrl);
        post.setRequestBody(new NameValuePair[] {});
        client.executeMethod(post);
        String responseString = post.getResponseBodyAsString();
        System.out.println(responseString);
        post.releaseConnection();

        System.out.println("******************************判断信息******************************");
        newUrl = "http://oa.ceacsz.com.cn/Attendance/CheckSignInToWork.html";
        post = new PostMethod(newUrl);
        post.setRequestBody(new NameValuePair[] {});
        client.executeMethod(post);
        responseString = post.getResponseBodyAsString();
        System.out.println(responseString);
        post.releaseConnection();

        // DesktopGrid/LoadDesktopGridDataBySql.html
        // searchField=Base_EmployeeName&key=%E7%BD%97%E4%BC%9A%E5%86%9B&page=1&rows=10&sort=CreateDate&order=desc

        // searchField=Base_EmployeeName&key=%E7%BD%97%E4%BC%9A%E5%86%9B&page=1&rows=10&sort=CreateDate&order=desc

        System.out.println("******************************xx******************************");
        newUrl = "http://oa.ceacsz.com.cn/Grid/LoadGridData.html?moduleId=a8f0426a-64f0-4cc4-bdbc-a3a900c4dac8";
        post = new PostMethod(newUrl);
        NameValuePair a = new NameValuePair("page", "1");
        NameValuePair b = new NameValuePair("rows", "1");
        NameValuePair c = new NameValuePair("sort", "CreateDate");
        NameValuePair d = new NameValuePair("order", "desc");
        post.setRequestBody(new NameValuePair[] { a, b, c, d });
        client.executeMethod(post);
        responseString = post.getResponseBodyAsString();

        System.out.println(responseString);
        JSONArray jsonArray = JSONArray.fromObject("[" + responseString + "]");
        String str = jsonArray.get(0).toString();
        str = str.substring(1, str.length() - 1);
        str = str.replaceAll("new Date", "");
        str = str.replaceAll("\\(", "");
        str = str.replaceAll("\\)", "");
        // str=str.replaceAll("[\\t\\n\\r]", "");
        // System.out.println(str);
        System.out.println(JSONObject.fromObject(str));
        // JSONObject obj = (JSONObject) jsonObject.get("rows");
        // System.out.println(obj.getString("WorkPunchTypeName"));
        post.releaseConnection();

        // System.out.println("******************************下班******************************");
        // newUrl = "http://oa.ceacsz.com.cn/Attendance/SignInFromWork.html";
        // post = new PostMethod(newUrl);
        // post.setRequestBody(new NameValuePair[] {});
        // client.executeMethod(post);
        // responseString = post.getResponseBodyAsString();
        // System.out.println(responseString);
        // post.releaseConnection();

    }
}
