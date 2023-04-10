package com.ranga.service;

/*import com.jpmchase.core.common.validation.annotation.ValidateException;
import com.jpmchase.core.validation.ValidCondor;*/
import com.ranga.model.Student;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated
public interface StudentService {
    List<Student> getAllEmployees();

    Student getEmployeeById(Long employeeId);

    Student saveStudent(@Valid Student student);

}
