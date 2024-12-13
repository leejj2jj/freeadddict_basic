package com.freeadddict.dict.word;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.freeadddict.dict.admin.Admin;
import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.phrase.WordPhrase;
import com.freeadddict.dict.todaysWord.TodaysWord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  @Pattern(regexp = "^.{1,50}$", message = "단어명은 50자 이내로 입력하세요.")
  private String name;

  @Column(nullable = false, length = 50)
  @Pattern(regexp = "^.{1,50}$", message = "품사명은 50자 이내로 입력하세요.")
  private String partOfSpeech;

  @Column(nullable = false, length = 100)
  @Pattern(regexp = "^.{1,100}$", message = "발음은 100자 이내로 입력하세요.")
  private String pronunciation;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String meaning;

  @CreatedDate
  private LocalDateTime addDate;

  @LastModifiedDate
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
