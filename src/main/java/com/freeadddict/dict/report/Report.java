package com.freeadddict.dict.report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.report.reportReply.ReportReply;
import com.freeadddict.dict.word.ReportedWord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false)
  private LocalDateTime writeDate;

  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn
  private Member member;

  @OneToOne(mappedBy = "report")
  private ReportReply reportReply;

  @OneToMany(mappedBy = "report")
  private List<ReportedWord> reportedWords = new ArrayList<>();
}