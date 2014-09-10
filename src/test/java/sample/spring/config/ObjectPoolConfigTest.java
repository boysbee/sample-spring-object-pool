package sample.spring.config;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.spring.bean.SimpleBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ObjectPoolConfig.class })
public class ObjectPoolConfigTest {
	@Autowired
	ObjectPoolConfig objectPoolConfig;
	@Autowired
	SimpleBean simpleBean;

	@Test
	public void it_should_say() {
		String message = simpleBean.say();
		assert (message.equals("Hi"));
	}
	
	@Test
	public void it_shold_call_from_pool() throws Exception{
		
		for (int i = 0; i < 30; i++) {
			SimpleBean s = (SimpleBean) objectPoolConfig.commonPoolTargetSource().getTarget();
			System.out.println("!! time: " + new Date() + " , " + s.say());
			objectPoolConfig.commonPoolTargetSource().releaseTarget(s);
		}
	}
}
