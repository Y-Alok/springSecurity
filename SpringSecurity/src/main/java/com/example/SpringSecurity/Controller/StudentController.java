package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody Student student){
        try {
            Student studentFromService = studentService.addStudent(student);
            return new ResponseEntity(studentFromService, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/getStudent")
    @Operation(summary = "Get a student by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @Tag(name = "Get Student API", description = "Find student using id ...")
//    @SecurityRequirement(name = "api")
    public ResponseEntity getStudent(@RequestParam("id") int id){
        try {
            Student studentFromService = studentService.getStudent(id);
            return new ResponseEntity(studentFromService, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CREATED);
        }
    }

}
