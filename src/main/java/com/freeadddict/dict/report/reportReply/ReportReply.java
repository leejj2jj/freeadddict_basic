package com.freeadddict.dict.report.reportReply;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.report.Report;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
@Entity
public class ReportReply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100)
  @Pattern(regexp = "^{1,100}$", message = "제목은 100자 이하로 입력하세요.")
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @CreationTimestamp
  private LocalDateTime writeDate;

  @UpdateTimestamp
  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn
  private Admin admin;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "report_id", referencedColumnName = "id")
  private Report report;

  public ReportReply(String title, String content, Admin admin, Report report) {
    this.title = title;
    this.content = content;
    this.admin = admin;
    this.report = report;
  }

}
