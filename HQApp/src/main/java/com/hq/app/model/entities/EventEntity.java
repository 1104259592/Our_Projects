package com.hq.app.model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @Class: EventEntity
 * @Description:
 * @author: zhangqi
 * @Date: 2020/4/20
 */
public class EventEntity implements Serializable {
    private long id;
    private long  category_id;
    private String title;
    private String sub_title;
    private String title_desc;
    private String url;
    private String pic;
    private String pic2;
    private String content	 ;
    private Date created;
    private Date updated;

    public EventEntity(String title, String pic, String content) {
        this.title = title;
        this.pic = pic;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getTitle_desc() {
        return title_desc;
    }

    public void setTitle_desc(String title_desc) {
        this.title_desc = title_desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", title='" + title + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", title_desc='" + title_desc + '\'' +
                ", url='" + url + '\'' +
                ", pic='" + pic + '\'' +
                ", pic2='" + pic2 + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
