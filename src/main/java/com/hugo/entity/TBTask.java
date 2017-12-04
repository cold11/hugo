package com.hugo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ohj on 2016/10/20.
 */
@Entity
@Table(name = "tb_task")
public class TBTask extends BaseEntity {
    private String taskId;
    private String bookname;
    private String sourceLanguage;
    private String targetLanguage;
    private Integer words;
    private String publisher;//原出版商
    private String author;
    private String authorIntroduction;
    private String bookIntroduction;//简介/约稿需求
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
    private SysUser sysUser;//发布人
    private Integer taskType;//任务类型
    private Integer taskStatus;//任务状态
    private TBClassification classification;//分类
    private Integer viewCount;//热度

    private Set<TBUserTask> tbUserTasks = new HashSet<>();
    private Set<EditorViewHistory> editorViewHistories = new HashSet<>();

    @Id
    @Column(name = "task_id",unique = true, length = 36, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Column(name = "bookname",length = 200)
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Column(name = "source_language",length = 30)
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }
    @Column(name = "target_language",length = 30)
    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
    @Column(name = "publisher",length = 100)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(name = "words")
    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    @Column(name = "author",length = 100)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "author_introduction",columnDefinition = "Text")
    public String getAuthorIntroduction() {
        return authorIntroduction;
    }

    public void setAuthorIntroduction(String authorIntroduction) {
        this.authorIntroduction = authorIntroduction;
    }
    @Column(name = "book_introduction",columnDefinition = "Text")
    public String getBookIntroduction() {
        return bookIntroduction;
    }

    public void setBookIntroduction(String bookIntroduction) {
        this.bookIntroduction = bookIntroduction;
    }
    @Column(name = "author_fl_introduction",columnDefinition = "Text")
    public String getBookFlIntroduction() {
        return bookFlIntroduction;
    }

    public void setBookFlIntroduction(String bookFlIntroduction) {
        this.bookFlIntroduction = bookFlIntroduction;
    }
    @Column(name = "publish_type",length = 30)
    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }
    @Column(name = "ebook_type",length = 100)
    public String geteBookType() {
        return eBookType;
    }

    public void seteBookType(String eBookType) {
        this.eBookType = eBookType;
    }
    @Column(name = "ebook_price")
    public Float geteBookPrice() {
        return eBookPrice;
    }

    public void seteBookPrice(Float eBookPrice) {
        this.eBookPrice = eBookPrice;
    }

    @Column(name = "copyright_type",length = 30)
    public String getCopyrightType() {
        return copyrightType;
    }

    public void setCopyrightType(String copyrightType) {
        this.copyrightType = copyrightType;
    }
    @Column(name = "copyright_descript",columnDefinition = "Text")
    public String getCopyrightDescript() {
        return copyrightDescript;
    }

    public void setCopyrightDescript(String copyrightDescript) {
        this.copyrightDescript = copyrightDescript;
    }
    @Column(name = "trans_content",columnDefinition = "Text")
    public String getTransContent() {
        return transContent;
    }

    public void setTransContent(String transContent) {
        this.transContent = transContent;
    }
    @Column(name = "trans_expiration_date")
    public Date getTransExpirationDate() {
        return transExpirationDate;
    }

    public void setTransExpirationDate(Date transExpirationDate) {
        this.transExpirationDate = transExpirationDate;
    }
    @Column(name = "cover_path")
    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
    @Column(name = "doc_path")
    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Column(name = "task_type")
    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
    @Column(name = "task_status")
    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Column(name = "view_count")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    //@ManyToOne(cascade=CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", unique = true,nullable = true)
    public TBClassification getClassification() {
        return classification;
    }

    public void setClassification(TBClassification classification) {
        this.classification = classification;
    }

    @JsonIgnore
    @OneToMany(mappedBy="tbTask",cascade=CascadeType.ALL,orphanRemoval = true)
    public Set<TBUserTask> getTbUserTasks() {
        return tbUserTasks;
    }

    public void setTbUserTasks(Set<TBUserTask> tbUserTasks) {
        this.tbUserTasks = tbUserTasks;
    }

    @JsonIgnore
    @OneToMany(mappedBy="tbTask",cascade=CascadeType.ALL,orphanRemoval = true)
    public Set<EditorViewHistory> getEditorViewHistories() {
        return editorViewHistories;
    }

    public void setEditorViewHistories(Set<EditorViewHistory> editorViewHistories) {
        this.editorViewHistories = editorViewHistories;
    }
}
