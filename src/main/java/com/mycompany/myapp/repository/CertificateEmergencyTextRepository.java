package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CertificateEmergencyText;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CertificateEmergencyText entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificateEmergencyTextRepository extends JpaRepository<CertificateEmergencyText, Long> {
}
