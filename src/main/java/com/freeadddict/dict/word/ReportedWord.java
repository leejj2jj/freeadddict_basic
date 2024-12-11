package com.freeadddict.dict.word;

import com.freeadddict.dict.report.Report;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportedWord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "report_id")
  private Report report;

  @ManyToOne
  @JoinColumn(name = "word_id")
  private Word word;

  public ReportedWord(Report report, Word word) {
    this.report = report;
    this.word = word;
  }

}
