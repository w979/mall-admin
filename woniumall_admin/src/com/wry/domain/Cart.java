package com.wry.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车表
 */
public class Cart implements Serializable {
    private Integer id;

    private Integer userid; //用户编号   外键，来自用户表

    private Integer goodsid;//商品编号   外键，来自商品表

    private BigDecimal price;//价格

    private Integer nums; //数量

    private Date addtime; //加入购物车时间

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userid=" + userid +
                ", goodsid=" + goodsid +
                ", price=" + price +
                ", nums=" + nums +
                ", addtime=" + addtime +
                '}'+"\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}