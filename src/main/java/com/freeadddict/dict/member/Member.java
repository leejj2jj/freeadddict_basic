package com.freeadddict.dict.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.freeadddict.dict.bookmark.Bookmark;
import com.freeadddict.dict.member.address.Address;
import com.freeadddict.dict.report.Report;
import com.freeadddict.dict.word.Word;

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
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Column(nullable = false, length = 15)
  private String id;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, unique = true, length = 15)
  private String nickname;

  @Column(length = 15)
  private String phone;

  @Column(nullable = false, unique = true, length = 50)
  private String email;

  private Boolean isReceivingEmail;

  @Column(nullable = false)
  private LocalDateTime registerDate;

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

  public Member(String password, String name, String nickname, String phone, String email, Boolean isReceivingEmail) {
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.email = email;
    this.isReceivingEmail = isReceivingEmail;
  }

}
