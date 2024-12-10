package com.freeadddict.dict.word.todaysWord;

import java.time.LocalDateTime;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.word.Word;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class TodaysWord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime todayDate;

  @ManyToOne
  @JoinColumn
  private Word word;

  @ManyToOne
  @JoinColumn
  private Admin admin;

}
