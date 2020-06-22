package com.ranga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Embeddable
@Data
public class ECreateInfo {
  public ECreateInfo() {
  }

  public ECreateInfo(ZonedDateTime createdOn, String createdBy) {
    this.createdOn = createdOn;
    this.createdBy = createdBy;
  }
  @CreatedDate
  @Column(name = "CREATED_ON", nullable = true,updatable = false)
  private ZonedDateTime createdOn;

  @CreatedBy
  @Column(name = "CREATED_BY", nullable = true,updatable = false)
  private String createdBy;
}
