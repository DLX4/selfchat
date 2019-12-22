package pers.dlx.selfchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
@ServletComponentScan
@MapperScan("pers.dlx.selfchat.mapper") // 此处填写 mapper接口所在包，如果不加则需要在每个 mapper上添加 @Mapper 注解。
public class SelfChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfChatApplication.class, args);
    }

}
