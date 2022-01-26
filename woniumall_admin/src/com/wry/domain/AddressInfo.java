package com.wry.domain;

import java.io.Serializable;

/**
 * 收货地址表
 */

public class AddressInfo implements Serializable {
    private Integer id;  //主键 自增

    private Integer userid; //用户编号  外键，来自用户表

    private String accept; //收货人

    private String telphone; //手机号码

    private String province; //省

    private String city; //市

    private String area; //区

    private String address; //详细地址

    private String type; // 是否默认  y:默认  n:非默认

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "AddressInfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", accept='" + accept + '\'' +
                ", telphone='" + telphone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}