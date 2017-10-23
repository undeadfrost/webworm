package com.frost.webworm.webmagic.controller;

import com.frost.webworm.webmagic.service.impl.MailRepoPageProcessor;
import com.frost.webworm.webmagic.service.impl.NewsPipeline;
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
        Spider.create(new MailRepoPageProcessor()).addUrl("http://www.xys.gov.cn/appeal/list.jsp?model_id=4&dept_id=20&cur_page=1").thread(1).run();
        return "success";
    }
}
