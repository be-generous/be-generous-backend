package com.begenerous.repository;

import com.begenerous.model.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepo extends JpaRepository<Charity, Long> {
}
