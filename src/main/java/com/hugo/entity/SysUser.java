package com.hugo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysUser entity.
 *
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

	// Fields

	private Long userId;
	private String username;
	private String email;
	private String phone;
	private String password;
	private String name;
	private Integer isActivate;
	private Integer isCheck;
	private Integer translatorType;//译员类型
	private Integer releaseRole;//发布人角色
	private String postscript;//补充说明
	private String releaseAgency;//所在出版机构
	private String releaseCompany;//公司名称
	private String personalIntro;//个人简介
	private String language;//擅长语种
	private String translationType;//擅长翻译类型
	private Integer translatorCount;//译员数
	private String filePath;
    private Long createId;
    private Long modifyId;
	private Date createTime;
	private Date modifyTime;
	private Integer isDisable;
	private Date disableDate;
	private Integer isDelete;
	
	private String signUpCode;
	private String loginIp;
	private Date loginDate;
    private Set<SysUserRole> sysUserRoles = new HashSet<>();
    private Set<TBTask> tbTasks = new HashSet<>();

    private Set<TBUserTask> tbUserTasks = new HashSet<>();
	// Constructors

	/** default constructor */
	public SysUser() {
	}

    public SysUser(Long userId) {
        this.userId = userId;
    }

    // Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "username", nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "phone", nullable = false, length = 30)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "is_activate")
	public Integer getIsActivate() {
		return this.isActivate;
	}

	public void setIsActivate(Integer isActivate) {
		this.isActivate = isActivate;
	}

	@Column(name = "is_check")
	public Integer getIsCheck() {
		return this.isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	@Column(name = "translator_type")
	public Integer getTranslatorType() {
		return translatorType;
	}

	public void setTranslatorType(Integer translatorType) {
		this.translatorType = translatorType;
	}
	@Column(name = "release_role")
	public Integer getReleaseRole() {
		return releaseRole;
	}

	public void setReleaseRole(Integer releaseRole) {
		this.releaseRole = releaseRole;
	}
	@Column(name = "postscript",length = 500)
	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	@Column(name = "release_agency")
	public String getReleaseAgency() {
		return releaseAgency;
	}

	public void setReleaseAgency(String releaseAgency) {
		this.releaseAgency = releaseAgency;
	}

	@Column(name = "release_company")
	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}
	@Column(name = "personal_intro",columnDefinition = "text")
	public String getPersonalIntro() {
		return personalIntro;
	}

	public void setPersonalIntro(String personalIntro) {
		this.personalIntro = personalIntro;
	}
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	@Column(name = "translation_type")
	public String getTranslationType() {
		return translationType;
	}

	public void setTranslationType(String translationType) {
		this.translationType = translationType;
	}
	@Column(name = "translator_count")
	public Integer getTranslatorCount() {
		return translatorCount;
	}

	public void setTranslatorCount(Integer translatorCount) {
		this.translatorCount = translatorCount;
	}
	@Column(name = "file_path")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name = "create_id")
	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	@Column(name = "modify_id")
	public Long getModifyId() {
		return modifyId;
	}

	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "is_disable")
	public Integer getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}
	
	@Column(name = "disable_date")
	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name = "sign_up_code")
	public String getSignUpCode() {
		return signUpCode;
	}

	public void setSignUpCode(String signUpCode) {
		this.signUpCode = signUpCode;
	}

	@Column(name = "login_ip",length = 36)
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@Column(name = "login_date")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@JsonIgnore
	@OneToMany(mappedBy="sysUser",cascade=CascadeType.ALL,orphanRemoval = true)
	public Set<SysUserRole> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}
    @JsonIgnore
    @OneToMany(mappedBy="sysUser",cascade=CascadeType.ALL,orphanRemoval = true)
    public Set<TBTask> getTbTasks() {
        return tbTasks;
    }

    public void setTbTasks(Set<TBTask> tbTasks) {
        this.tbTasks = tbTasks;
    }
    @JsonIgnore
    @OneToMany(mappedBy="sysUser",cascade=CascadeType.ALL,orphanRemoval = true)
    public Set<TBUserTask> getTbUserTasks() {
        return tbUserTasks;
    }

    public void setTbUserTasks(Set<TBUserTask> tbUserTasks) {
        this.tbUserTasks = tbUserTasks;
    }
}