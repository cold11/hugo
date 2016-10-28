package com.hugo.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * Created by ohj on 2016/10/4.
 * 用户
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserVO extends BaseUserVO {
    private String password;
    private Integer translatorType;//译员类型
    private Integer releaseRole;//发布人角色
    private String postscript;//补充说明
    private String releaseAgency;//所在出版机构
    private String releaseCompany;//公司名称
    private String personalIntro;//个人简介
    private String language;//擅长语种
    private String translationType;//擅长翻译类型
    private Integer translatorCount;//译员数

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTranslatorType() {
        return translatorType;
    }

    public void setTranslatorType(Integer translatorType) {
        this.translatorType = translatorType;
    }

    public Integer getReleaseRole() {
        return releaseRole;
    }

    public void setReleaseRole(Integer releaseRole) {
        this.releaseRole = releaseRole;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getReleaseAgency() {
        return releaseAgency;
    }

    public void setReleaseAgency(String releaseAgency) {
        this.releaseAgency = releaseAgency;
    }

    public String getReleaseCompany() {
        return releaseCompany;
    }

    public void setReleaseCompany(String releaseCompany) {
        this.releaseCompany = releaseCompany;
    }

    public String getPersonalIntro() {
        return personalIntro;
    }

    public void setPersonalIntro(String personalIntro) {
        this.personalIntro = personalIntro;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTranslationType() {
        return translationType;
    }

    public void setTranslationType(String translationType) {
        this.translationType = translationType;
    }

    public Integer getTranslatorCount() {
        return translatorCount;
    }

    public void setTranslatorCount(Integer translatorCount) {
        this.translatorCount = translatorCount;
    }

    @Override
    public String toString() {
        return "SysUserVO{" +
                "username='" + username + '\'' +
                ",password='" + password + '\'' +
                ", translatorType=" + translatorType +
                ", releaseRole=" + releaseRole +
                ", postscript='" + postscript + '\'' +
                ", releaseAgency='" + releaseAgency + '\'' +
                ", releaseCompany='" + releaseCompany + '\'' +
                ", personalIntro='" + personalIntro + '\'' +
                ", language='" + language + '\'' +
                ", translationType='" + translationType + '\'' +
                ", translatorCount=" + translatorCount +
                '}';
    }
}
