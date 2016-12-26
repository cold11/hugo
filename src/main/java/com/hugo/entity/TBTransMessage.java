package com.hugo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ohj on 2016/12/8.
 * 邀请试译通知
 */
@Entity
@Table(name = "tb_trans_message")
public class TBTransMessage extends BaseEntity{
    private String transMessageId;
    private SysUser sysUser;
    private SysUser inviteUser;//邀请人
    private String transContent;
    private String memo;//需求
    private String transResult;
    private Integer status;
    @Id
    @Column(name = "trans_message_id",unique = true, length = 36, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getTransMessageId() {
        return transMessageId;
    }

    public void setTransMessageId(String transMessageId) {
        this.transMessageId = transMessageId;
    }
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "invite_user_id", unique = true)
    public SysUser getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(SysUser inviteUser) {
        this.inviteUser = inviteUser;
    }
    @Column(name = "trans_content",columnDefinition = "Text")
    public String getTransContent() {
        return transContent;
    }
    public void setTransContent(String transContent) {
        this.transContent = transContent;
    }
    @Column(name = "memo",columnDefinition = "Text")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name = "trans_result",columnDefinition = "Text")
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
