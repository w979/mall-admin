package com.wry.service;

import com.wry.domain.AdminInfo;

import java.util.List;

public interface IAdminService {
    //管理员登录验证
    public AdminInfo getLogin(String account,String password,String ip) throws Exception;

    //查询所有管理员信息
    public List<AdminInfo> findAdmin();

    //根据账号查询
    public AdminInfo getAdminByAccount(String account);

    //添加管理员
    public int saveAdmin(AdminInfo adminInfo);

    //根据id删除管理员
    public int delAdminById(AdminInfo adminInfo) throws  Exception;

    //修改管理员信息
    public int updateAdmin(AdminInfo adminInfo) throws Exception;

    //根据id修改密码
    int updatePwd(Integer id,String pwd);
}
