package com.freeadddict.dict.member.address;

import java.util.ArrayList;
import java.util.List;

import com.freeadddict.dict.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Address {

  @Id
  private String postcode;

  @Column(nullable = false, length = 100)
  private String address;

  @Column(columnDefinition = "TEXT")
  private String detailedAddress;

  @OneToMany(mappedBy = "address")
  private List<Member> members = new ArrayList<>();

  public Address(String postcode, String address, String detailedAddress) {
    this.postcode = postcode;
    this.address = address;
    this.detailedAddress = detailedAddress;
  }

}
