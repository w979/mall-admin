package com.wry.service.impl;

import com.wry.dao.CategoryDao;
import com.wry.domain.Category;
import com.wry.domain.CategoryExample;
import com.wry.service.ICategoryService;
import com.wry.utils.MybatisUtils;

import java.util.List;

/**
 * 商品类别业务类
 */
public class CategoryService implements ICategoryService{
    /**
     * 查询所有商品类别
     * @return
     * @throws Exception
     */
    @Override
    public List<Category> findCategory() throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        return categoryDao.selectByExample(new CategoryExample());
    }

    /**
     * 添加商品类别
     * @param category
     * @return
     * @throws Exception
     */
    @Override
    public int saveCategory(Category category) throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        return categoryDao.insert(category);
    }

    /**
     * 根据类别名称查询商品类别
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Category getCategoryByName(String name) throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        CategoryExample example = new CategoryExample();
        example.createCriteria().andNameEqualTo(name);
        List<Category> categoryList = categoryDao.selectByExample(example);
        if (categoryList.size() >0){
            return categoryList.get(0);
        }
        return null;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Category getCategoryById(Integer id) throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        return categoryDao.selectByPrimaryKey(id);
    }

    /**
     * 修改
     * @param category
     * @return
     * @throws Exception
     */
    @Override
    public int updateCategory(Category category) throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        return categoryDao.updateByPrimaryKey(category);
    }

    /**
     * 删除
     * @param category
     * @return
     * @throws Exception
     */
    @Override
    public int delCategory(Category category) throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        return categoryDao.updateByPrimaryKey(category);
    }

    /**
     *  根据商品状态查询商品类别
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public List<Category> getCategoryByStatus(Integer status) throws Exception {
        CategoryDao categoryDao = MybatisUtils.getDao(CategoryDao.class);
        return categoryDao.getCategoryByStatus(status);
    }
}
