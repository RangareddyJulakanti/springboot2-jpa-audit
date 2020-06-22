package com.ranga.model;

import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.data.jpa.domain.AbstractPersistable;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PersistableEntityListener {
  public PersistableEntityListener() {}

  @PrePersist
  public void prePersist(AbstractPersistable e) {
    if (e instanceof EAuditable.Create) {
      EAuditable.Create create = (EAuditable.Create) e;
      create.setCreateInfo(
          new ECreateInfo(ZonedDateTime.now(ZoneId.of("UTC")), this.getUserName()));
    }
    if (e instanceof EAuditable.CreateDate) {
      EAuditable.CreateDate createDate = (EAuditable.CreateDate) e;
      createDate.setCreatedOn(ZonedDateTime.now(ZoneId.of("UTC")));
    }
    if (e instanceof EAuditable.CreateUser) {
      EAuditable.CreateUser createUser = (EAuditable.CreateUser) e;
      createUser.setCreatedBy(this.getUserName());
    }
    this.preUpdate(e);
  }
  @PreUpdate
  public void preUpdate(AbstractPersistable e) {
    if (e instanceof EAuditable.Update) {
      EAuditable.Update update = (EAuditable.Update) e;
      update.setUpdateInfo(
          new EUpdateInfo(ZonedDateTime.now(ZoneId.of("UTC")), this.getUserName()));
    }

    if (e instanceof EAuditable.UpdateDate) {
      EAuditable.UpdateDate updateDate = (EAuditable.UpdateDate) e;
      updateDate.setLastUpdatedOn(ZonedDateTime.now(ZoneId.of("UTC")));
    }

    if (e instanceof EAuditable.UpdateUser) {
      EAuditable.UpdateUser updateUser = (EAuditable.UpdateUser) e;
      updateUser.setLastUpdatedBy(this.getUserName());
    }
  }

  private String getUserName() {
    return LoggedUser.get();
  }
}