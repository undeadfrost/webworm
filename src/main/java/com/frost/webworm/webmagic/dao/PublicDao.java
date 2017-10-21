package com.frost.webworm.webmagic.dao;

import com.frost.webworm.webmagic.entity.PublicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by LB on 2017/10/21.
 */
public interface PublicDao extends JpaRepository<PublicEntity,Integer> {
    PublicEntity findByPublicUrl(String publicUrl);
}
