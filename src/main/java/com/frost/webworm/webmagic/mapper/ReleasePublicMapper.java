package com.frost.webworm.webmagic.mapper;

import com.frost.webworm.webmagic.entity.ReleasePublicEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;


/**
 * Created by LB on 2017/10/19.
 */
public interface ReleasePublicMapper {

    @Insert("insert into release_public ('website', 'public_index', 'input_dept', 'public_title', 'release_time', 'public_url', 'doc_no', 'public_column'}) values (#{website}, #{publicIndex}, #{inputDept}, #{publicTitle}, #{releaseTime}, #{publicUrl}, #{docNo}, #{publicColumn})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertList(ReleasePublicEntity releasePublicEntity);
}
