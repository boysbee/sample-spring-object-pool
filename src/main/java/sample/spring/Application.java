package sample.spring;

import java.util.Date;

import org.springframework.aop.TargetSource;
import org.springframework.aop.target.CommonsPoolTargetSource;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import sample.spring.bean.SimpleBean;
import sample.spring.config.ObjectPoolConfig;

public class Application {
	public static void main(String[] args) {
		ApplicationContext ctx = new SpringApplicationBuilder(
				ObjectPoolConfig.class).run(args);
		SimpleBean s = (SimpleBean) ctx.getBean("simpleBean");
		System.out.printf("time : %s , Say from SimpleBean : %s\n",
				new Date().toString(), s.say());
		TargetSource t = ctx.getBean(CommonsPoolTargetSource.class);
		for (int i = 0; i < 100; i++) {
			SimpleBean s1 = null;
			try {
				s1 = (SimpleBean) t.getTarget();
				System.out.printf("time : %s , Say from ObjectPool : %s\n",
						new Date().toString(), s1.say());
				t.releaseTarget(s1);
			} catch (Exception e) {
				System.err.println(e);
			}

		}
	}
}
