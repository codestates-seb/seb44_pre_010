package com.rainbow.sof.domain.question.repository;

import com.rainbow.sof.domain.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findTop20ByOrderByViewDesc();
    Page<Question> findByTitleContaining(Pageable pageable, String keyword);
}
