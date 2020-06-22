package com.ranga.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity(name = "STUDENT_TABLE")
@Table(name = "student")
@Data
@EntityListeners(PersistableEntityListener.class)
public class Student extends AbstractPersistable<Long> implements EAuditable.Create,EAuditable.Update {
	@Embedded
	private ECreateInfo createInfo;
	@Embedded
	private EUpdateInfo updateInfo;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", nullable = false)
	@JsonProperty("first_name")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@JsonProperty("last_name")
	private String lastName;

	@Column(name = "email_address", nullable = false)
	@JsonProperty("email_address")
	private String emailId;

	@Column(name = "branch", nullable =false)
	@JsonProperty("branch")
	private String branch;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}
}
