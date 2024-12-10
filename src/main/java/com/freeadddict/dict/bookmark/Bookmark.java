package com.freeadddict.dict.bookmark;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.validation.constraints.Pattern;
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
  @Pattern(regexp = "^{1,50}$", message = "이름은 50자 이하여야 합니다.")
  private String name;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime makeDate;

  @UpdateTimestamp
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
