package com.wry.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 */
@Data
public class Orders implements Serializable {
    private Integer id;

    private String orderno; //订单编号  唯一，系统产生

    private Integer userid; //用户编号  外键，来自用户表的主键

    private Date ordertime; //下单时间  系统产生

    private String accept;  //收货人

    private String telphone;   //电话

    private String address; //收货地址

    private BigDecimal money; //总金额

    private Integer paytype; //支付方式  1：货到付款 2：支付宝支付

    private Date paytime; //支付时间

    private Date delivertime; //发货时间

    private Date receivetime; //收货时间

    private Integer status; //状态  1: 待付款  2:已付款 3: 已收货 4:已取消  5: 已关闭  6: 已完成

    private String isdel; //删除标识  y:已删除 n:未删除

    //用户对象属性
    private User user;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderno='" + orderno + '\'' +
                ", userid=" + userid +
                ", ordertime=" + ordertime +
                ", accept='" + accept + '\'' +
                ", telphone='" + telphone + '\'' +
                ", address='" + address + '\'' +
                ", money=" + money +
                ", paytype=" + paytype +
                ", paytime=" + paytime +
                ", delivertime=" + delivertime +
                ", receivetime=" + receivetime +
                ", status=" + status +
                ", isdel='" + isdel + '\'' +
                '}'+"\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept == null ? null : accept.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Date getDelivertime() {
        return delivertime;
    }

    public void setDelivertime(Date delivertime) {
        this.delivertime = delivertime;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }
}