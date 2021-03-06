package pers.dlx.selfchat.model.request;

import lombok.Data;

/**
 * 消息（用户提交）
 */
@Data
public class MessageReqVo {


    /**
     * 主题标识
     */
    private Long topicId;

    /**
     * 主题内容
     */
    private String content;

    /**
     * 主题类型
     */
    private String type;

}
