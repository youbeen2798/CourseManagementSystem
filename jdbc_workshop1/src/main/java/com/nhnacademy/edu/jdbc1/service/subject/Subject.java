package com.nhnacademy.edu.jdbc1.service.subject;

import java.util.Date;

public class Subject {
  private final Long id;
  private final String name;
  private final Date createdAt;

  public Subject(Long id, String name, Date createdAt) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Subject{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}
