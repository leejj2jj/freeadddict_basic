package com.freeadddict.dict.report.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freeadddict.dict.report.domain.Report;
import com.freeadddict.dict.report.dto.AddReportRequest;
import com.freeadddict.dict.report.dto.ReportResponse;
import com.freeadddict.dict.report.dto.UpdateReportRequest;
import com.freeadddict.dict.report.service.ReportService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReportApiController {

  private final ReportService reportService;

  @PostMapping("/api/reports")
  public ResponseEntity<Report> addReport(@RequestBody AddReportRequest request) {
    Report savedReport = reportService.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);
  }

  @GetMapping("/api/reports")
  public ResponseEntity<List<ReportResponse>> findAllReports() {
    List<ReportResponse> reports = reportService.findAll()
        .stream()
        .map(ReportResponse::new)
        .toList();

    return ResponseEntity.ok().body(reports);
  }

  @GetMapping("/api/reports/{id}")
  public ResponseEntity<ReportResponse> findReport(@PathVariable long id) {
    Report report = reportService.findById(id);

    return ResponseEntity.ok().body(new ReportResponse(report));
  }

  @DeleteMapping("/api/reports/{id}")
  public ResponseEntity<Void> deleteReport(@PathVariable long id) {
    reportService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/api/reports/{id}")
  public ResponseEntity<Report> updateReport(@PathVariable long id,
      @RequestBody UpdateReportRequest request) {
    Report updatedReport = reportService.update(id, request);
    return ResponseEntity.ok().body(updatedReport);
  }
}
