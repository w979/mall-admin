package com.wry.service;

import com.wry.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodsService {
    //多条件查询商品列表
    List<Goods> getGoods( String goodsName,Integer categoryid,Integer status,
                       Integer startstock, Integer endstock) throws Exception;
    //添加商品
    int saveGoods(Goods goods) throws Exception;

    //根据id查询商品
    Goods getGoodById(Integer id) throws Exception;

    //修改商品
    int UpdateGoods(Goods goods) throws Exception;

    //批量下架
    int downGoods(List<String> ids) throws Exception;
}
