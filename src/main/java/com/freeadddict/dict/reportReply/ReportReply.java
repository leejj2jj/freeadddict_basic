package com.freeadddict.dict.reportReply;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.report.domain.Report;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportReply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Pattern(regexp = "^{1,100}$")
  @Column(length = 100)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @CreatedDate
  private LocalDateTime writeDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn(name = "admin_idx")
  private Admin admin;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Report report;

  @Builder
  public ReportReply(String title, String content, Admin admin, Report report) {
    this.title = title;
    this.content = content;
    this.admin = admin;
    this.report = report;
  }

}
