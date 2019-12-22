package pers.dlx.selfchat.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.dlx.selfchat.entity.Topic;
import pers.dlx.selfchat.mapper.TopicMapper;
import pers.dlx.selfchat.model.core.Response;
import pers.dlx.selfchat.model.request.TopicSaveInfo;
import pers.dlx.selfchat.utils.BoolUtil;
import pers.dlx.selfchat.utils.DateUtils;
import pers.dlx.selfchat.utils.SnowflakeIdWorker;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 主题
 */

@Controller
@Api(tags = "主题相关接口")
public class TopicController {

    @Resource
    private TopicMapper mapper;

    /**
     * 保存/修改主题信息
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "保存/修改主题信息", notes = "")
    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    public Response saveTopic(@ApiParam(value = "主题信息", name = "info", required = true) @RequestBody TopicSaveInfo info) {
        Long topicId = info.getId();
        // 插入一个新的主题
        if (topicId == null) {
            Topic topic = new Topic();
            topic.setId(SnowflakeIdWorker.getNextId());
            topic.setName(info.getName());
            topic.setCreateTime(DateUtils.dateToLocalDateTime(new Date()));
            topic.setIsDeleted(BoolUtil.FALSE_INT);
            topic.setUpdateTime(DateUtils.dateToLocalDateTime(new Date()));
            mapper.insert(topic);
        }
        // 更新已经创建的主题
        else {
            Topic topic = mapper.selectById(topicId);
            topic.setName(info.getName());
            topic.setUpdateTime(DateUtils.dateToLocalDateTime(new Date()));
            mapper.updateById(topic);
        }
        return new Response();
    }

    /**
     * 查询主题信息
     *
     * @return
     */
    @ApiOperation(value = "查询全部主题信息", notes = "")
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public Response queryTopic() {
        Response response = new Response();
        response.setContent(mapper.selectList(Wrappers.<Topic>lambdaQuery().eq(Topic::getIsDeleted, BoolUtil.FALSE_INT).orderByDesc(Topic::getLastMsgTime)));
        return response;
    }

    /**
     * 删除主题信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除主题信息", notes = "")
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
    public Response deleteTopic(@PathVariable Long id) {
        mapper.deleteById(id);
        return new Response();
    }

}
