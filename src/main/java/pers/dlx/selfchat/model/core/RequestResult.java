package pers.dlx.selfchat.model.core;

import lombok.Data;

/**
 * 请求结果
 *
 * @author dinglingxiang
 */
@Data
public class RequestResult {
    private boolean success;

    private String errorCode;

    private String errorMsg;

}
