package org.example.issuetracker.web.api;

import java.util.ArrayList;
import java.util.List;

import org.example.issuetracker.model.Issue;
import org.example.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IssueController {

	@Autowired
	private IssueService issueService;

	@RequestMapping(
			value = "/issues", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Issue>> getAllIssues() {
		log.info("> getAllIssues");
		List<Issue> issues = null;

		try {
			issues = issueService.findAll();
			if (issues == null) {
				issues = new ArrayList<Issue>();
			}

		} catch (Exception e) {
			log.error("Ocurrio una exception.", e);
			return new ResponseEntity<List<Issue>>(issues,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("< getAllIssues");
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/issues", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
		log.info("> createIssue");
		Issue createdIssue = null;

		try {
			createdIssue = issueService.create(issue);
		} catch (Exception ex) {
			log.error("Unexpected exception caught.", ex);
			return new ResponseEntity<Issue>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("< createIssue");
		return new ResponseEntity<Issue>(createdIssue, HttpStatus.CREATED);
	}

	@RequestMapping(
			value = "/issues/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> getIssue(@PathVariable("id") Long id) {
		log.info("> getIssue");

		Issue issue = null;

		try {
			issue = issueService.find(id);
			if (issue == null) {
				return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			log.error("Unexprected exception caught", ex);
			return new ResponseEntity<Issue>(issue,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("< getIssue");
		return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/issues/{id}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue) {
		log.info("> updateIssue");

		Issue updatedIssue = null;
		try {
			updatedIssue = issueService.update(issue);
		} catch (Exception e) {
			log.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Issue>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("< updateIssue");
		return new ResponseEntity<Issue>(updatedIssue, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/issues/{id}", 
			method = RequestMethod.DELETE)
	public ResponseEntity<Issue> deleteIssue(@PathVariable("id") Long issueId) {
		log.info("> deleteIssue");

		try {
			issueService.delete(issueId);
		} catch (Exception e) {
			log.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Issue>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("< deleteIssue");
		return new ResponseEntity<Issue>(HttpStatus.NO_CONTENT);
	}
}
