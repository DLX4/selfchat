package pers.dlx.selfchat.controller.export;

/**
 * 导出大量数据到excel的过程监控
 *
 * @author dinglingxiang
 */
interface ExportMonitor {

    /**
     * 获取当前导出进度
     *
     * @return
     */
    ExportProgressVo getProgress();

    /**
     * 取消导出
     */
    void cancel();

    /**
     * 获取sessionId
     *
     * @return
     */
    String getSessionId();
}
