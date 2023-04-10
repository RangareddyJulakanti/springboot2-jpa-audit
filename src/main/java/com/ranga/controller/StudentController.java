package com.ranga.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/*import com.jpmchase.core.data.jpa.repository.helper.RepositoryHelper;
import com.jpmchase.core.validation.ValidCondor;*/
import com.ranga.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranga.exception.ResourceNotFoundException;
import com.ranga.model.Student;
import com.ranga.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public List<Student> getAllEmployees() {
		List<Student>  students = studentService.getAllEmployees();
		return students;
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Student student=studentService.getEmployeeById(employeeId);
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/students")
	public Student createEmployee(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}

}
