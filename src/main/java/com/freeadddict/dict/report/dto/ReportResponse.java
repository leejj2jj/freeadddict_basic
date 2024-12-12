package com.freeadddict.dict.report.dto;

import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.report.domain.Report;

import lombok.Getter;

@Getter
public class ReportResponse {

  private String title;
  private String content;
  private Member member;

  public ReportResponse(Report report) {
    this.title = report.getTitle();
    this.content = report.getContent();
    this.member = report.getMember();
  }

}
