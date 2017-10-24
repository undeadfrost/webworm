package com.frost.webworm.webmagic.entity;


import javax.persistence.*;

/**
 * Created by LB on 2017/10/24.
 */
@Entity
@Table(name = "dqw_fzxk")
public class XydqwEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String source;
    private String author;
    private String time;
    private String content;
    private String colum;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getColum() {
        return colum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setColum(String colum) {
        this.colum = colum;
    }
}