package pers.dlx.selfchat.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import pers.dlx.selfchat.entity.Message;
import pers.dlx.selfchat.mapper.MessageMapper;
import pers.dlx.selfchat.model.core.Response;
import pers.dlx.selfchat.model.request.MessageReqVo;
import pers.dlx.selfchat.model.response.MessageRspVo;
import pers.dlx.selfchat.utils.BoolUtil;
import pers.dlx.selfchat.utils.DateUtils;
import pers.dlx.selfchat.utils.SnowflakeIdWorker;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 消息
 */

@RestController
@Api(tags = "消息相关接口")
public class MessageController {

    @Resource
    private MessageMapper mapper;

    /**
     * 保存/修改消息信息
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "保存/修改消息信息", notes = "")
    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Response saveMessage(@ApiParam(value = "消息信息", name = "info", required = true) @RequestBody MessageReqVo info) {
        // 插入一个新的消息
        Message message = new Message();
        message.setId(SnowflakeIdWorker.getNextId());
        message.setContent(info.getContent());
        message.setCreateTime(DateUtils.dateToLocalDateTime(new Date()));
        message.setIsDeleted(BoolUtil.FALSE_INT);
        mapper.insert(message);

        return new Response();
    }

    /**
     * 查询某个主题下的消息列表
     *
     * @return
     */
    @ApiOperation(value = "查询全部消息信息", notes = "")
    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public Response queryMessage(@PathVariable Long id) {
        Response response = new Response();
        List<MessageRspVo> messages = Lists.newArrayList();
        mapper.selectList(Wrappers.<Message>lambdaQuery().eq(Message::getIsDeleted, BoolUtil.FALSE_INT).eq(Message::getTopicId, id).orderByDesc(Message::getCreateTime))
                .forEach(message -> messages.add(new MessageRspVo(message)));
        response.setContent(messages);
        return response;
    }

    /**
     * 删除消息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除消息信息", notes = "")
    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public Response deleteMessage(@PathVariable Long id) {
        mapper.deleteById(id);
        return new Response();
    }

}
