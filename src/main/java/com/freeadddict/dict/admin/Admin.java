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
  @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s-]{1,15}$", message = "ID는 15자 이하의 영문, 한글, 공백, 하이픈, 언더바만 가능합니다.")
  private String id;

  @Column(nullable = false, length = 60)
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문, 한글, 특수 문자(!@#$%^&*)를 포함해야 합니다.")
  private String password;

  @Column(nullable = false, unique = true, length = 15)
  @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,15}$", message = "닉네임은 15자 이하만 가능합니다.")
  private String nickname;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime addDate;

  @UpdateTimestamp
  private LocalDateTime modifyDate;

  private LocalDateTime accessDate;

  @OneToMany(mappedBy = "admin")
  private List<ReportReply> reportReplies = new ArrayList<>();

  @OneToMany(mappedBy = "admin")
  private List<TodaysWord> todaysWords = new ArrayList<>();

  public Admin(String id, String password, String nickname) {
    this.id = id;
    this.password = password;
    this.nickname = nickname;
    this.accessDate = LocalDateTime.now();
  }

}
