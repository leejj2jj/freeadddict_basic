package com.freeadddict.dict.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.validation.constraints.Pattern;
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
  @Pattern(regexp = "^.{1,15}$", message = "ID는 1~15자로 입력하세요.")
  private String id;

  @Column(nullable = false, length = 60)
  @Pattern(regexp = "^.{8,60}$", message = "비밀번호는 8~60자로 입력하세요.")
  private String password;

  @Column(nullable = false, length = 50)
  @Pattern(regexp = "^.{1,50}$", message = "이름은 1~50자로 입력하세요.")
  private String name;

  @Column(nullable = false, unique = true, length = 15)
  @Pattern(regexp = "^.{1,15}$", message = "별명은 1~15자로 입력하세요.")
  private String nickname;

  @Column(length = 15)
  @Pattern(regexp = "^.{1,15}$", message = "전화번호는 1~15자로 입력하세요.")
  private String phone;

  @Column(nullable = false, unique = true, length = 50)
  @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$", message = "이메일 형식을 지켜주세요.")
  private String email;

  private boolean isReceivingEmail;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime registerDate;

  @UpdateTimestamp
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
