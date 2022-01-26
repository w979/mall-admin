package com.wry.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybaits工具类
 * 
 * @author Administrator
 *
 */
public class MybatisUtils {
	private static SqlSessionFactory  factory=null;
	private static ThreadLocal<SqlSession> local=new ThreadLocal<SqlSession>();
	
	static {
		try {
			// 读取mybaits核心配置文件
			InputStream input = Resources.getResourceAsStream("mybatis-config.xml");
			//获得SqlSessionFactory工厂
			factory=new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得SqlSession对象
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession sqlSession=local.get();
		if(sqlSession==null) {
			sqlSession=factory.openSession();
			//添加到ThreadLocal
			local.set(sqlSession);
		}
		return sqlSession;
	}
	
	 /**
     * 回滚事务
     */
    public static void rollback(){
    	SqlSession sqlSession=local.get();
		if(sqlSession!=null) {
        	sqlSession.rollback();
        }
    }
	
	/**
	 * 释放SqlSession并提交事务
	 */
	public static void closeSqlSession() {
		SqlSession sqlSession=local.get();
		if(sqlSession!=null) {
			//提交事务
			sqlSession.commit();
			//释放资源
			sqlSession.close();
			//从线程中移除
			local.remove();
		}
	}
	
	/**
	 * 获取动态代理Dao接口对象
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T> T getDao(Class<T> c) {
		return getSqlSession().getMapper(c);
	}
}
