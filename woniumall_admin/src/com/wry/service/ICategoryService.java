package com.wry.service;

import com.wry.domain.Category;

import java.util.List;

public interface ICategoryService {
    //查询所有商品类别
    public List<Category> findCategory() throws Exception;

    //添加商品类别
    public int saveCategory(Category category) throws Exception;

    //根据类别名称查询商品类别
    public Category getCategoryByName(String name) throws Exception;

    //根据id查询
    public Category getCategoryById(Integer id) throws Exception;

    //修改商品信息
    public int updateCategory(Category category) throws Exception;

    //删除
    public int delCategory(Category category) throws Exception;

    // 根据商品状态查询商品类别
    public List<Category> getCategoryByStatus(Integer status) throws Exception;
}
