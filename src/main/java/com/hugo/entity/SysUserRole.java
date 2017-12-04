package com.hugo.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysUserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_role")
public class SysUserRole implements java.io.Serializable {

	// Fields

	private Long userRoleId;
	private SysUser sysUser;
	private SysRole sysRole;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}


	// Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "user_role_id", unique = true, nullable = false)
	public Long getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

    //@ManyToOne(cascade=CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
    //@ManyToOne(cascade=CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", unique = true)
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
}