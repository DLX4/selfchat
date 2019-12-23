package pers.dlx.selfchat.model.response;

import lombok.Data;
import pers.dlx.selfchat.constants.MessageType;
import pers.dlx.selfchat.entity.Message;
import pers.dlx.selfchat.utils.DateUtils;

/**
 * 消息（返回给前端）
 */
@Data
public class MessageRspVo {

    private Long topicId;

    private Long msgId;

    private String content;

    private String msgType;

    private String msgName;

    private String picUrl;

    private String srcUrl;

    private Long createTime;

    public MessageRspVo(Message messageEntity) {
        this.topicId = messageEntity.getTopicId();
        this.msgId = messageEntity.getId();
        this.content = messageEntity.getContent();
        this.msgType = messageEntity.getType();
        this.msgName = MessageType.valueOf(messageEntity.getType()).name;
        switch (MessageType.valueOf(messageEntity.getType())) {
            case IMAGE:
                this.picUrl = messageEntity.getContent();
                break;
            case TEXT:
                break;
        }

        this.createTime = DateUtils.localDateTimeToUnixTime(messageEntity.getCreateTime());
    }
}
