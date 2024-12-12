package com.freeadddict.dict.report.dto;

import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.report.domain.Report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReportRequest {

  private String title;
  private String content;
  private Member member;

  public Report toEntity() {
    return Report.builder()
        .title(title)
        .content(content)
        .member(member)
        .build();
  }

}
