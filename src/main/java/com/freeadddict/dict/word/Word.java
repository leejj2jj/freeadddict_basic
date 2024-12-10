package com.freeadddict.dict.word;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.word.phrase.WordPhrase;
import com.freeadddict.dict.word.todaysWord.TodaysWord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Word {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 50)
  private String partOfSpeech;

  @Column(nullable = false, length = 100)
  private String pronunciation;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String meaning;

  @Column(nullable = false)
  private LocalDateTime addDate;

  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn
  private Member member;

  @ManyToOne
  @JoinColumn
  private Admin admin;

  @OneToMany(mappedBy = "word")
  private List<TodaysWord> todaysWords = new ArrayList<>();

  @OneToMany(mappedBy = "word")
  private List<BookmarkedWord> bookmarks = new ArrayList<>();

  @OneToMany(mappedBy = "word")
  private List<WordPhrase> wordPhrases = new ArrayList<>();

  @OneToMany(mappedBy = "word")
  private List<ReportedWord> reports = new ArrayList<>();

  public Word(String name, String partOfSpeech, String pronunciation, String meaning) {
    this.name = name;
    this.partOfSpeech = partOfSpeech;
    this.pronunciation = pronunciation;
    this.meaning = meaning;
  }

}
