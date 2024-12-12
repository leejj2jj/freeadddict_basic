package com.freeadddict.dict.report.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.freeadddict.dict.report.domain.Report;
import com.freeadddict.dict.report.dto.ReportListViewResponse;
import com.freeadddict.dict.report.service.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReportViewController {

  private final ReportService reportService;

  @GetMapping("/reports")
  public String getReports(Model model) {
    List<ReportListViewResponse> reports = reportService.findAll().stream()
        .map(ReportListViewResponse::new)
        .toList();

    model.addAttribute("reports", reports);
    return "reportList";
  }

  @GetMapping("/reports/{id}")
  public String getReport(@PathVariable Long id, Model model) {
    Report report = reportService.findById(id);
    model.addAttribute("report", new ReportListViewResponse(report));

    return "report";
  }
}
