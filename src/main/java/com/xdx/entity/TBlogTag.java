package com.xdx.entity;

import java.util.Date;

public class TBlogTag {
    private Integer blogTagId;

    private Integer blogId;

    private Integer tagId;

    private Integer num;

    private Date createTime;

    private Integer isDel;

    public Integer getBlogTagId() {
        return blogTagId;
    }

    public void setBlogTagId(Integer blogTagId) {
        this.blogTagId = blogTagId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}