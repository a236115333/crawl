package com.etoak.crawl;

import com.etoak.crawl.util.CharsetDetector;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class fileContent {

    //main 方法入口
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        // 设置 HTTP 连接超时 5s
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        // 2.生成 GetMethod 对象并设置参数
        PostMethod post = new PostMethod("https://account.geekbang.org/account/ticket/login");
        post.addParameter("cellphone","15004240862");
        post.addParameter("password","hyj236115333");
        post.addParameter("appid","1");
        post.addParameter("country","86");
        post.addParameter("platform","3");
        post.addRequestHeader("Accept","application/json, text/plain, */*");
//        post.addRequestHeader("Accept-Encoding","gzip, deflate, br");
//        post.addRequestHeader("Accept-Language","zh-CN,zh;q=0.9");
        post.addRequestHeader("Origin","https://account.geekbang.org");
        post.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
//        post.addRequestHeader("Connection","keep-alive");
        post.addRequestHeader("Content-Type","application/json");
//        post.addRequestHeader("Content-Length","115");


//        post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
//
//        post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
// 3.执行 HTTP GET 请求
        try {
            int statusCode = httpClient.executeMethod(post);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + post.getStatusLine());
            }
            // 4.处理 HTTP 响应内容
            byte[] responseBody = post.getResponseBody();// 读取为字节 数组
            String contentType = post.getResponseHeader("Content-Type").getValue(); // 得到当前返回类型

            Document doc = Jsoup.parse(new String(responseBody,  CharsetDetector.guessEncoding(responseBody)), "https://account.geekbang.org/account/ticket/login");
            System.out.println(doc.toString());
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            e.printStackTrace();
        } finally {
            // 释放连接
            post.releaseConnection();
        }
    }


}
