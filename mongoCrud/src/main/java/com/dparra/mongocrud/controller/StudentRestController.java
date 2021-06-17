package com.dparra.mongocrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dparra.mongocrud.dto.StudentDTO;
import com.dparra.mongocrud.model.Student;
import com.dparra.mongocrud.service.StudentService;
import com.dparra.mongocrud.util.ObjectMapperUtils;

@RestController
@RequestMapping("/students")
public class StudentRestController {

	
    @Autowired
    private StudentService studentService;
    

    @GetMapping(value = "/")
    public List<StudentDTO> getAllStudents() {
        return ObjectMapperUtils.mapAll(studentService.findAll(), StudentDTO.class);
    }

    @GetMapping(value = "/byStudentNumber/{studentNumber}")
    public StudentDTO getStudentByStudentNumber(@PathVariable("studentNumber") Long studentNumber) {
        return ObjectMapperUtils.map(studentService.findByStudentNumber(studentNumber), StudentDTO.class);
    }

    @GetMapping(value = "/byEmail/{email}")
    public StudentDTO getStudentByEmail(@PathVariable("email") String email) {
        return ObjectMapperUtils.map(studentService.findByEmail(email), StudentDTO.class);
    }

    @GetMapping(value = "/orderByGpa")
    public List<StudentDTO> findAllByOrderByGpaDesc() {
        return ObjectMapperUtils.mapAll(studentService.findAllByOrderByGpaDesc(), StudentDTO.class);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateStudent(@RequestBody StudentDTO studentDTO) {
        Student s = studentService.saveOrUpdateStudent(ObjectMapperUtils.map(studentDTO, Student.class));
        
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateOrUpdateStudent(@RequestBody StudentDTO studentDTO) {
        Student s = studentService.saveOrUpdateStudent(ObjectMapperUtils.map(studentDTO, Student.class));
        
        return new ResponseEntity(s, HttpStatus.OK);
    }    

    @DeleteMapping(value = "/delete/{studentNumber}")
    public ResponseEntity<?> deleteStudentByStudentNumber(@PathVariable long studentNumber) {
        studentService.deleteStudentById(studentService.findByStudentNumber(studentNumber).getId());
        
        return new ResponseEntity("Student deleted successfully", HttpStatus.OK);
    }

}
