package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Exception.StudentAlreadyExistsException;
import com.example.SpringSecurity.Exception.StudentNotFoundException;
import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getEnrollmentNo());
        if(optionalStudent.isPresent()){
            throw new StudentAlreadyExistsException("Student already exists");
        }

        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setEmailId(student.getEmailId());
        newStudent.setBranch(student.getBranch());
        Student savedStudent = studentRepository.save(newStudent);

        return savedStudent;
    }

    public Student getStudent(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new StudentNotFoundException("Student with given id doesn`t exists");
        }
        return optionalStudent.get();
    }
}
