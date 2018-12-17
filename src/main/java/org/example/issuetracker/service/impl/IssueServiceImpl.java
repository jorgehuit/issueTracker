package org.example.issuetracker.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.example.issuetracker.model.Issue;
import org.example.issuetracker.model.IssuePriority;
import org.example.issuetracker.model.IssueStatus;
import org.example.issuetracker.repository.IssueRepository;
import org.example.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("issueService")
@Transactional
@Slf4j
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueRepository issueRepository;

	@Override
	public List<Issue> findAll() {

		log.info("> findAll");

		List<Issue> issues = (List<Issue>) issueRepository.findAll();

		log.info("< findAll");
		return issues;
	}

	@Override
	public Issue create(Issue issue) {
		log.info("> create");

		// establecer atributo por defecto
		issue.setStatus(IssueStatus.OPEN);

		if (issue.getPriority() == null) {
			issue.setPriority(IssuePriority.MEDIUM);
		}

		// persistimos la entidad
		Issue persistedIssue = issueRepository.save(issue);

		log.info("< create");
		return persistedIssue;
	}

	@Override
	public Issue find(Long id) {
		log.info("> 	find id:{}", id);

		Issue issue = issueRepository.findIssueById(id);

		log.info("< find id:[]", id);
		return issue;
	}

	@Override
	public Issue update(Issue issue) {
		log.info("> update");

		Issue updatedIssue = issueRepository.save(issue);

		log.info("< update");
		return updatedIssue;
	}

	@Override
	public void delete(Long id) {
		log.info("> delete");

		issueRepository.deleteById(id);

		log.info("< delete");

	}

}
