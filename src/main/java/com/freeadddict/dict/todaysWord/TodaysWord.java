package com.freeadddict.dict.todaysWord;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.word.Word;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodaysWord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  private LocalDateTime todayDate;

  @ManyToOne
  @JoinColumn
  private Word word;

  @ManyToOne
  @JoinColumn(name = "admin_idx")
  private Admin admin;

  @Builder
  public TodaysWord(Word word, Admin admin) {
    this.word = word;
    this.admin = admin;
  }

}
