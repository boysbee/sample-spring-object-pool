package sample.spring.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPoolTargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import sample.spring.bean.SimpleBean;

@Configuration
public class ObjectPoolConfig {

	@Bean(name = "simpleBean")
	@Scope("prototype")
	public SimpleBean simpleBean() {
		return new SimpleBean();
	}

	@Bean
	public CommonsPoolTargetSource commonPoolTargetSource() {
		CommonsPoolTargetSource c = new CommonsPoolTargetSource();
		c.setMaxSize(20);
		c.setTargetClass(SimpleBean.class);
		c.setTargetBeanName("simpleBean");
		return c;
	}

	@Bean
	public ProxyFactoryBean proxyFactoryBean() {
		ProxyFactoryBean p = new ProxyFactoryBean();
		p.setTargetSource(commonPoolTargetSource());
		return p;
	}
}
