package com.freeadddict.dict.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

  Optional<Admin> findById(String id);

  Optional<Admin> findByNickname(String nickname);

}
