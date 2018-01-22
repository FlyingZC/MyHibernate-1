package com.zc.helloworld;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class HibernateTest2 {
	@Test
	public void test(){
		//1.创建一个SessionFactory对象
		SessionFactory sessionFactory=null;
		//(1)创建一个Configuration对象:对应hibernate的 基本配置信息 和对 象关系映射信息
		/*源码关联的就是cfg.xml
		 * public Configuration configure() throws HibernateException {
				configure( "/hibernate.cfg.xml" );
				return this;
			}
		 * */
		Configuration configuration=new Configuration().configure();
		//(2)4.0之前这样创建sessionFactory
		//sessionFactory=configuration.buildSessionFactory();
		
		//(2)创建一个ServiceRegistry对象:hibernate4.x新添加的对象
		//hibernate的任何配置和服务都需要该对象中注册后才能生效
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).buildServiceRegistry();

		//(3)创建sessionFactory对象
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		//2.创建一个Session对象
		Session session=sessionFactory.openSession();
		
		//3.开启事务
		Transaction transaction=session.beginTransaction();
		
		//4.执行保存操作
		News news=new News("zccx","zxx",new Date(new java.util.Date().getTime()));
		//调用session的save方法保存
		session.save(news);
		//执行查询操作,查询一个news对象,且在数据库中id为1
		//News news2=(News) session.get(News.class,1);
	//	System.out.println(news2);
		
		
		//5.提交事务
		transaction.commit();
		//6.关闭Session
		session.close();
		//7.关闭SessionFactory对象
		sessionFactory.close();
	}
}
