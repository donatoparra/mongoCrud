package com.dparra.mongocrud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dparra.mongocrud.model.Student;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data!
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByStudentNumber(long studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpaDesc();

}
