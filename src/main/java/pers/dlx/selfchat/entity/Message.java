package pers.dlx.selfchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息
 * </p>
 *
 * @author dinglingxiang
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息标识
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 主题标识
     */
    private Long topicId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否被删除
     */
    private Integer isDeleted;


}
