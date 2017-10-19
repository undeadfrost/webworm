package com.frost.webworm;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    public final String listUrl = "\\?site_id=CMSxy&cat_id=10011&cur_page=\\d+";
    public final String cssUrl = "div.commonPage form";

    private Site site = Site.me().setDomain("www.xianyang.gov.cn").setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 列表页
        if (page.getUrl().regex(listUrl).match()){
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"item_title\"]").links().regex("/xyxw/jryw/\\w+\\.htm").all());
            page.addTargetRequests(page.getHtml().css(cssUrl).links().regex(listUrl).all());
        } else {
            page.putField("title", page.getHtml().xpath("//div[@id=\"info_title\"]/text()"));
            page.putField("release_time", page.getHtml().xpath("//span[@id=\"info_released_dtime\"]/text()"));
            page.putField("content", page.getHtml().xpath("//div[@id=\"info_content\"]/html()"));
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.xianyang.gov.cn/info/iList.jsp?site_id=CMSxy&cat_id=10011&cur_page=1").thread(1).run();
    }
}
