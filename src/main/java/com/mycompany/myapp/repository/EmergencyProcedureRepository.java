package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EmergencyProcedure;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EmergencyProcedure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmergencyProcedureRepository extends JpaRepository<EmergencyProcedure, Long> {
}
