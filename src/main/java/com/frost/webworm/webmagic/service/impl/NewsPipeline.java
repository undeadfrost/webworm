package com.frost.webworm.webmagic.service.impl;

import com.frost.webworm.webmagic.dao.PublicDao;
import com.frost.webworm.webmagic.entity.PublicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by LB on 2017/10/21.
 */
@Service
public class NewsPipeline implements Pipeline {
    @Autowired
    private PublicDao publicDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        PublicEntity publicEntity = resultItems.get("repo");
        if (publicEntity == null){
            return;
        }
        String publicUrl = publicEntity.getPublicUrl();
        if (publicDao.findByPublicUrl(publicUrl) == null){
            publicDao.save(publicEntity);
        }
    }
}
