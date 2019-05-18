package com.etoak.crawl.hospital;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etoak.crawl.util.IdentifyingCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpHelp {

    private static String[] oneAnswer = new String[]{"A","B","C","D"};
    private static String[] moreAnswer = new String[]{"A","AB","AC","AD","ABC","ABD","ACD","ABCD","BC","BD","BCD","CD"};

    /**
     * 发送post请求
     * @param url
     * @param client
     * @param Accept
     * @param Origin
     * @param UserAgent
     * @param ent
     * @return
     * @throws Exception
     */
    public  String getPostStr(String url, HttpClient client, String Accept, String Origin, String UserAgent, StringEntity ent) throws Exception{
        String retStr = "";
        HttpPost post = new HttpPost(url);//目标页面
        post.setEntity(ent);
        retStr = responseResult(post,client,Accept,Origin,UserAgent);
        return retStr;
    }

//    public Object jsonToObject(String str){
//        JSONObject json = JSONObject.parseObject(str);
//        Object identCode = (Object)JSONObject.toJavaObject(json,Object.class);
//        return identCode;
//    }

    /**
     * 发送get请求
     * @param url
     * @param client
     * @param Accept
     * @param Origin
     * @param UserAgent
     * @return
     * @throws Exception
     */
    public  String getGetStr(String url, HttpClient client, String Accept, String Origin, String UserAgent) throws Exception{
        String retStr = "";
        HttpGet get = new HttpGet(url);//目标页面
        retStr = responseResult(get,client,Accept,Origin,UserAgent);
        return retStr;
    }

    /**
     * 获取响应信息
     * @param httpPostGet
     * @param client
     * @param Accept
     * @param Origin
     * @param UserAgent
     * @return
     * @throws Exception
     */
    public String responseResult(HttpRequestBase httpPostGet,HttpClient client, String Accept, String Origin, String UserAgent) throws Exception{
        String result = "";
        httpPostGet.addHeader("Accept", Accept);
        httpPostGet.addHeader("Origin", Origin);
        httpPostGet.addHeader("User-Agent", UserAgent);
        HttpResponse response = client.execute(httpPostGet);
        int code = response.getStatusLine().getStatusCode();
        String locationUrl = "";
        if(code==200 ){
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        }else if(code==302){
            locationUrl=response.getLastHeader("Location").getValue();
//            System.out.println(locationUrl);
//            get(locationUrl);//跳转到重定向的url
        }else{
            throw new Exception("http get/post call error httpCode: "+ String.valueOf(code));
        }
        return result;
    }

    /**
     *
     * @param html
     * @param name   值
     * @param attr   标签
     * @return
     */
    public List<String> getStudyUrl(String html,String name,String attr){
        List<String> studyList = new ArrayList<String>();
        if("".equals(html) || html==null){
            return new ArrayList<String>();
        }

        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Elements link = doc.select(attr);//查找第一个元素
        Iterator<Element> links =  link.iterator();
        while(links.hasNext()){
            Element linka = links.next();
            if("".equals(name)){
                studyList.add(linka.text());
            }else{
                //判断值与标签内容比较
                if(name.equals(linka.text())){
                    String linkaHref = linka.attr("name");
                    studyList.add(linkaHref);
                }
            }
        }
        return studyList;
    }

    public StringEntity getStrEnt(Map map){
        StringEntity stringEntity = null;
        if(map != null && map.size() >0){
            stringEntity = new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
        }
        return stringEntity;
    }

    /**
     * 实体转换
     * @param className
     * @param str
     * @return
     * @throws ClassNotFoundException
     */
    public Object getBody(String  className,String str) throws ClassNotFoundException {
        Class obj = Class.forName(className);
        JSONObject json = JSONObject.parseObject(str);
        return JSONObject.toJavaObject(json, obj);
    }


    /**
     *
     * @param html   字符串
     * @param label     标签名
     * @param attribute     确定标签用的属性名
     * @param attribute1    要取的属性值 为空则返回标签内html
     * @param value         确定标签用的属性值
     * @return
     */
    public String getValueByHtml(String html,String label,String attribute,String attribute1,String value){
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Elements link = doc.select(label);//查找第一个a元素
        Iterator<Element> links =  link.iterator();
        while(links.hasNext()){
            Element linka = links.next();
            //获取属性值
            String attributeValue = linka.attr(attribute);
            if(value.equals(attributeValue)){
                if("".equals(attribute1)){
                    return linka.html();
                }else{
                    return linka.attr(attribute1);
                }

            }
        }
        return  "";
    }

    /**
     *
     * @param html
     * @param nameValue name属性的值
     * @return
     */
    public String getQuesten(String html,String nameValue){
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Elements link = doc.select("input");//查找第一个a元素
        Iterator<Element> links =  link.iterator();
        boolean flag = false;
        String result = "";
        while(links.hasNext()){
            Element linka = links.next();
            String name = linka.attr("name");
            if(name.indexOf(nameValue) != -1 ){
                result += "&"+name+"="+linka.attr("value");
                flag = true;
            }else if(flag){
                result += "&"+name+"="+linka.attr("value");
                flag = false;
            }
        }
        return  result;
    }

    public String getNewAnswer(String html ,String param,String attr) throws Exception {
        List<String>   list = getStudyUrl(html, "",attr);
        if(list != null && list.size()>0){
            for(int i=0;i<list.size();i++){
                if(list.get(i).length() >23){
                    if("错误".equals(list.get(i).substring(21,23))){
                        String select = list.get(i).substring(11,12);
                        String target = "rdo"+(i-1)+"="+select;
                        String newAs = getNewSelect(select);
                        if("false".equals(newAs)){//未知答案
                                throw new Exception();
                        }
                        String replacement = "rdo"+(i-1)+"="+getNewSelect(select);
                        param.replace(target,replacement);
                    }else{//处理正确答案
                        String[] answer = getRightAnswer(param,i);
                    }
                }
            }
        }
        return param;
    }

    public String getNewSelect(String select){
        String result = "";
        for (int i = 0; i < oneAnswer.length; i++) {
            if(i == oneAnswer.length-1){
                result = "false";
                break;
            }
            if(select.equals(oneAnswer[i])){
                result = oneAnswer[i+1];
                break;
            }
        }

        return result;
    }


    public String[] getRightAnswer(String param,int i){
        String[] res = new String[2];
        String title = "title"+i;
        String rdo = "rdo"+i;
        int num = 0;
        if(param.indexOf(title) != -1){
            num = param.indexOf(title);
        }else{
            //转换出错
            return null;
        }
        res[0] = param.substring(param.indexOf(title),param.indexOf("&",param.indexOf(title)));
        res[1] = param.substring(param.indexOf(rdo),param.indexOf("&",param.indexOf(rdo)));

        return res;
    }
    public static void main(String[] args) {
        String a = "第1题： 您的选项是：A      结果：错误   得分：0.0";
        System.out.println(a.substring(1,2));
        System.out.println(a.substring(11,12));
        System.out.println(a.substring(21,23));
    }



}
