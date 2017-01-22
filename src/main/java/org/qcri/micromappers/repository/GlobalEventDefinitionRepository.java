package org.qcri.micromappers.repository;

import org.qcri.micromappers.entity.GdeltMMIC;
import org.qcri.micromappers.entity.GlobalEventDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jlucas on 12/11/16.PagingAndSortingRepository
 */
public abstract interface GlobalEventDefinitionRepository extends PagingAndSortingRepository<GlobalEventDefinition, Long> {
    GlobalEventDefinition findByEventUrl(String eventUrl);
    Page<GlobalEventDefinition> findAll(Pageable pageable);
	GlobalEventDefinition findById(Long id);

    List<GlobalEventDefinition> findByState(String state);

    @Query("SELECT d FROM GlobalEventDefinition d WHERE d.state=:state and d.articleTag like :words or d.searchKeyword like :words")
    List<GlobalEventDefinition> findByStateAndTag(@Param("state")String state, @Param("words")String words);
}


