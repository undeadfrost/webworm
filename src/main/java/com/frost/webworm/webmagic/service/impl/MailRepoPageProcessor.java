package com.frost.webworm.webmagic.service.impl;

import com.frost.webworm.webmagic.entity.MailBoxEntity;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
public class MailRepoPageProcessor implements PageProcessor {
    public final String listUrl = "/appeal/list\\.jsp\\?model_id=4&dept_id=20&cur_page=\\d+";
    public final String cssUrl = "div.commomPage span";

    private Site site = Site.me().setDomain("www.xys.gov.cn").setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public void process(Page page) {
        if (page.getUrl().regex(listUrl).match()) {
            page.addTargetRequests(page.getHtml().links().regex("/appeal/view\\.jsp\\?model_id=4&sq_id=\\d+").all());
            page.addTargetRequests(page.getHtml().css(cssUrl).links().regex(listUrl).all());
        } else {
            MailBoxEntity mailBoxEntity = new MailBoxEntity();
            mailBoxEntity.setIndex(page.getHtml().css("table.mail_content:nth-child(1) tr:nth-child(2)").xpath("//td/text()").toString());
            mailBoxEntity.setType(page.getHtml().css("table.mail_content:nth-child(1) tr:nth-child(3)").xpath("//td/text()").toString());
            mailBoxEntity.setMailTitle(page.getHtml().css("table.mail_content:nth-child(1) tr:nth-child(4)").xpath("//td/text()").toString());
            mailBoxEntity.setSendTime(page.getHtml().css("table.mail_content:nth-child(1) tr:nth-child(5)").xpath("//td/text()").toString());
            mailBoxEntity.setSendContent(page.getHtml().css("table.mail_content:nth-child(1) tr:nth-child(6)").xpath("//td/text()").toString());
            mailBoxEntity.setCompany(page.getHtml().css("table.mail_content:nth-child(3) tr:nth-child(2)").xpath("//td/text()").toString());
            mailBoxEntity.setReplyTime(page.getHtml().css("table.mail_content:nth-child(3) tr:nth-child(3)").xpath("//td/text()").toString());
            mailBoxEntity.setReplyContent(page.getHtml().css("table.mail_content:nth-child(3) tr:nth-child(4)").xpath("//td/html()").toString());
            if (mailBoxEntity.getIndex() == null) {
                //skip this page
                page.setSkip(true);
            } else {
                page.putField("repo", mailBoxEntity);
            }
            // page.putField("content", page.getHtml().xpath("//div[@class=\"art-con\"]/html()"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
