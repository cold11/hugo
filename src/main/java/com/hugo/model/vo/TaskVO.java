package com.hugo.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by ohj on 2016/10/21.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskVO extends BaseVo {
    private String taskId;
    private String bookname;
    private String sourceLanguage;
    private String targetLanguage;
    private String author;
    private Integer words;
    private String publisher;
    private String authorIntroduction;
    private String bookIntroduction;
    private String bookFlIntroduction;//外文简介
    private String publishType;//出版类型
    private String eBookType;//电子书类型
    private Float eBookPrice;
    private String copyrightType;//版权类型
    private String copyrightDescript;//版权及翻译情况说明
    private String transContent;//试译内容
    private Date transExpirationDate;//试译截止日期
    private String coverPath;
    private String docPath;//试读word文档
    private Integer taskType;//任务类型
    private Integer taskStatus;
    private String transExpirationDateStr;//试译截止时间
    private SysUserVO user;
    public TaskVO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthorIntroduction() {
        return authorIntroduction;
    }

    public void setAuthorIntroduction(String authorIntroduction) {
        this.authorIntroduction = authorIntroduction;
    }

    public String getBookIntroduction() {
        return bookIntroduction;
    }

    public void setBookIntroduction(String bookIntroduction) {
        this.bookIntroduction = bookIntroduction;
    }

    public String getBookFlIntroduction() {
        return bookFlIntroduction;
    }

    public void setBookFlIntroduction(String bookFlIntroduction) {
        this.bookFlIntroduction = bookFlIntroduction;
    }

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public String geteBookType() {
        return eBookType;
    }

    public void seteBookType(String eBookType) {
        this.eBookType = eBookType;
    }

    public Float geteBookPrice() {
        return eBookPrice;
    }

    public void seteBookPrice(Float eBookPrice) {
        this.eBookPrice = eBookPrice;
    }

    public String getCopyrightType() {
        return copyrightType;
    }

    public void setCopyrightType(String copyrightType) {
        this.copyrightType = copyrightType;
    }

    public String getCopyrightDescript() {
        return copyrightDescript;
    }

    public void setCopyrightDescript(String copyrightDescript) {
        this.copyrightDescript = copyrightDescript;
    }

    public String getTransContent() {
        return transContent;
    }

    public void setTransContent(String transContent) {
        this.transContent = transContent;
    }

    public Date getTransExpirationDate() {
        return transExpirationDate;
    }

    public void setTransExpirationDate(Date transExpirationDate) {
        this.transExpirationDate = transExpirationDate;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTransExpirationDateStr() {
        return transExpirationDateStr;
    }

    public void setTransExpirationDateStr(String transExpirationDateStr) {
        this.transExpirationDateStr = transExpirationDateStr;
    }

    public SysUserVO getUser() {
        return user;
    }

    public void setUser(SysUserVO user) {
        this.user = user;
    }
}
