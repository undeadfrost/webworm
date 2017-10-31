package com.frost.webworm.webmagic.service.impl;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.xml.ws.spi.http.HttpContext;
import java.util.HashMap;
import java.util.Map;

@Service
public class CcdiRepoPageProcessor implements PageProcessor{

    private Site site = Site.me().setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        page.getHtml().links().all();
        for (int i=1 ; i <= 1 ; i++){
            Request request = new Request("http://www.ccdi.gov.cn/fgk/law_pagenumb");
            request.setMethod(HttpConstant.Method.POST);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("m","pagenumb");
            map.put("p","1");
            map.put("cstart","1");
            map.put("ptcxKey","");
            map.put("ptcxSpecies","");
            map.put("types","fgfl");
            map.put("fgmc","");
            map.put("fbrq","");
            map.put("fgwh",null);
            map.put("zhclassify","");
            map.put("gjc","");
            map.put("bhgjc","");
            map.put("bbhgjc","");
            map.put("validity","0101");
            request.setRequestBody(HttpRequestBody.form(map,"utf-8"));
            page.addTargetRequest(request);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
