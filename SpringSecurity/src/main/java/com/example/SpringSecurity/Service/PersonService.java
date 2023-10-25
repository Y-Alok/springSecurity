package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Exception.PersonAlreadyExistsException;
import com.example.SpringSecurity.Model.Person;
import com.example.SpringSecurity.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Person addPerson(Person person) {

        Person optionalPerson = personRepository.findByUserName(person.getUserName());
        if(optionalPerson==null){
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            Person savedPerson = personRepository.save(person);
            return savedPerson;
        }
        throw new PersonAlreadyExistsException("Person already exists");
    }
}
