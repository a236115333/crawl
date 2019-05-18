package com.etoak.httpCrawl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.etoak.crawl.util.IdentifyingCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class TestY {

    public static void main(String[] args) throws Exception{
//        Map map = new HashMap();
//        map.put("memberId","03F19C5C48144795");
        HttpClient client = HttpClients.createDefault();
//        String  str = getPostStr(yanzhengm,client);
//        JSONObject json = JSONObject.parseObject(str);
//        IdentifyingCode identCode = (IdentifyingCode)JSONObject.toJavaObject(json,IdentifyingCode.class);
//        int idcode = identCode.getCode();//验证码
//        String identData = identCode.getData();//验证码64位编码
////        System.out.println(identCode.getData());
//        String analyzeCode = OCRVCode(identData);
//        System.out.println(analyzeCode + "=" +idcode);
    }


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

//    /**
//     * 识别验证码
//     * @param imageUrl
//     * @return
//     * @throws Exception
//     */
//    public static String OCRVCode(String imageUrl) throws Exception{
//        String VCode = "";
//        String url = "";
//        if (isBlank(getAccessToken())) {
//            return VCode;
//        }
////        String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imageUrl, "UTF-8");
//        url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token=" + getAccessToken();
//        url= url.replaceAll(" ", "+");
//
////        Map params = new HashMap<String, String>();
////        params.put("image", imageUrl);
////        params.put("url", "data:image/png;base64,"+imageUrl);
//        HttpClient client = HttpClients.createDefault();
////        StringEntity strEnt = new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON);
//        List<BasicNameValuePair> list = new LinkedList<BasicNameValuePair>();
//        list.add( new BasicNameValuePair("image",imageUrl ));
//        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
//        HttpPost post = new HttpPost(url);
//        post.addHeader("Content-Type","application/x-www-form-urlencoded");
//        post.setEntity(entityParam);
////        HttpGet get = new  HttpGet(url);
////        get.addHeader("Content-Type","application/x-www-form-urlencoded");
//
//        HttpResponse response = client.execute(post);
//        String json = "";
//        if (response.getStatusLine().getStatusCode() == 200) {
//            try {
//                json = EntityUtils.toString(response.getEntity());
//                JSONObject jsonObject = JSONObject.parseObject(json);
//                JSONArray wordsResult = jsonObject.getJSONArray("words_result");
//                VCode = wordsResult.getJSONObject(0).getString("words");
//            } catch (IOException e) {
//            }
//        }
//        return VCode;
//    }

    public static boolean isBlank(String str){
        if(str != null && str != ""){
            return false;
        }else{
            return true;
        }
    }
}
