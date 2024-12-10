package com.freeadddict.dict.bookmark;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.word.BookmarkedWord;

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
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false)
  private LocalDateTime makeDate;

  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn
  private Member member;

  @OneToMany(mappedBy = "bookmark")
  private List<BookmarkedWord> bookmarkedWords = new ArrayList<>();

  public Bookmark(String name) {
    this.name = name;
  }

}
