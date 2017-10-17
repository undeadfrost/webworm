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
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("url", page.getUrl().regex("http://www.xianyang.gov.cn/xyxw/jryw/(\\w+).*").toString());
        page.putField("name", page.getHtml().xpath("//div[@class='item_title']/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("http://www.xianyang.gov.cn/xyxw/jryw/(\\w+).*").all());
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.xianyang.gov.cn/info/iList.jsp?cat_id=10011").thread(1).run();
    }
}
