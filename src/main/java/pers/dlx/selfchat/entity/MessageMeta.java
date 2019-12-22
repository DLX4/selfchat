package pers.dlx.selfchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 消息属性
 * </p>
 *
 * @author dinglingxiang
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessageMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键标识
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 消息标识
     */
    private Long messageId;

    /**
     * 属性key
     */
    private String metaKey;

    /**
     * 属性value
     */
    private String metaValue;


}
