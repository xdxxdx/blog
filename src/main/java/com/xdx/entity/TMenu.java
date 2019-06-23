package com.xdx.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TMenu {
    private Integer menuId;

    private String menuName;

    private Integer menuType;

    private Integer menuLevel;

    private String menuIcon;

    private String menuSrc;

    private Integer rMenuId;

    private Integer pMenuId;

    private String menuIntro;

    private Integer priority1;

    private Integer priority2;

    private Integer priority3;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;
    private List<TMenu>child=new ArrayList<TMenu>();

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public String getMenuSrc() {
        return menuSrc;
    }

    public void setMenuSrc(String menuSrc) {
        this.menuSrc = menuSrc == null ? null : menuSrc.trim();
    }

    public Integer getrMenuId() {
        return rMenuId;
    }

    public void setrMenuId(Integer rMenuId) {
        this.rMenuId = rMenuId;
    }

    public Integer getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(Integer pMenuId) {
        this.pMenuId = pMenuId;
    }

    public String getMenuIntro() {
        return menuIntro;
    }

    public void setMenuIntro(String menuIntro) {
        this.menuIntro = menuIntro == null ? null : menuIntro.trim();
    }

    public Integer getPriority1() {
        return priority1;
    }

    public void setPriority1(Integer priority1) {
        this.priority1 = priority1;
    }

    public Integer getPriority2() {
        return priority2;
    }

    public void setPriority2(Integer priority2) {
        this.priority2 = priority2;
    }

    public Integer getPriority3() {
        return priority3;
    }

    public void setPriority3(Integer priority3) {
        this.priority3 = priority3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

	public List<TMenu> getChild() {
		return child;
	}

	public void setChild(List<TMenu> child) {
		this.child = child;
	}
    
}