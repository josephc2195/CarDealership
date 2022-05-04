package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.PersonDaoDB.PersonMapper;
import com.sg.CarDealership.dto.Customer;
import com.sg.CarDealership.dto.Person;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of a class ... on month day, year
 */
@Repository
public class CustomerDaoDB implements CustomerDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Customer addCustomer(int personId, Customer customer) {
        final String INSERT_CUSTOMER = "INSERT INTO customer(message, personId) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_CUSTOMER, 
                customer.getMessage(),
                personId);
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        customer.setId(currId);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer LIMIT 20";
        List<Customer> customers = jdbc.query(SELECT_ALL_CUSTOMERS, new CustomerMapper());
        
        for(Customer customer: customers){
            Person person = getPersonForCustomer(customer);
            customer.setPerson(person); // populate person field in Customer class
        }
        return customers;
    }
    
    // fetch and populate the Person field in Customer class
    public Person getPersonForCustomer(Customer customer) {
        final String SELECT_PERSON_FOR_CUSTOMER = "SELECT p.* FROM person p "
                + "JOIN customer c ON p.id = c.personId WHERE c.id = ?";
        return jdbc.queryForObject(SELECT_PERSON_FOR_CUSTOMER, new PersonMapper(), customer.getId());
    }
    
    // Specific field to Customer class
    public static final class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int index) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setMessage(rs.getString("message"));
            
            return customer;
        }
    }
}
