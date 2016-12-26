package com.hugo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ohj on 2016/12/8.
 * 图书分类
 */
@Entity
@Table(name = "tb_classification")
public class TBClassification implements Serializable {
    private String id;
    private String name;
    private Integer orderNum;

    private Set<TBTask> tbTasks = new HashSet<>();

    @Id
    @Column(name = "class_id",unique = true, length = 50, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "order_num")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    @JsonIgnore
    @OneToMany(mappedBy="classification",cascade=CascadeType.ALL,orphanRemoval = true)
    public Set<TBTask> getTbTasks() {
        return tbTasks;
    }

    public void setTbTasks(Set<TBTask> tbTasks) {
        this.tbTasks = tbTasks;
    }
}
