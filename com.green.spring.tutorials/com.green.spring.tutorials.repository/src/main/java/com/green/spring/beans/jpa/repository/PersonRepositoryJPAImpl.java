package com.green.spring.beans.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.green.spring.beans.entity.Person;
import com.green.spring.beans.repository.PersonRepository;

@Repository("PersonRepositoryJPA")
@Transactional
public class PersonRepositoryJPAImpl implements PersonRepository{
	
	@PersistenceContext private EntityManager entityManager;

	@Override
	public void saveUpdate(Person person) {
		if(person.getId() == null){
			entityManager.persist(person);
		}else{
			entityManager.merge(person);
		}
		
	}

	@Override
	public void delete(Person person) {
		entityManager.remove(person);
	}

	@Override
	public Person findPersonById(Long id) {
		return entityManager.find(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAllPerson() {
		return entityManager.createQuery("from Person").getResultList();
	}

	@Override
	public Person findPersonByName(String name) {
		Query query = entityManager.createNamedQuery("findPersonByName");
		query.setParameter("name", name);
		return (Person) (query.getResultList() == null || query.getResultList().isEmpty() ? new Person() : query.getResultList().get(0));
	}

	@Override
	public Person findPersonReferenceById(Long id) {
		return entityManager.getReference(Person.class, id);
	}

}
