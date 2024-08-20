package com.example.jdbc_template.controller;


import com.example.jdbc_template.model.Student;
import com.example.jdbc_template.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public int addStudent(@RequestBody Student student) {
        System.out.println(student);
        return studentService.save(student);
    }

    @PatchMapping("/update/{id}")
    public int updateStudent(@PathVariable int id, @RequestBody Student student) {
        return  studentService.update(student, id);
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return  studentService.getAll();
    }

    @GetMapping("/{id}")
    public  Student getStudentById(@PathVariable int id) {
        return  studentService.getStudent(id);
    }

    @DeleteMapping("/{id}")
    public int deleteStudentById(@PathVariable int id) {
        return  studentService.delete(id);
    }





}
