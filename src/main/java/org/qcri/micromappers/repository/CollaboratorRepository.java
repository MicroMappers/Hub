package org.qcri.micromappers.repository;

import java.util.List;

import org.qcri.micromappers.entity.Collaborator;
import org.springframework.data.repository.CrudRepository;

public abstract interface CollaboratorRepository extends CrudRepository<Collaborator, Long>
{
	public Long countByCollectionIdAndAccountId(Long collectionId, Long accountId);

	public List<Collaborator> findByCollectionId(Long collectionId);
}
