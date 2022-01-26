package com.wry.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员表
 */
public class AdminInfo implements Serializable {
    private Integer id;

    private String account; //账号     唯一，最短5位

    private String password;// 密码 加密

    private Integer role; //角色  1：超级管理员  2：普通管理员

    private Date lastlogintime; //最后登录时间

    private String lastloginip; //最后登录IP

    private Integer status; //状态  1：正常  2：锁定

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "AdminInfo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", lastlogintime=" + lastlogintime +
                ", lastloginip='" + lastloginip + '\'' +
                ", status=" + status +
                '}'+"\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}