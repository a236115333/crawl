package com.etoak.httpCrawl;

import com.alibaba.fastjson.JSONObject;
import com.etoak.crawl.util.IdentifyingCode;
import com.etoak.httpCrawl.uitl.Base64Util;
import com.etoak.httpCrawl.uitl.FileUtil;
import com.etoak.httpCrawl.uitl.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class BaiduCrawl {
    static String  access_token = "24.ef4d34037a418c71b0ee8df9e2f513d3.2592000.1558151165.282335-16041014";
    public static void main(String[] args) {
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
        String filePath = "";
        try {
//            filePath = "data:image/png;base64,"+data();
//            byte[] imgData = FileUtil.readFileByBytes(filePath);
//            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(filePath, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = access_token;
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static String data() throws Exception{
//        String yanzhengm = "http://www.tcmce.cn/createBase64Code.shtml";//验证码
////        Map map = new HashMap();
////        map.put("memberId","03F19C5C48144795");
//        HttpClient client = HttpClients.createDefault();
//        String  str = getPostStr(yanzhengm,client);
//        JSONObject json = JSONObject.parseObject(str);
//        IdentifyingCode identCode = (IdentifyingCode)JSONObject.toJavaObject(json,IdentifyingCode.class);
//        int idcode = identCode.getCode();//验证码
//        String identData = identCode.getData();//验证码64位编码
//        System.out.println(idcode);
//        return identData;
//    }


//    public static String getPostStr(String url, HttpClient client) throws Exception{
//        String retStr = "";
//        HttpPost post = new HttpPost(url);//目标页面
////        post.setEntity(stringEntity);
//        post.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
////        post.addHeader("Content-Type", "application/json");
//        post.addHeader("Origin", "https://account.geekbang.org");
//        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
//
//        HttpResponse response = client.execute(post);
//        int code = response.getStatusLine().getStatusCode();
//        if(code==200){
//            HttpEntity entity = response.getEntity();
//            retStr = EntityUtils.toString(entity);
//        }else{
//            throw new Exception("http post2 call error httpCode: "+ String.valueOf(code));
//        }
//        return retStr;
//    }
//
//    /**
//     * 获取AccessToken
//     * @return
//     * @throws Exception
//     */
//    public static String getAccessToken() throws Exception{
//        String accessToken = "";
//        HttpClient client = HttpClients.createDefault();
////        HttpPost post = new HttpPost("https://aip.baidubce.com/oauth/2.0/token");//目标页面
////        HashMap<String, String> params = new HashMap<String, String>();
////        params.put("grant_type", "client_credentials");
////        params.put("client_id", "63FW0BYNpHW3IpbaDvTV5MKo");
////        params.put("client_secret", "PmqN8juCo6yBXu3WSDWFWl6niRxTErWb");
////        StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON);
////        post.setEntity(stringEntity);
////        HttpResponse response = client.execute(post);
//        HttpGet get = new HttpGet("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=63FW0BYNpHW3IpbaDvTV5MKo&client_secret=PmqN8juCo6yBXu3WSDWFWl6niRxTErWb");
//        HttpResponse response = client.execute(get);
//        String retStr = "";
//        try {
//            HttpEntity entity = response.getEntity();
//            retStr = EntityUtils.toString(entity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (response.getStatusLine().getStatusCode() == 200) {
//            JSONObject jsonObject = JSONObject.parseObject(retStr);
//            if (jsonObject != null && !jsonObject.isEmpty()) {
//                accessToken = jsonObject.getString("access_token");//expires_in为有效期
//            }
//        }
//        return accessToken;
//    }
}
