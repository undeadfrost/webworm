package com.frost.webworm.webmagic.service.impl;


import com.frost.webworm.webmagic.entity.ReleasePublicEntity;
import com.frost.webworm.webmagic.mapper.ReleasePublicMapper;
import com.frost.webworm.webmagic.service.ReleasePublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleasePublicServiceImpl implements ReleasePublicService{

    @Autowired
    private ReleasePublicMapper releasePublicMapper;

    @Override
    public void insertList(ReleasePublicEntity releasePublicEntity) {
        releasePublicMapper.insertList(releasePublicEntity);
    }
}
