package com.wry.service.impl;

import com.wry.dao.GoodsDao;
import com.wry.domain.Goods;
import com.wry.domain.GoodsExample;
import com.wry.service.IGoodsService;
import com.wry.utils.MybatisUtils;

import java.util.List;

/**
 * 商品业务类
 */
public class GoodsService implements IGoodsService {

    /**
     * 多条件查询商品列表
     * @param goodsName
     * @param categoryid
     * @param status
     * @param startstock
     * @param endstock
     * @return
     */
    @Override
    public List<Goods> getGoods(String goodsName, Integer categoryid, Integer status,
                                Integer startstock, Integer endstock) throws Exception{
        GoodsDao goodsDao = MybatisUtils.getDao(GoodsDao.class);
        return goodsDao.getGoods(goodsName, categoryid, status, startstock, endstock);
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @Override
    public int saveGoods(Goods goods) throws Exception{
        GoodsDao goodsDao = MybatisUtils.getDao(GoodsDao.class);
        return goodsDao.insert(goods);
    }

    /**
     * 根据id查询商品
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Goods getGoodById(Integer id) throws Exception {
        GoodsDao goodsDao = MybatisUtils.getDao(GoodsDao.class);
        return goodsDao.selectByPrimaryKey(id);
    }

    /**
     * 修改商品
     * @param goods
     * @return
     * @throws Exception
     */
    @Override
    public int UpdateGoods(Goods goods) throws Exception {
        GoodsDao goodsDao = MybatisUtils.getDao(GoodsDao.class);
        return goodsDao.updateByPrimaryKey(goods);
    }

    /**
     * 批量下架商品
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public int downGoods(List<String> ids) throws Exception {
        GoodsDao goodsDao = MybatisUtils.getDao(GoodsDao.class);
        return goodsDao.downGoods(ids);
    }


}
