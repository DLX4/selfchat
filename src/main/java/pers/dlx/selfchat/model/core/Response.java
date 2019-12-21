package pers.dlx.selfchat.model.core;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 处理客户端请求结果类。
 *
 * @author dinglingxiang
 * @version 1.0 Date 2014年5月12日
 */
@JsonPropertyOrder({"result", "content"})
public class Response {
    private Object content;

    private RequestResult requestResult;

    public Response() {
        requestResult = new RequestResult();
        requestResult.setSuccess(true);
    }

    /**
     * 返回处理结果内容
     *
     * @return 处理结果内容
     */
    @JsonProperty("content")
    public Object getContent() {
        return content;
    }

    /**
     * 设置处理结果内容
     *
     * @param content 处理结果内容
     */
    @JsonProperty("content")
    public void setContent(Object content) {
        this.content = content;
    }

    /**
     * 返回处理结果
     *
     * @return 处理结果
     */
    @JsonProperty("result")
    public RequestResult getRequestResult() {
        return requestResult;
    }

    /**
     * 设置处理结果
     *
     * @param requestResult 处理结果
     */
    @JsonProperty("result")
    public void setRequestResult(RequestResult requestResult) {
        this.requestResult = requestResult;
    }
}