package com.frost.webworm.webmagic.controller;

import com.frost.webworm.webmagic.service.impl.NewsPipeline;
import com.frost.webworm.webmagic.service.impl.XydqwRepoPageProcessor;
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
        Spider.create(
                new XydqwRepoPageProcessor())
                .addUrl("http://www.xydqw.com/sjzlk/sjxysz/index.htm")
                .addPipeline(newsPipeline)
                .thread(1)
                .run();
        return "success";
    }
}
