package com.codeway.base;

import com.codeway.utils.DateUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@EntityScan("com.codeway.model.pojo")
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories("com.codeway.base.dao")
@EnableFeignClients(basePackages = "com.codeway.api")
// SpringBoot主配置类只会扫描自己所在的包及其子包下面,如果不加此扫描codeway-common里的公用包则扫描不到
@ComponentScan(basePackages = {"com.codeway"})
public class BaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

	/**
	 * 配置querydsl
	 *
	 * @see https://www.cnblogs.com/jpfss/p/11003964.html
	 */
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}

}
