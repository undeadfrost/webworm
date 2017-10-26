package com.frost.webworm.webmagic.service.impl;

import com.frost.webworm.webmagic.dao.XydqwDao;
import com.frost.webworm.webmagic.entity.XydqwEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by LB on 2017/10/21.
 */
@Service
public class NewsPipeline implements Pipeline {
    @Autowired
    private XydqwDao xydqwDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            XydqwEntity xydqwEntity = resultItems.get("repo");
            xydqwDao.save(xydqwEntity);
        } catch ()
        XydqwEntity xydqwEntity = resultItems.get("repo");
        List<XydqwEntity> xydqwEntityList = resultItems.get("repo");
        if (xydqwEntityList.size() < 1){
            xydqwDao.save(xydqwEntity);
        } else {
            xydqwDao.save(xydqwEntityList);
        }
//        String publicUrl = publicEntity.getPublicUrl();
//        if (publicDao.findByPublicUrl(publicUrl) == null){
//            publicDao.save(publicEntity);
//        } else {
//
//        }
    }
}
