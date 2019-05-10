package cn.cloudwalk;

import cn.cloudwalk.model.Account;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMethodCache(basePackages = {"cn.cloudwalk"})
@EnableCreateCacheAnnotation
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @CreateCache()
    public Cache<String, Account> cache;


}
