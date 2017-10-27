package com.frost.webworm.webmagic.service.impl;

import com.frost.webworm.webmagic.dao.XydqwDao;
import com.frost.webworm.webmagic.entity.XydqwEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LB on 2017/10/21.
 */
@Service
public class XydqwRepoPageProcessor implements PageProcessor {

    public final String listUrl = "/zjlt/zjzyjh/inde\\w+\\.htm";
    public final String cssUrl = "div.pagediv";
    public final String url = "http://www.xydqw.com/zjlt/zjzyjh/index_";
    public final String contentUrl = ".*/zjlt/zjzyjh/\\d+/\\w+\\.htm";
    public final String colum = "志鉴论坛重要讲话";

    private Site site = Site.me().setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Autowired
    private XydqwDao xydqwDao;

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 列表页
        for (int i = 1 ; i < 10 ; i++){
            page.addTargetRequest(url + i + ".htm");
        }
        if (page.getUrl().regex(listUrl).match()) {
            List<String> linkList = page.getHtml().css("li.content").links().all();
            for (int i = 0 ; i < linkList.size() ; i++) {
                if (linkList.get(i).matches(contentUrl)){
                    page.addTargetRequest(linkList.get(i));
                } else {
                    XydqwEntity xydqwEntity = new XydqwEntity();
                    xydqwEntity.setTitle(page.getHtml().css("li.content div:nth-child(1)").xpath("//a/text()").all().get(i).trim());
                    xydqwEntity.setTime(page.getHtml().css("li.content div:nth-child(2)").xpath("//div/text()").all().get(i).trim());
                    xydqwEntity.setLink(page.getHtml().css("li.content").links().all().get(i).trim());
                    xydqwEntity.setColum(colum);
                    xydqwDao.save(xydqwEntity);
                }
            }
        } else {
            XydqwEntity xydqwEntity = new XydqwEntity();
            xydqwEntity.setTitle(page.getHtml().css("div.title").xpath("//div/text()").toString());
            // xydqwEntity.setSource(page.getHtml().$("div.title1").regex("(?<=来源：).*?(?=&nbsp;)").toString().trim());
            xydqwEntity.setAuthor(page.getHtml().$("div.title1").regex("(?<=作者：).*?(?=&nbsp;)").toString().trim());
            xydqwEntity.setTime(page.getHtml().$("div.title1").regex("(?<=时间：).*?(?=</div>)").toString().trim());
            xydqwEntity.setContent(page.getHtml().css("div.content").xpath("//div/html()").toString());
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

    @Override
    public Site getSite() {
        return site;
    }
}

