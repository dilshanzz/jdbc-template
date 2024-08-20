package com.example.jdbc_template.service.impl;

import com.example.jdbc_template.model.Student;
import com.example.jdbc_template.repository.StudentRepository;
import com.example.jdbc_template.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
   private StudentRepository studentRepository;

    @Override
    public int save(Student student) {
        int id = generate();
        return studentRepository.save(student, id);
    }

    @Override
    public int update(Student student, int id) {
        return  studentRepository.update(student, id);
    }

    @Override
    public int delete(int id) {
        return studentRepository.delete(id);
    }

    @Override
    public Student getStudent(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public int generate(){
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            return 1;
        }
        Student lastStudent = students.get(students.size()-1);

       int id =   lastStudent.getId();

       return id+1;

    }

}
