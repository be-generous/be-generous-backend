package com.begenerous.repository;

import com.begenerous.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonationRepo extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT * FROM DONATION WHERE CHARITY_ID = ?1", nativeQuery = true)
    public Optional<List<Donation>> findAllByCharityId(Long charityId);

    @Query(value = "SELECT * FROM DONATION WHERE CREDIT_CARD_ID = ?1", nativeQuery = true)
    public Optional<List<Donation>> findAllByCreditCardId(Long creditCardId);

    @Query(value = "SELECT * FROM DONATION D JOIN CREDIT_CARD C ON D.CREDIT_CARD_ID = C.CREDIT_CARD_ID WHERE C.USER_ID = ?1", nativeQuery = true)
    public Optional<List<Donation>> findAllByUserId(Long userId);
}