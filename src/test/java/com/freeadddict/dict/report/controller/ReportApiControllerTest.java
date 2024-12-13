package com.freeadddict.dict.report.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freeadddict.dict.report.domain.Report;
import com.freeadddict.dict.report.dto.AddReportRequest;
import com.freeadddict.dict.report.repository.ReportRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportApiControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  ReportRepository reportRepository;

  @BeforeEach
  public void mockMvcSetUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    reportRepository.deleteAll();
  }

  @DisplayName("addReport: 문의 글 추가에 성공한다.")
  @Test
  public void addReport() throws Exception {
    // given
    final String url = "/api/reports";
    final String title = "tile";
    final String content = "content";
    final AddReportRequest userRequest = new AddReportRequest(title, content);

    final String requestBody = objectMapper.writeValueAsString(userRequest);

    // when
    ResultActions result = mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestBody));

    // then
    result.andExpect(status().isCreated());

    List<Report> reports = reportRepository.findAll();

    assertThat(reports.size()).isEqualTo(1);
    assertThat(reports.get(0).getTitle()).isEqualTo(title);
    assertThat(reports.get(0).getContent()).isEqualTo(content);
  }

  @DisplayName("findAllReports: 문의 글 목록 조회에 성공한다.")
  @Test
  public void findAllReports() throws Exception {
    // given
    final String url = "/api/reports";
    final String title = "title";
    final String content = "content";

    reportRepository.save(Report.builder()
        .title(title)
        .content(content)
        .build());

    // when
    final ResultActions resultActions = mockMvc.perform(get(url)
        .accept(MediaType.APPLICATION_JSON));

    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].content").value(content))
        .andExpect(jsonPath("$[0].title").value(title));
  }

  @DisplayName("findReport: 문의 글 조회에 성공한다.")
  @Test
  public void findReport() throws Exception {
    // given
    final String url = "/api/reports/{id}";
    final String title = "title";
    final String content = "content";

    Report savedReport = reportRepository.save(Report.builder()
        .title(title)
        .content(content)
        .build());

    // when
    final ResultActions resultActions = mockMvc.perform(get(url, savedReport.getId()));

    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value(content))
        .andExpect(jsonPath("$.title").value(title));
  }

  @DisplayName("deleteReport: 문의 글 삭제에 성공한다.")
  @Test
  public void deleteReport() throws Exception {
    // given
    final String url = "/api/reports/{id}";
    final String title = "title";
    final String content = "content";

    Report savedReport = reportRepository.save(Report.builder()
        .title(title)
        .content(content)
        .build());

    // when
    mockMvc.perform(delete(url, savedReport.getId()))
        .andExpect(status().isOk());

    // then
    List<Report> reports = reportRepository.findAll();

    assertThat(reports).isEmpty();
  }
}
