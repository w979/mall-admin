package com.wry.service.impl;

import com.ndktools.javamd5.Mademd5;
import com.wry.dao.AdminInfoDao;
import com.wry.domain.AdminInfo;
import com.wry.domain.AdminInfoExample;
import com.wry.service.IAdminService;
import com.wry.utils.MybatisUtils;

import java.util.Date;
import java.util.List;
/**
 * 管理员业务逻辑层
 */
public class AdminService implements IAdminService {
    /**
     * 管理员登录验证
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public AdminInfo getLogin(String account, String password,String ip) throws Exception {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        //创建AdminInfoExample
        AdminInfoExample example = new AdminInfoExample();
        //添加条件
        example.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(new Mademd5().toMd5(password));
        //调用查询方法
        List<AdminInfo> adminInfoList = adminInfoDao.selectByExample(example);
        if (adminInfoList.size() > 0){
            AdminInfo adminInfo = adminInfoList.get(0);
            //更新最后登录时间和ip
            adminInfo.setLastlogintime(new Date());
            adminInfo.setLastloginip(ip);
            adminInfoDao.updateByPrimaryKey(adminInfo);
            return adminInfo;
        }
        return null;
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    @Override
    public List<AdminInfo> findAdmin() {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        return adminInfoDao.selectByExample(new AdminInfoExample());
    }

    /**
     * 根据账号查询
     * @param account
     * @return
     */
    @Override
    public AdminInfo getAdminByAccount(String account) {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        AdminInfoExample example = new AdminInfoExample();
        example.createCriteria().andAccountEqualTo(account);
        List<AdminInfo> adminInfoList = adminInfoDao.selectByExample(example);
        if (adminInfoList.size()>0){
            return  adminInfoList.get(0);
        }
        return null;
    }

    /**
     * 添加管理员
     * @param adminInfo
     * @return
     */
    @Override
    public int saveAdmin(AdminInfo adminInfo) {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        return adminInfoDao.insert(adminInfo);
    }

    /**
     * 根据id设置状态
     * @return
     * @throws Exception
     */
    @Override
    public int delAdminById(AdminInfo adminInfo) throws Exception {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        return adminInfoDao.updateByPrimaryKey(adminInfo);
    }

    /**
     * 修改管理员信息
     * @param adminInfo
     * @return
     * @throws Exception
     */
    @Override
    public int updateAdmin(AdminInfo adminInfo) throws Exception {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        return adminInfoDao.updateByPrimaryKey(adminInfo);
    }

    /**
     * 修改密码
     * @param id
     * @param pwd
     * @return
     */
    @Override
    public int updatePwd(Integer id, String pwd) {
        AdminInfoDao adminInfoDao = MybatisUtils.getDao(AdminInfoDao.class);
        return adminInfoDao.updatePwd(id, pwd);
    }
}
