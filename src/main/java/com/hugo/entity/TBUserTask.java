package com.hugo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ohj on 2016/11/11.
 */
@Entity
@Table(name = "tb_user_task")
public class TBUserTask extends BaseEntity {
    private String userTaskId;
    private SysUser sysUser;
    private TBTask tbTask;
    private String trans;
    private Integer status;
    @Id
    @Column(name = "user_task_id",unique = true, length = 36, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(String userTaskId) {
        this.userTaskId = userTaskId;
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
    @JoinColumn(name = "task_id", unique = true)
    public TBTask getTbTask() {
        return tbTask;
    }

    public void setTbTask(TBTask tbTask) {
        this.tbTask = tbTask;
    }
    @Column(name = "trans",columnDefinition = "Text")
    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
