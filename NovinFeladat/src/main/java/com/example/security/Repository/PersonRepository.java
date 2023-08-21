package com.example.security.Repository;

import com.example.security.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
}