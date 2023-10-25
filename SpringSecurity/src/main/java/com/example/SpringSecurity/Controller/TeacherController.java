package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Model.Teacher;
import com.example.SpringSecurity.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/addTeacher")
    public ResponseEntity addTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher teacherFromService = teacherService.addTeacher(teacher);
            return new ResponseEntity(teacherFromService, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/getTeacher")
    public ResponseEntity getTeacher(@RequestParam("id") int id) {
        try {
            Teacher teacherFromService = teacherService.getTeacher(id);
            return new ResponseEntity(teacherFromService, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CREATED);
        }
    }
}