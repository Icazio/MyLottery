package cn.icarus.lottery;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 * @date 2023/3/20 15:40
 */
@SpringBootApplication
@Configurable
@EnableDubbo
public class LotteryApplication {
    public static void main(String[] args) {

    }
}
