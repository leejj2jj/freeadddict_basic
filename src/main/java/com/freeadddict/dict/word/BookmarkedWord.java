package com.freeadddict.dict.word;

import com.freeadddict.dict.bookmark.Bookmark;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class BookmarkedWord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "word_id")
  private Word word;

  @ManyToOne
  @JoinColumn(name = "bookmark_id")
  private Bookmark bookmark;
}