/*
package com.ranga.model;

import com.ranga.model.Audit;
import com.ranga.model.Auditable;
import com.ranga.model.LoggedUser;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

  @PrePersist
  public void setCreatedOn(Auditable auditable) {
    Audit audit = auditable.getAudit();

    if(audit == null) {
      audit = new Audit();
      auditable.setAudit(audit);
    }

    audit.setCreatedOn(LocalDateTime.now());
    audit.setCreatedBy(LoggedUser.get());
  }

  @PreUpdate
  public void setUpdatedOn(Auditable auditable) {
    Audit audit = auditable.getAudit();
    if(audit == null) {
      audit = new Audit();
      auditable.setAudit(audit);
    }
    audit.setUpdatedOn(LocalDateTime.now());
    audit.setUpdatedBy(LoggedUser.get());
  }
}*/
