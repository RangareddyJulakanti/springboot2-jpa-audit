package com.ranga.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity(name = "STUDENT_TABLE")
@Table(name = "student")
@Getter
@Setter
public class Student  implements Serializable {
	@Id
	@Column(name = "id",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 5,message = "{FirstName.Is.Invalid}")
	@Column(name = "first_name", nullable = false)
	@JsonProperty("first_name")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@JsonProperty("last_name")
	private String lastName;

}
