package pers.dlx.selfchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 主题
 * </p>
 *
 * @author dinglingxiang
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主题标识
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 主题名称
     */
    private String name;

    /**
     * 主题创建时间
     */
    private LocalDateTime createTime;

    /**
     * 数据更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否被删除
     */
    private Integer isDeleted;

    /**
     * 最后一条消息内容
     */
    private String lastMsgContent;

    /**
     * 最后一条消息时间
     */
    private LocalDateTime lastMsgTime;


}
