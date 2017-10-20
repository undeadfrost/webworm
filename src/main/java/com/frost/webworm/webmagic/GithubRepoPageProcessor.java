package com.frost.webworm.webmagic;

import com.frost.webworm.webmagic.entity.ReleasePublicEntity;
import com.frost.webworm.webmagic.mapper.ReleasePublicMapper;
import com.frost.webworm.webmagic.service.ReleasePublicService;
import com.frost.webworm.webmagic.service.impl.ReleasePublicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.PostConstruct;

@Component
public class GithubRepoPageProcessor implements PageProcessor {

    public final String listUrl = "\\?tm_id=7&node_id=&site_id=CMSxy&catalog_id=72&gk_index=&title=&cur_page=\\d+";
    public final String cssUrl = "div.pageing span";

    private Site site = Site.me().setDomain("www.snxunyi.gov.cn").setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 列表页
        if (page.getUrl().regex(listUrl).match()){
            page.addTargetRequests(page.getHtml().links().regex("/gk/zcfg/flfg/\\w+\\.htm").all());
            page.addTargetRequests(page.getHtml().css(cssUrl).links().regex(listUrl).all());
        } else {
            ReleasePublicEntity releasePublicEntity = new ReleasePublicEntity();
            releasePublicEntity.setPublicUrl(page.getUrl().toString());
            releasePublicEntity.setPublicIndex(page.getHtml().css("table.gk-table tr:nth-child(1) td:nth-child(2)").xpath("//td/text()").toString());
            releasePublicEntity.setInputDept(page.getHtml().css("table.gk-table tr:nth-child(2) td:nth-child(2)").xpath("//td/text()").toString());
            releasePublicEntity.setPublicTitle(page.getHtml().xpath("//div[@class=\"art-title\"]/text()").toString());
            releasePublicEntity.setReleaseTime(page.getHtml().css("table.gk-table tr:nth-child(2) td:nth-child(4)").xpath("//td/text()").toString());
            releasePublicEntity.setDocNo(page.getHtml().css("table.gk-table tr:nth-child(3) td:nth-child(2)").xpath("//td/text()").toString());
            releasePublicEntity.setWebsite("旬邑县政府");
            releasePublicEntity.setPublicColumn("法律法规");
            System.out.println(releasePublicEntity);
            // page.putField("content", page.getHtml().xpath("//div[@class=\"art-con\"]/html()"));
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://www.snxunyi.gov.cn/info/iList.jsp?tm_id=7&node_id=&site_id=CMSxy&catalog_id=72&gk_index=&title=&cur_page=1").thread(1).run();
    }
}
