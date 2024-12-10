package com.freeadddict.dict.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.freeadddict.dict.report.reportReply.ReportReply;
import com.freeadddict.dict.word.todaysWord.TodaysWord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Column(nullable = false, length = 15)
  private String id;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = false, unique = true, length = 15)
  private String nickname;

  @Column(nullable = false)
  private LocalDateTime addDate;

  private LocalDateTime modifyDate;

  @Column(nullable = false)
  private LocalDateTime accessDate;

  @OneToMany(mappedBy = "admin")
  private List<ReportReply> reportReplies = new ArrayList<>();

  @OneToMany(mappedBy = "admin")
  private List<TodaysWord> todaysWords = new ArrayList<>();

  public Admin(String password, String nickname) {
    this.password = password;
    this.nickname = nickname;
  }

}
