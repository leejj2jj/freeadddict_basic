package com.freeadddict.dict.report.reportReply;

import java.time.LocalDateTime;

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
import lombok.Getter;

@Getter
@Entity
public class ReportReply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime writeDate;

  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn
  private Admin admin;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "report_id", referencedColumnName = "id")
  private Report report;

}
