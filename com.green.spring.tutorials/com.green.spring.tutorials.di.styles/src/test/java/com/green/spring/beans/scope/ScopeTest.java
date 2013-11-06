package com.green.spring.beans.scope;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:annotation.xml"})
public class ScopeTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void itShouldReturnNewInstanceOfPrototypeEverytime() throws Exception {
		PrototypeBean pb1 = applicationContext.getBean(PrototypeBean.class);
		PrototypeBean pb2 = applicationContext.getBean(PrototypeBean.class);
		
		Assert.assertNotSame(pb1, pb2);
	}
	
	@Test
	public void itShouldReturnSameInstanceOfSingletonEverytime() throws Exception {
		SingletonBean s1 = applicationContext.getBean(SingletonBean.class);
		SingletonBean s2 = applicationContext.getBean(SingletonBean.class);
		
		Assert.assertSame(s1, s2);
	}
}
