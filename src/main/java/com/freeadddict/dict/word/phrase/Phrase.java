package com.freeadddict.dict.word.phrase;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Phrase {

  @Id
  @Column(nullable = false, columnDefinition = "TEXT")
  private String phrase;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String meaning;

  @OneToMany(mappedBy = "phrase")
  private List<WordPhrase> words = new ArrayList<>();

  public Phrase(String phrase, String meaning) {
    this.phrase = phrase;
    this.meaning = meaning;
  }

}
