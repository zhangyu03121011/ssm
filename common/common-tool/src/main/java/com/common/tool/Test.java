package com.common.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test {

    public static void main(String[] args) {

        HttpClient httpClient = null;
        HttpPost httpPost = null;
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost("http://192.168.1.186/app/link/search");
            
            // 设置参数
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("id", "111"));
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println(EntityUtils.toString(resEntity, "utf-8"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
