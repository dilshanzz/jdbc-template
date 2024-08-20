package com.example.jdbc_template.service;


import com.example.jdbc_template.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    public int save(Student student);

    public  int update(Student student, int id);

    public int delete(int id);

    public Student getStudent(int id);

    public List<Student> getAll();
}
