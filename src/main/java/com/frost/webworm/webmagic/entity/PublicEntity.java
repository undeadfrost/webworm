package com.frost.webworm.webmagic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LB on 2017/10/19.
 */

@Entity
@Table(name = "release_public")
public class PublicEntity {
    //id
    @Id
    @GeneratedValue
    private Integer id;
    //站点标识
    private String website;
    //索引号
    private String publicIndex;
    //发布机构
    private String inputDept;
    //标题
    private String publicTitle;
    //发文日期
    private String releaseTime;
    //公开链接
    private String publicUrl;
    //文号
    private String docNo;
    //栏目名
    private String publicColumn;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setPublicIndex(String publicIndex) {
        this.publicIndex = publicIndex;
    }

    public void setInputDept(String inputDept) {
        this.inputDept = inputDept;
    }

    public void setPublicTitle(String publicTitle) {
        this.publicTitle = publicTitle;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public void setPublicColumn(String publicColumn) {
        this.publicColumn = publicColumn;
    }

    public Integer getId() {
        return id;
    }

    public String getWebsite() {
        return website;
    }

    public String getPublicIndex() {
        return publicIndex;
    }

    public String getInputDept() {
        return inputDept;
    }

    public String getPublicTitle() {
        return publicTitle;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public String getDocNo() {
        return docNo;
    }

    public String getPublicColumn() {
        return publicColumn;
    }
}
