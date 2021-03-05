package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RezmaEntity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RezmaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RezmaEntityRepository extends JpaRepository<RezmaEntity, Long> {
}
