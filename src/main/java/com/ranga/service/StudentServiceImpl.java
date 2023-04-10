package com.ranga.service;

/*import com.jpmchase.core.common.validation.BaseException;
import com.jpmchase.core.common.validation.ExceptionFactory;
import com.jpmchase.core.data.jpa.repository.exception.IntegrityViolationException;
import com.jpmchase.core.data.jpa.repository.helper.RepositoryHelper;*/
import com.ranga.model.Student;
import com.ranga.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
   //@Autowired
   // private RepositoryHelper repositoryHelper;
   // @Autowired
   // private ExceptionFactory exceptionFactory;

    @Override
    public List<Student> getAllEmployees() {
        return studentRepository.findAll();
    }

    @Override
    public Student getEmployeeById(Long employeeId) {
       Optional<Student> student= studentRepository.findById(employeeId);
         if(student.isPresent()){
             return student.get();
         }else{
           /*throw  exceptionFactory.createEx(InvalidDataException.class,"Student.Id.Not.Found.in.DB",new Object[]{
                   "id",employeeId
           });*/
         }
         return null;
      // return repositoryHelper.findOneNotNull(studentRepository,employeeId,Student.class);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
      /*  return repositoryHelper.saveAndFlush(studentRepository,student, (String constraintName, Throwable ref) -> {

                return exceptionFactory.wrap(ref, IntegrityViolationException.class,
                        "Store.Order.IntegrityViolation", new Object[]{
                                "constraintName", constraintName
                        });

        });*/

    }
}
