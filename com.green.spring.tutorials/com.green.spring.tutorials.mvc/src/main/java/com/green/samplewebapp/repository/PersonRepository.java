package com.green.samplewebapp.repository;

import com.green.samplewebapp.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository handling persistence
 * 
 * @author gaurav
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
