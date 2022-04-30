package com.boost.webcrawler.dao;

import com.boost.webcrawler.model.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DomainRepository extends JpaRepository<Domain,Long> {

    Optional<Domain> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Domain d set d.count = d.count + 1 WHERE d.id = :id")
    void updateCounter( Long id);
}
