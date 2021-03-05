package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ScheduleLock;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ScheduleLock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScheduleLockRepository extends JpaRepository<ScheduleLock, Long> {
}
