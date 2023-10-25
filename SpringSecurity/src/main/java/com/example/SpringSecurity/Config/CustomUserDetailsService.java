package com.example.SpringSecurity.Config;

import com.example.SpringSecurity.Model.Person;
import com.example.SpringSecurity.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = personRepository.findByUserName(username);

        if(person == null){
            //create your custom exception
            throw new RuntimeException("user doesn`t exist");
        }
     return new UserDetailsCreator(person);
    }
}
