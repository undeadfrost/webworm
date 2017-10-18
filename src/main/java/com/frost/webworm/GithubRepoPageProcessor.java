package com.frost.webworm;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetrySleepTime(3).setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 过滤获取列表文章链接
        page.addTargetRequests(page.getHtml().xpath("//div[@class=\"item_title\"]").links().regex("/xyxw/jryw/\\w+\\.htm").all());
        //
        page.putField("title", page.getHtml().xpath("//div[@id=\"info_title\"]/text()").toString());
        page.putField("release_time", page.getHtml().xpath("//span[@id=\"info_released_dtime\"]/text()").toString());
        page.putField("content", page.getHtml().xpath("//div[@id=\"info_content\"]/html()").toString());
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.xianyang.gov.cn/info/iList.jsp?cat_id=10011").thread(1).run();
    }
}
