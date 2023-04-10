package com.ranga.repository;

import com.ranga.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    //select * from student where firstname=?1
    Optional<Student> findByFirstNameOrLastName(String fname,String lname);
}
