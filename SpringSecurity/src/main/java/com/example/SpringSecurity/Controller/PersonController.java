package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Person;
import com.example.SpringSecurity.Repository.PersonRepository;
import com.example.SpringSecurity.Service.PersonService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/addPerson")
//    @Hidden
    public ResponseEntity addPerson(@RequestBody Person person){
        try{
            Person personFromService = personService.addPerson(person);
            return new ResponseEntity(personFromService, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CREATED);
        }
    }
}
