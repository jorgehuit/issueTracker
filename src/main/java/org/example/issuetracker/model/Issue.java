package org.example.issuetracker.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ISSUE" , schema="ISSUETRACKER")
@Data
public class Issue {

	 @Id
	 @GeneratedValue
	 private Long id;

	 private String title;

	 private String description;

	 @Enumerated(EnumType.STRING)
	 private IssueType type;

	 @Enumerated(EnumType.STRING)
	 private IssuePriority priority;

	 @Enumerated(EnumType.STRING)
	 private IssueStatus status;
	 
}
