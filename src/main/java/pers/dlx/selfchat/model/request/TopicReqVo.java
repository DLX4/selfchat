package pers.dlx.selfchat.model.request;

import lombok.Data;

@Data
public class TopicReqVo {

    /**
     * 主题名称
     */
    private String name;

    /**
     * 主题标识
     */
    private Long id;
}
