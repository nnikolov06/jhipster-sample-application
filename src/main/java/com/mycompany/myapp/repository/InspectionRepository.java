package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Inspection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Inspection entity.
 */
@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query(value = "select distinct inspection from Inspection inspection left join fetch inspection.rezmaEntities",
        countQuery = "select count(distinct inspection) from Inspection inspection")
    Page<Inspection> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct inspection from Inspection inspection left join fetch inspection.rezmaEntities")
    List<Inspection> findAllWithEagerRelationships();

    @Query("select inspection from Inspection inspection left join fetch inspection.rezmaEntities where inspection.id =:id")
    Optional<Inspection> findOneWithEagerRelationships(@Param("id") Long id);
}
