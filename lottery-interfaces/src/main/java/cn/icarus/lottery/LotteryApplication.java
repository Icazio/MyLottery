package cn.icarus.lottery;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Icarus
 * @description
 * @date 2023/3/20 15:40
 */
@SpringBootApplication
@Configurable
@EnableDubbo
public class LotteryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotteryApplication.class,args);
    }
}
/**
 * @SpringBootApplication
 * 该注解是Spring Boot的核心注解，用于声明该类是一个Spring Boot应用程序的入口类。它包含了@Configuration、@EnableAutoConfiguration和@ComponentScan注解的组合，可以自动配置Spring Boot应用程序所需的各种组件。
 *
 * @Configurable
 * 该注解表示这个类是可配置的，可以被Spring容器托管，并通过Spring容器的依赖注入功能来实现各个Bean之间的协作。
 *
 * @EnableDubbo
 * 该注解是Dubbo分布式服务框架的核心注解，用于启用Dubbo框架的功能。通过这个注解启用Dubbo框架的功能
 */