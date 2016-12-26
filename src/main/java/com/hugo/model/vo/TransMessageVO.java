package com.hugo.model.vo;

/**
 * Created by ohj on 2016/12/14.
 */
public class TransMessageVO extends BaseVo {
    private String transMessageId;
    private Long userId;
    private Long inviteId;
    private String transContent;
    private String memo;//需求
    private String transResult;
    private Integer status;

    public String getTransMessageId() {
        return transMessageId;
    }

    public void setTransMessageId(String transMessageId) {
        this.transMessageId = transMessageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public String getTransContent() {
        return transContent;
    }

    public void setTransContent(String transContent) {
        this.transContent = transContent;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTransResult() {
        return transResult;
    }

    public void setTransResult(String transResult) {
        this.transResult = transResult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
