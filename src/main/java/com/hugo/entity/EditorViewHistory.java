package com.hugo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author ohj
 * @create 2017-12-04 13:46
 **/
@Entity
@Table(name = "editor_view_history")
public class EditorViewHistory extends BaseEntity {
    private String viewId;
    private SysUser sysUser;
    private TBTask tbTask;
    @Id
    @Column(name = "view_id",unique = true, length = 36, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
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
}
