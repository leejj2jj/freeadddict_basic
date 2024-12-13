package com.freeadddict.dict.bookmark;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.freeadddict.dict.member.Member;
import com.freeadddict.dict.word.BookmarkedWord;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @Pattern(regexp = "^{1,50}$")
  @Column(nullable = false, length = 50)
  private String name;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime makeDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;

  @ManyToOne
  @JoinColumn(name = "member_idx", nullable = false)
  private Member member;

  @OneToMany(mappedBy = "bookmark")
  private List<BookmarkedWord> bookmarkedWords = new ArrayList<>();

  @Builder
  public Bookmark(String name) {
    this.name = name;
  }

}
