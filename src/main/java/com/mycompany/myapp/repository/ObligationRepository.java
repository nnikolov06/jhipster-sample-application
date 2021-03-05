package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Obligation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Obligation entity.
 */
@Repository
public interface ObligationRepository extends JpaRepository<Obligation, Long> {

    @Query(value = "select distinct obligation from Obligation obligation left join fetch obligation.rezmaEntities",
        countQuery = "select count(distinct obligation) from Obligation obligation")
    Page<Obligation> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct obligation from Obligation obligation left join fetch obligation.rezmaEntities")
    List<Obligation> findAllWithEagerRelationships();

    @Query("select obligation from Obligation obligation left join fetch obligation.rezmaEntities where obligation.id =:id")
    Optional<Obligation> findOneWithEagerRelationships(@Param("id") Long id);
}
