package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findByUserName(String userName);
}
