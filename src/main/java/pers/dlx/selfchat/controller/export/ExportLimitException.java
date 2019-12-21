package pers.dlx.selfchat.controller.export;

/**
 * 受检查异常-超过最大导出并发数
 *
 * @author dinglingxiang
 */
public class ExportLimitException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExportLimitException(String message) {
        super(message);
    }
}
