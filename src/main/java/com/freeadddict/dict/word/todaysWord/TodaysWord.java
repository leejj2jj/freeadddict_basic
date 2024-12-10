package com.freeadddict.dict.word.todaysWord;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.word.Word;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class TodaysWord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private LocalDateTime todayDate;

  @ManyToOne
  @JoinColumn
  private Word word;

  @ManyToOne
  @JoinColumn
  private Admin admin;

  public TodaysWord(Word word, Admin admin) {
    this.word = word;
    this.admin = admin;
  }

}
