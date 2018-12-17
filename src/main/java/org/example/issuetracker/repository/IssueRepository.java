package org.example.issuetracker.repository;

import org.example.issuetracker.model.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends CrudRepository<Issue, Long>{
	
	@Query("SELECT i FROM Issue i WHERE i.id = :id")
	Issue findIssueById(@Param("id") Long id);

}
