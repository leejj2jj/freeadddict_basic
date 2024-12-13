package com.freeadddict.dict.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.freeadddict.dict.address.Address;
import com.freeadddict.dict.bookmark.Bookmark;
import com.freeadddict.dict.report.domain.Report;
import com.freeadddict.dict.word.Word;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Pattern(regexp = "^.{1,15}$")
  @Column(nullable = false, length = 15)
  private String id;

  @Pattern(regexp = "^.{8,60}$")
  @Column(nullable = false, length = 60)
  private String password;

  @Pattern(regexp = "^.{1,50}$")
  @Column(nullable = false, length = 50)
  private String name;

  @Pattern(regexp = "^.{1,15}$")
  @Column(nullable = false, unique = true, length = 15)
  private String nickname;

  @Pattern(regexp = "^.{1,15}$")
  @Column(length = 15)
  private String phone;

  @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")
  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @ColumnDefault("1")
  // @Column(columnDefinition = "TINYINT(1)")
  private boolean isReceivingEmail;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime registerDate;

  @LastModifiedDate
  private LocalDateTime modifyDate;

  @Column(nullable = false)
  private LocalDateTime accessDate;

  @ManyToOne
  @JoinColumn(name = "postcode")
  private Address address;

  @OneToMany(mappedBy = "member")
  private List<Word> words = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Bookmark> bookmarks = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Report> reports = new ArrayList<>();

  @Builder
  public Member(String id, String password, String name, String nickname, String phone, String email,
      boolean isReceivingEmail) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.email = email;
    this.isReceivingEmail = isReceivingEmail;
  }
}
