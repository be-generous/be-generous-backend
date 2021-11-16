package com.begenerous.repository;

import com.begenerous.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditCardRepo extends JpaRepository<CreditCard, Long> {

    @Query(value = "SELECT * FROM CREDIT_CARD WHERE USER_ID = ?1", nativeQuery = true)
    public List<CreditCard> findAllByUserId(Long userId);
}
