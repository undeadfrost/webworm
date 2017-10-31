package com.frost.webworm.webmagic.service.impl;

import com.frost.webworm.webmagic.dao.XydqwDao;
import com.frost.webworm.webmagic.entity.XydqwEntity;
import com.frost.webworm.webmagic.utlis.DownloadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LB on 2017/10/21.
 * 咸阳地情网www.xydqw.com数据抓取
 */
@Service
public class SxsdqRepoPageProcessor implements PageProcessor {

    public final String listUrl = "/sqzlk/xbsxz/sxdyl/xys_16200/xysz1/index.xml";
    public final String cssUrl = "div.pagediv";
    public final String url = "http://www.xydqw.com/yxxy/ystz/index_";
    public final String contentUrl = ".*/yxxy/ystz/\\d+/\\w+\\.htm";
    public final String colum = "主题活动文明创建";

    private Site site = Site.me().setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Autowired
    private XydqwDao xydqwDao;

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 列表页
//        for (int i = 1 ; i < 10 ; i++){
//            page.addTargetRequest(url + i + ".htm");
//        }
        if (page.getUrl().regex(listUrl).match()) {
            List<String> urlList = page.getHtml().regex("./\\d+/\\w+\\.html").all();
            String webUrl = page.getUrl().toString();
            for (String url : urlList){
                page.addTargetRequest(pageUrl(webUrl,url));
            }
        } else {
            XydqwEntity xydqwEntity = new XydqwEntity();
            xydqwEntity.setTitle(page.getHtml().css("h4.title").xpath("//h4/text()").toString().trim());
            xydqwEntity.setContent(page.getHtml().css("div.cnt").xpath("//div/html()").toString());
            xydqwEntity.setColum(colum);
            if (xydqwEntity.getTitle() == null) {
                //skip this page
                page.setSkip(true);
            } else {
                page.putField("repo", xydqwEntity);
            }
            // page.putField("content", page.getHtml().xpath("//div[@class=\"art-con\"]/html()"));
        }
    }

    public String pageUrl(String webUrl, String url){
        webUrl = webUrl.substring(0, webUrl.lastIndexOf("/"));
        url = url.substring(1);
        return webUrl + url;
    }
    @Override
    public Site getSite() {
        return site;
    }
}

