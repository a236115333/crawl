package com.etoak.crawl.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 *
 *  远程调用接口方法
 */
public class test {


    /**
     * post请求
     * @param url 请求的地址
     * @param map 参数结合
     * @return String
     * @throws Exception
     */
//    public static String httpPost(String url, Map<String, Object> map) throws Exception {
//        String url2 = "https://time.geekbang.org/serv/v1/my/products/all";//目标页
//        String url3 = "https://time.geekbang.org/serv/v1/column/articles";//章节信息
//        String url4 = "https://time.geekbang.org/serv/v1/article";//章节内容
//        String url5 = "https://time.geekbang.org/serv/v1/comments";//精品留言
//        String url6 = "https://account.geekbang.org/dashboard/buy";//我的订阅
//
//        HttpClient client = HttpClients.createDefault();
//        String retStr="";
//        StringEntity stringEntity = new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
//        String str = getPostStr(url,stringEntity,client,"1");
//        //目标页面
//        String str2 = getPostStr(url2,stringEntity,client,"1");
//        //章节信息
//        map.put("cid","108");
//        map.put("size","2");
////        map.put("order","earliest");
////        map.put("sample","false");
//        StringEntity stringEntity2 = new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
//        String str3 = getPostStr(url3,stringEntity2,client,"1");
////        str3 = str3.replaceAll("\\\\", "");
////        str3 = str3.replaceAll("/", "");
//        JSONObject jsonObject = JSONObject.parseObject(str3);
//        responeBody grades = (responeBody) JSONObject.toJavaObject(jsonObject,responeBody.class);
//        //章节内容
//        List<listBody> list = grades.getData().getList();
//        System.out.println(list.size());
//        File file = new File("C:\\Users\\issuser\\Desktop\\ddd.doc");
//        file.createNewFile();
//        FileOutputStream ou = new FileOutputStream(file);
////        OutputStreamWriter out = new OutputStreamWriter(ou);
////        out.write();
//        for(listBody body:list){
//            String  title = body.getArticle_title();
//            ou.write(title.getBytes());
//            map.put("id",body.getId());
//            StringEntity stringEntity1 = new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
//            String str4 = getPostStr(url4,stringEntity1,client,"2");
//            JSONObject jsonObject4 = JSONObject.parseObject(str4);
//            ContentBody grades4 = (ContentBody) JSONObject.toJavaObject(jsonObject4,ContentBody.class);
//            ou.write(grades4.getData().getArticle_content().getBytes());
////            System.out.println(grades4.getData().getArticle_content());
//            //章节评论
////            map.put("aid",body.getId());
////            map.put("prev","0");
////            StringEntity stringEntity3 = new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
////            String str5 = getPostStr(url5,stringEntity3,client,"2");
//        }
//        ou.close();
//        return retStr;
//    }
//
//    public static void main(String[] args) {
//        Map<String, Object> param = new HashMap<String, Object>();
////        Random random = new Random(5);
////        float[][] data = new float[60][8];
////        for(int i = 0; i < 60; i++) {
////            for(int j = 0; j < 8; j++) {
////                data[i][j] = random.nextFloat();
////            }
////        }
//        String url = "https://account.geekbang.org/account/ticket/login";//首页
//
//        try {
//            param.put("appid", 1);
//            param.put("cellphone", "15004240862");
//            param.put("country", 86);
//            param.put("password", "hyj236115333");
//            param.put("platform", 3);
//            System.out.println(test.httpPost(url, param));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static String getPostStr(String url,StringEntity stringEntity,HttpClient client,String type) throws Exception{
//        String retStr = "";
//        HttpPost post = new HttpPost(url);//目标页面
//        post.setEntity(stringEntity);
//        post.addHeader("Accept", "application/json, text/plain, */*");
//        post.addHeader("Content-Type", "application/json");
//        if("1".equals(type)){
//            post.addHeader("Origin", "https://account.geekbang.org");
//            post.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
//        }else{
//            post.addHeader("Origin", "https://time.geekbang.org");
//            post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
//        }
//
//        HttpResponse response = client.execute(post);
//        int code = response.getStatusLine().getStatusCode();
//        if(code==200){
//            HttpEntity entity = response.getEntity();
//            retStr = EntityUtils.toString(entity);
//        }else{
//            throw new Exception("http post2 call error httpCode: "+ String.valueOf(code));
//        }
//        System.out.println("post:"+retStr);
//        return retStr;
}