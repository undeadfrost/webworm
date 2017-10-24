package com.frost.webworm.webmagic.service.impl;

import com.frost.webworm.webmagic.entity.PublicEntity;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by LB on 2017/10/21.
 */
@Service
public class XunyiRepoPageProcessor implements PageProcessor {

    public final String listUrl = "\\?tm_id=7&node_id=&site_id=CMSxy&catalog_id=13&gk_index=&title=&cur_page=\\d+";
    public final String cssUrl = "div.pageing span";

    private Site site = Site.me().setDomain("www.snxunyi.gov.cn").setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 列表页
        if (page.getUrl().regex(listUrl).match()) {
            page.addTargetRequests(page.getHtml().links().regex("/gk/\\w+\\/\\d+\\.htm").all());
            page.addTargetRequests(page.getHtml().css(cssUrl).links().regex(listUrl).all());
        } else {
            PublicEntity publicEntity = new PublicEntity();
            publicEntity.setPublicUrl(page.getUrl().toString());
            publicEntity.setPublicIndex(page.getHtml().css("table.gk-table tr:nth-child(1) td:nth-child(2)").xpath("//td/text()").toString());
            publicEntity.setInputDept(page.getHtml().css("table.gk-table tr:nth-child(2) td:nth-child(2)").xpath("//td/text()").toString());
            publicEntity.setPublicTitle(page.getHtml().xpath("//div[@class=\"art-title\"]/text()").toString());
            publicEntity.setReleaseTime(page.getHtml().css("table.gk-table tr:nth-child(2) td:nth-child(4)").xpath("//td/text()").toString());
            publicEntity.setDocNo(page.getHtml().css("table.gk-table tr:nth-child(3) td:nth-child(2)").xpath("//td/text()").toString());
            publicEntity.setWebsite("旬邑县政府");
            publicEntity.setPublicColumn("政府文件");
            if (publicEntity.getPublicTitle() == null) {
                //skip this page
                page.setSkip(true);
            } else {
                page.putField("repo", publicEntity);
            }
            // page.putField("content", page.getHtml().xpath("//div[@class=\"art-con\"]/html()"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}

