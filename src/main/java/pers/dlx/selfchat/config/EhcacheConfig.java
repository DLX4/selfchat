package pers.dlx.selfchat.config;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.management.ManagementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

@Configuration
public class EhcacheConfig {

    /**
     * ehcache配置
     *
     * @return
     * @throws CacheException
     */
    @Bean
    public CacheManager ehcacheManager() throws CacheException {
        net.sf.ehcache.config.Configuration cacheManagerConfig = new net.sf.ehcache.config.Configuration()
                .diskStore(new DiskStoreConfiguration().path("java.io.tmpdir"));
        // 110字典缓存，24小时过期
        CacheConfiguration cacheConfig = new CacheConfiguration().name("DictCache")
                .maxBytesLocalHeap(16, MemoryUnit.MEGABYTES).timeToLiveSeconds(3600 * 24)
                .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP));
        cacheManagerConfig.addCache(cacheConfig);
        CacheManager cacheManager = new CacheManager(cacheManagerConfig);

        /**
         * 添加监控，可以使用jconsole工具实时查看缓存的使用信息
         */
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService.registerMBeans(cacheManager, mBeanServer, true, true, true, true);

        return cacheManager;
    }
}