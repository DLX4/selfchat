package pers.dlx.selfchat.model.response;

import lombok.Data;
import pers.dlx.selfchat.entity.Topic;
import pers.dlx.selfchat.utils.DateUtils;

/**
 * 话题信息（返回给前端）
 */
@Data
public class TopicRspVo {

    /**
     * 主题标识
     */
    private Long topicId;

    /**
     * 主题名称
     */
    private String name;

    /**
     * 最后一条消息内容
     */
    private String lastMsgContent;

    /**
     * 最后一条消息时间
     */
    private Long lastMsgTime;

    public TopicRspVo(Topic topicEntity) {
        this.topicId = topicEntity.getId();
        this.name = topicEntity.getName();
        this.lastMsgContent = topicEntity.getLastMsgContent();
        this.lastMsgTime = DateUtils.localDateTimeToUnixTime(topicEntity.getLastMsgTime());
    }
}
