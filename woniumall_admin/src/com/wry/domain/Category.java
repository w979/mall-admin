package com.wry.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品类别表
 */
@Data
public class Category implements Serializable {
    private Integer id;

    private String name; //类别名  唯一，最短2位

    private Integer status;//状态  1:正常 2:无效

    private String navable;//是否作为导航  y:是. n:否

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", navable='" + navable + '\'' +
                '}'+"\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNavable() {
        return navable;
    }

    public void setNavable(String navable) {
        this.navable = navable == null ? null : navable.trim();
    }
}