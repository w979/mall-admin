package com.wry.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 通过cglib动态代理，自动处理事务
 * 检测service类中的方法有没有出现异常，有异常回滚，没有异常提交
 * @author Administrator
 *
 */
public class ServiceProxyFactory {
	/**
	 * 动态代理方法
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getProxy(Class<T> clazz) {
		// Enhancer类是CGLIB中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);// 将传进来的Service类设为父类

		enhancer.setCallback(new MethodInterceptor() {
			// 调用代理对象的方法时，会执行此方法
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				// 获取目标类的方法
				Object result = null;
				try {
					result = methodProxy.invokeSuper(proxy, args);
				} catch (Exception e) {
					// 回滚事务
					MybatisUtils.rollback();
					System.out.println("回滚事务...");
					// 向上层抛异常
					throw e;
				} finally {
					//释放资源
					MybatisUtils.closeSqlSession();
					System.out.println("提交事务，并释放资源...");
				}
				return result;
			}
		});
		return (T) enhancer.create();
	}
}
