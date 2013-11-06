package com.green.spring.beans.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.green.spring.beans.entity.Person;
import com.green.spring.beans.repository.PersonRepository;

@Repository("PersonRepositoryJDBC")
public class PersonRepositoryJDBCImpl implements PersonRepository{
	
	private final class SinglePersonResultSetExtractor implements
			ResultSetExtractor<Person> {
		@Override
		public Person extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			rs.next();
			Person person = new Person();
			person.setId(rs.getLong("ID"));
			person.setName(rs.getString("NAME"));
			return person;
		}
	}

	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveUpdate(Person person) {
		String qry = null;
		if(person.getId() == null){
			qry = "insert into PERSON(NAME) values(?)";
			jdbcTemplate.update(qry, person.getName());
		}else{
			qry = "update PERSON set NAME = ? where ID = ?";
			jdbcTemplate.update(qry, person.getName(),person.getId());
		}
	}

	@Override
	public void delete(Person person) {
		this.jdbcTemplate.update("delete from PERSON where ID = ?", person.getId());
		
	}

	@Override
	public Person findPersonById(Long id) {
		String qry = "select * from PERSON where ID = ?";
		return this.jdbcTemplate.query(qry, new Object[]{id}, new SinglePersonResultSetExtractor());
	}

	@Override
	public Person findPersonReferenceById(Long id) {
		throw new UnsupportedOperationException("Not needed in JDBC");
	}

	@Override
	public List<Person> findAllPerson() {
		return this.jdbcTemplate.query("select * from PERSON", new RowMapper<Person>(){

			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person person = new Person();
				person.setId(rs.getLong("ID"));
				person.setName(rs.getString("NAME"));
				return person;
			}
			
		});
	}

	@Override
	public Person findPersonByName(String name) {
		String qry = "select * from PERSON where NAME = ?";
		return this.jdbcTemplate.query(qry, new Object[]{name}, new SinglePersonResultSetExtractor());
	}

}
