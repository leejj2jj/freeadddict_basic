package com.freeadddict.dict.word.todaysWord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodaysWordRepository extends JpaRepository<TodaysWord, Long> {

}
