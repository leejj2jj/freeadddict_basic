package com.freeadddict.dict.phrase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, String> {

}