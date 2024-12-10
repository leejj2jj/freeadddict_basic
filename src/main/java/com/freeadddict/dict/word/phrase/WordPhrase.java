package com.freeadddict.dict.word.phrase;

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
public class WordPhrase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "word_id")
  private Word word;

  @ManyToOne
  @JoinColumn(name = "phrase_id")
  private Phrase phrase;

  public WordPhrase(Word word, Phrase phrase) {
    this.word = word;
    this.phrase = phrase;
  }

}
