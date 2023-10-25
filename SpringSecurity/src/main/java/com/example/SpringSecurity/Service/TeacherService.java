package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Exception.StudentAlreadyExistsException;
import com.example.SpringSecurity.Exception.StudentNotFoundException;
import com.example.SpringSecurity.Exception.TeacherAlreadyExistsException;
import com.example.SpringSecurity.Exception.TeacherNotFoundException;
import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Model.Teacher;
import com.example.SpringSecurity.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public Teacher addTeacher(Teacher teacher) {

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getTeacherId());
        if(optionalTeacher.isPresent()){
            throw new TeacherAlreadyExistsException("Teacher already exists");
        }

        Teacher newTeacher = new Teacher();
        newTeacher.setName(teacher.getName());
        newTeacher.setSubject(teacher.getSubject());
        newTeacher.setEmailId(teacher.getEmailId());

        Teacher savedTeacher = teacherRepository.save(newTeacher);

        return savedTeacher;
    }

    public Teacher getTeacher(int id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if(!optionalTeacher.isPresent()){
            throw new TeacherNotFoundException("Teacher with given id doesn`t exists");
        }
        return optionalTeacher.get();
    }
}
