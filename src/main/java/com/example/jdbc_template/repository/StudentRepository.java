package com.example.jdbc_template.repository;

import com.example.jdbc_template.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentRepository{



    int save(Student student, int id);

    int update(Student student, int id);

    int delete(int id);

    Student findById(int id);

    List<Student> findAll();


}
