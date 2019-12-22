package pers.dlx.selfchat;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import pers.dlx.selfchat.entity.Topic;
import pers.dlx.selfchat.mapper.TopicMapper;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusTest {


    @Resource
    private TopicMapper topicMapper;

    @Test
    public void testQueryWrapper() {
        Page<Topic> page = new Page<>(1, 3);
        Page<Topic> result = topicMapper.selectPage(page, Wrappers.<Topic>lambdaQuery().eq(Topic::getId, 1));
        print(page.getRecords());
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }
}
