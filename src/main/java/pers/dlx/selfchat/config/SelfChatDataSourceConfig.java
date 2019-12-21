package pers.dlx.selfchat.config;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.management.ManagementService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.management.MBeanServer;
import javax.sql.DataSource;
import java.lang.management.ManagementFactory;

@Configuration
public class SelfChatDataSourceConfig {

    /**
     * selfchat业务数据库-mysql
     *
     * @return
     */
    @Bean(name = "selfchatDataSource")
    @Qualifier("selfchatDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.selfchat")
    public DataSource selfchatDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 配置mybatis mapper的扫描路径
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(selfchatDataSource());

        // 配置mapper的扫描
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        factoryBean.setMapperLocations(resources);

        factoryBean.setVfs(SpringBootVFS.class); // Sets the SpringBootVFS class into SqlSessionFactoryBean
        // ...
        return factoryBean.getObject();
    }

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