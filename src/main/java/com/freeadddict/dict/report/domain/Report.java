package com.freeadddict.dict.report.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.reportReply.ReportReply;
import com.freeadddict.dict.word.ReportedWord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime writeDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn(name = "member_idx")
  private Member member;

  @OneToOne(mappedBy = "report")
  private ReportReply reportReply;

  @OneToMany(mappedBy = "report")
  private List<ReportedWord> reportedWords = new ArrayList<>();

  @Builder
  private Report(String title, String content, Member member) {
    this.title = title;
    this.content = content;
    this.member = member;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
    this.modifyDate = LocalDateTime.now();
  }
}
