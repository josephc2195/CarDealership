package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Utility class used in DB a utility table to refactor entities common properties
 */
@Repository
public class PersonDaoDB implements PersonDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Person getPersonById(int personId) {
        final String SELECT_PERSONID = "SELECT * FROM person WHERE id = ?";

        // if person id does not exist
        try {
            return jdbc.queryForObject(SELECT_PERSONID, new PersonMapper(), personId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Person> getAllPersons() {
        final String SELECT_ALL_PERSONS = "SELECT * FROM person LIMIT 20";
        List<Person> persons = jdbc.query(SELECT_ALL_PERSONS, new PersonMapper());
        
        return persons;
    }
    
    @Override
    public Person addPerson(Person person) {
        final String INSERT_PERSON = "INSERT INTO person(firstName, lastName, email, phone, address) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_PERSON, 
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhone(),
                person.getAddress());
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        person.setId(currId);
        return person;
    }

    public static final class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int index) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setFirstName(rs.getString("firstName"));
            person.setLastName(rs.getString("lastName"));
            person.setEmail(rs.getString("email"));
            person.setPhone(rs.getString("phone"));
            person.setAddress(rs.getString("address"));
            
            return person;
        }
        
    }
}
