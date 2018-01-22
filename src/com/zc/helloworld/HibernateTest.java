package com.zc.helloworld;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class HibernateTest {
	@Test
	public void test(){
		//1.创建一个SessionFactory对象
		SessionFactory sessionFactory=null;
		//1)创建Configuration对象,对应hibernate的基本配置信息和对应关系映射
		Configuration configuration=new Configuration().configure();
		
		//4.0 之前这样创建,直接获取sessionFactory
		//sessionFactory = configuration.buildSessionFactory();
		
		//4.0之后
		//2)创建一个ServiceRegistry对象,hibernate任何配置和服务都需要在该对象中注册后才能有效
		ServiceRegistry s=
		new ServiceRegistryBuilder().applySettings(configuration.getProperties())
		.buildServiceRegistry();
		
		//3)
		sessionFactory=configuration.buildSessionFactory(s);
		
		//2.创建一个Session对象
		Session session=sessionFactory.openSession(); 
		//3.开启事务
		Transaction transaction=session.beginTransaction();
		//4.执行保存操作
		News news=new News(1,"he","zcc",new Date());
		session.save(news);
		
		//5.提交事务
		transaction.commit();
		
		//6.关闭session
		session.close();
		//7.关闭SessionFactory对象
		sessionFactory.close();
	}
}
