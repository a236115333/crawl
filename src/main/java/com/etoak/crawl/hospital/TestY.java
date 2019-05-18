package com.etoak.crawl.hospital;

import com.alibaba.fastjson.JSONObject;

import com.etoak.crawl.hospital.entity.IdentifyingCode;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;


public class TestY {
    //网址：http://www.tcmce.cn
    //账号：1000075067
    //密码：bacd137749
    public static void main(String[] args) throws Exception {
        String baseUrl = "http://www.tcmce.cn/";
        String identUrl = "http://www.tcmce.cn/createBase64Code.shtml";//验证码
        HttpClient client = HttpClients.createDefault();
        HttpHelp help = new HttpHelp();
        String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3";
        String Origin = "http://www.tcmce.cn";
        //验证码
        String UserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36";
        String idcode = getIdentCode(identUrl, client, help, accept, Origin, UserAgent);
        //登陆
        String loginUrl = "http://www.tcmce.cn/login.shtml?loginName=1000075067&password=bacd137749&validatecode=" + idcode + "&baseCode=" + idcode;
        String str1 = help.getGetStr(loginUrl, client, accept, Origin, UserAgent);
        //获取memberId
        String memberId = str1.substring(str1.indexOf("Storage.set('memberId','") + 24, str1.indexOf("');", str1.indexOf("Storage.set('memberId','")));

        String studyUrl = baseUrl + "member/listStudy.shtml" + "?memberId=" + memberId;
        String studyHtml = help.getGetStr(studyUrl, client, accept, Origin, UserAgent);//所有大课程
        List<String>  studyClass = help.getStudyUrl(studyHtml, "继续学习","a");
        System.out.println(studyClass.toString());

        String newId = "";
        if(studyClass != null && studyClass.size()>0){
            for(int j = 0; j< studyClass.size();j++){
                String classU = studyClass.get(j);
                newId = classU.substring(classU.indexOf("newId")+6);
//                if (!"200".equals(newId))continue;
                String classUrl = baseUrl + classU + "&memberId=" + memberId;
                String classHtml = help.getGetStr(classUrl, client, accept, Origin, UserAgent);//所有课程
                Iterator<Element> links = getElementIterator(classHtml, "div");
                String classText = "";
                while (links.hasNext()) {
                    Element linka = links.next();
                    if (linka.hasClass("kc-block2")) {
                        classText = linka.html();//取div中的内容
                    }
                }
                List<String> classNameList =getStudyName(classText);//课程名称
                List<String> classIdList = getStudyTaskId(classText);//taskId
                List<String> classStatuList =getStudyStatus(classText);//学习状态
                if(classIdList != null && classIdList.size()>0){
                    for(int i = 0;i< classIdList.size();i++){
                        if(!"学习完成".equals(classStatuList.get(i))){
                            String classurl = "http://www.tcmce.cn/member/study.shtml?taskId="+classIdList.get(i)+"&newId=200&flag=1&memberId="+memberId;
                            String classhtml = help.getGetStr(classurl, client, accept, Origin, UserAgent);//所有课程
                            String formHtml = help.getValueByHtml(classhtml,"form","id","","staFrm");
//                            String formUrl = help.getValueByHtml(classhtml,"form","id","action","staFrm");///updateMemberTaskById.do
                            String id = help.getValueByHtml(formHtml,"input","name","value","id");
//                            String formurl = "http://www.tcmce.cn/member/updateMemberTaskById.do?id="+id+"&memberId="+memberId;
//                            String zhtml = help.getGetStr(formurl,client,accept,Origin,UserAgent);//刷新 视频进度
                        }
                    }
                }
                //考试题
                Long time = System.currentTimeMillis();
                String hrefUrl = "http://www.tcmce.cn:80/pager/listExam.shtml?par=1&time=" + time + "&taskId=null&flag=1&newId=200&memberId=" + memberId;
                String hrefHtml = help.getGetStr(hrefUrl, client, accept, Origin, UserAgent);//
                System.out.println(hrefHtml);
                String formH = help.getValueByHtml(hrefHtml,"form","name","","form1");
                String taskId = help.getValueByHtml(formH,"input","name","value","taskId");
                String pagerId = help.getValueByHtml(formH,"input","name","value","pagerId");
                String flag = help.getValueByHtml(formH,"input","name","value","flag");
                String sign = help.getValueByHtml(formH,"input","name","value","sign");
                String count = help.getValueByHtml(formH,"input","name","value","count");
                String pageterm = help.getValueByHtml(formH,"input","name","value","pageterm");
                String question = help.getQuesten(formH,"title");
                //提交url
                String submitHtml = getString(client, help, accept, Origin, UserAgent, memberId, taskId, pagerId, flag, sign, count, pageterm, question);

                List<String>  dddddd = help.getStudyUrl(submitHtml, "","li");
//                System.out.println(dddddd.toString());
            }
        }

        //题 http://www.tcmce.cn:80/pager/listExam.shtml?par=1&time=1556097792022&taskId=null&flag=1&newId=200&memberId=FBAEE1ED23FB962A
        String submitUrl = "http://www.tcmce.cn/pager/comExam.shtml?memberId=FBAEE1ED23FB962A&url=null&taskId=2703&" +
                "pagerId=1301273&flag=1&sign=1&count=1&pageterm=2019-04&title0=91833&rdo0=B&title1=91836&rdo1=B&" +
                "title2=91823&rdo2=B&title3=91832&rdo3=D&title4=91826&rdo4=D&Submit=交卷";
//        String submitHtml = help.getGetStr(submitUrl, client, accept, Origin, UserAgent);
//        System.out.println(submitHtml);
    }

