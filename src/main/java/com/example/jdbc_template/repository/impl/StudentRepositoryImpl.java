package com.example.jdbc_template.repository.impl;

import com.example.jdbc_template.model.Student;
import com.example.jdbc_template.repository.StudentRepository;
import com.example.jdbc_template.service.impl.StudentRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private JdbcTemplate jdbcTemplate;

    @Value("${students.saveStudent}")
    private  String saveStudent;

    @Value("${students.getAllStudents}")
    private String getAllStudents;

    @Value("${students.updateStudent}")
    private  String updateStudent;

    @Value("${students.findStudent}")
    private  String findStudent;

    @Value("${students.deleteStudents}")
    private  String deleteStudents;

    public  StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Student student, int id) {

        student.setId(id);
        String sql = saveStudent
                .replace(":id","'"+ student.getId() +"'")
                .replace(":name","'"+student.getName()+"'")
                .replace(":age","'"+student.getAge()+"'")
                .replace(":course","'"+student.getCourse()+"'");
        int effectedRows = jdbcTemplate.update(sql);

//        String verifySql = findStudent;
//        int rowcounychange = jdbcTemplate.queryForObject(verifySql, Integer.class,student.getId());

        return  effectedRows>0 ? 1 : 0 ;



    }

    @Override
    public int update(Student student, int id) {
        String sql = updateStudent;
        System.out.println("Updating student with ID: " + id);
        System.out.println("New values - Name: " + student.getName() + ", Age: " + student.getAge() + ", Course: " + student.getCourse());
        int affectedRows = jdbcTemplate.update(sql,
                student.getName(),
                student.getAge(),
                student.getCourse(),
                id
                );

        if(affectedRows > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = deleteStudents;
        int effectedRows = jdbcTemplate.update(sql,id);

        return  effectedRows > 0 ? 1 : 0 ;
    }

    @Override
    public Student findById(int id) {

        String sql = findStudent;

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                return student;
            });
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = getAllStudents;
        List<Student> students = jdbcTemplate.query(sql,getStudentRowMapper());
        return  students;
    }

    public RowMapper<Student> getStudentRowMapper() {
        return  new StudentRowMapper();
    }
}
