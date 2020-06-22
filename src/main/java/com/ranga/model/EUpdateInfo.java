package com.ranga.model;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import java.time.ZonedDateTime;

@Embeddable
@Data
public class EUpdateInfo {
  @LastModifiedDate
  @Column(name = "UPDATED_ON", nullable = true, insertable = false)
  private ZonedDateTime lastUpdatedOn;

  @LastModifiedBy
  @Column(name = "UPDATED_BY", nullable = true,insertable = false)
  private String lastUpdatedBy;

  public EUpdateInfo() {}

  public EUpdateInfo(ZonedDateTime lastUpdatedOn, String lastUpdatedBy) {
    this.lastUpdatedOn = lastUpdatedOn;
    this.lastUpdatedBy = lastUpdatedBy;
  }
  public String toString() {
    return "Updated by - " + this.lastUpdatedBy + " on " + this.lastUpdatedOn;
  }
}
