package pers.dlx.selfchat.listenner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 监听springboot初始化完成事件
 *
 * @author dinglingxiang
 */
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) { // root application context 没有parent
            logger.info("----");
        }
    }

}
