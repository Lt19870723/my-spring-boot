package com.myproject.myindex.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.myproject.myindex.Enum.QueueStatusType;

@Entity
@Table(name = "smp_pay_queue")
public class Queue {
    /**队列编号**/
    @Id
    @Column(length = 32)
    private String          queueId;
    /**队列类型**/
    @Column(length = 64)
    private String          queueType;
    /**业务数据类型**/
    @Column(length = 64)
    private String          dataType;
    /**业务数据Id**/
    @Column(length = 64)
    private String          dataId;
    /**状态**/
    @Enumerated(EnumType.STRING)
    private QueueStatusType status;
    /**预期执行时间**/
    private Date            expectTime;
    /**失败次数**/
    private Integer         faildTime;
    /**创建时间**/
    private Date            createTime;
    /**修改时间**/
    private Date            modifiedTime;

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataId() {
        return dataId;
    }

    public QueueStatusType getStatus() {
        return status;
    }

    public void setStatus(QueueStatusType status) {
        this.status = status;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setFaildTime(Integer faildTime) {
        this.faildTime = faildTime;
    }

    public Integer getFaildTime() {
        return faildTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    @PrePersist
    public void onCreate() {
        this.queueId = UUId.getUUid();
        this.createTime = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedTime = new Date();
    }

}
