package com.freeadddict.dict.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.freeadddict.dict.report.reportReply.ReportReply;
import com.freeadddict.dict.word.todaysWord.TodaysWord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long idx;

  @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s-]{1,15}$")
  @Column(nullable = false, length = 15)
  private String id;

  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,16}$")
  @Column(nullable = false, length = 60)
  private String password;

  @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,15}$")
  @Column(nullable = false, unique = true, length = 15)
  private String nickname;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime addDate;

  @UpdateTimestamp
  private LocalDateTime modifyDate;

  private LocalDateTime accessDate;

  @OneToMany(mappedBy = "admin")
  private List<ReportReply> reportReplies = new ArrayList<>();

  @OneToMany(mappedBy = "admin")
  private List<TodaysWord> todaysWords = new ArrayList<>();

  @Builder
  public Admin(String id, String password, String nickname) {
    this.id = id;
    this.password = password;
    this.nickname = nickname;
    this.accessDate = LocalDateTime.now();
  }

}
