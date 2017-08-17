package me.inspiringbits.cleanscene;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IvanJian on 2017/8/9.
 */
@SpringBootApplication
//@MapperScan(basePackages={"me.inspiringbits.cleanscene.Mapper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
