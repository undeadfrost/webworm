package com.frost.webworm.webmagic.controller;

import com.frost.webworm.webmagic.service.impl.NewsPipeline;
import com.frost.webworm.webmagic.service.impl.XunyiRepoPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

/**
 * Created by LB on 2017/10/21.
 */
@RestController
public class XunyiPublicController {
    @Autowired
    private NewsPipeline newsPipeline;
    @RequestMapping("/start")
    public String start(){
        Spider.create(new XunyiRepoPageProcessor()).addUrl("http://www.snxunyi.gov.cn/info/iList.jsp?tm_id=7&node_id=&site_id=CMSxy&catalog_id=72&gk_index=&title=&cur_page=1").addPipeline(newsPipeline).thread(1).run();
        return "success";
    }
}
