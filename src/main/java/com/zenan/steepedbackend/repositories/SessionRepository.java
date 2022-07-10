package com.zenan.steepedbackend.repositories;

import com.zenan.steepedbackend.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    @Query("SELECT DISTINCT s FROM Session s WHERE s.userId = :userId")
    List<Session> findByUserId(@Param("userId") int userId);
}
