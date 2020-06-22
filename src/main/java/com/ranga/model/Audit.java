/*
package com.ranga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class Audit{
    @CreatedDate
    @Column(name = "created_on",insertable = true,updatable = false)
    private LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "created_by",insertable = true,updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_on",insertable = false,updatable = true)
    private LocalDateTime updatedOn;

    @LastModifiedBy
    @Column(name = "updated_by",insertable = false,updatable = true)
    private String updatedBy;

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
}*/
