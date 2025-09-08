package com.desafio.fit21.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.fit21.model.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByid(Long id);

    Progress findByidAndDayNumber(Long id, int dayNumber);
}