    /**
     * 提交考试
     * @param client
     * @param help
     * @param accept
     * @param origin
     * @param userAgent
     * @param memberId
     * @param taskId
     * @param pagerId
     * @param flag
     * @param sign
     * @param count
     * @param pageterm
     * @param question
     * @return
     * @throws Exception
     */
    private static String getString(HttpClient client, HttpHelp help, String accept, String origin, String userAgent, String memberId, String taskId, String pagerId, String flag, String sign, String count, String pageterm, String question) throws Exception {
        String submitUrl = "http://www.tcmce.cn/pager/comExam.shtml?memberId="+memberId+"&url=null&taskId="+
                taskId+"&pagerId="+pagerId+"&flag="+flag+"&sign="+sign+"&count="+count+"&pageterm="+pageterm+
                "&Submit=交卷";
        //提交结果
       String  submitHtml = help.getGetStr(submitUrl+question, client, accept, origin, userAgent);
       if(submitHtml.indexOf("重新考试") != -1){
           question = help.getNewAnswer(submitHtml,question,"li");
//           System.out.println(question);
//           getString(client, help, accept, origin, userAgent, memberId, taskId, pagerId, flag, sign, count, pageterm, question);
       }
        return "true";
    }

    /**
     * 获取验证码
     * @param identUrl
     * @param client
     * @param help
     * @param accept
     * @param origin
     * @param userAgent
     * @return
     * @throws Exception
     */
    private static String getIdentCode(String identUrl, HttpClient client, HttpHelp help, String accept, String origin, String userAgent) throws Exception {
        String str = help.getPostStr(identUrl, client, accept, origin, userAgent, null);
        //获取验证码
        IdentifyingCode identCode = (IdentifyingCode) help.getBody("com.etoak.crawl.hospital.entity.IdentifyingCode",str);
        return identCode.getCode() + "";
    }

    public static List<String> getStudyName(String  studyText){
        List<String> resultList = new ArrayList<String>();
        Iterator<Element> classLinks = getElementIterator(studyText, "a");
        while (classLinks.hasNext()) {
            Element linka = classLinks.next();
            if (!"课程简介".equals(linka.text()) && !"张再良".equals(linka.text())) {
                resultList.add(linka.text());
            }
        }
        return resultList;
    }

    public  static List<String> getStudyTaskId(String  studyText){
        List<String> resultList = new ArrayList<String>();
        Iterator<Element> classLinks = getElementIterator(studyText, "a");
        while (classLinks.hasNext()) {
            Element linka = classLinks.next();
            if ("课程简介".equals(linka.text())) {
                String url = linka.attr("name");
                String taskId = url.substring(url.indexOf("taskId")+7);
                resultList.add( taskId);
            }
        }
        return resultList;
    }

    public  static  List<String> getStudyStatus(String  studyText){
        List<String> resultList = new ArrayList<String>();
        Iterator<Element> classLinks = getElementIterator(studyText, "span");
        while (classLinks.hasNext()) {
            Element statulinka = classLinks.next();
            String text = statulinka.text();
            if (text != null && !"".equals(text) && text.indexOf("学习") != -1) {
                resultList.add(text);
            }
        }
        return resultList;
    }

    private static Iterator<Element> getElementIterator(String studyText, String a) {
        Document classDoc = Jsoup.parse(studyText);//解析HTML字符串返回一个Document实现
        Elements classLink = classDoc.select(a);//查找第一个a元素Document classDoc = Jsoup.parse(classText);//解析HTML字符串返回一个Document实现
        return classLink.iterator();
    }
}
