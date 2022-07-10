package com.zenan.steepedbackend.repositories;

import com.zenan.steepedbackend.entities.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Integer> {
}
