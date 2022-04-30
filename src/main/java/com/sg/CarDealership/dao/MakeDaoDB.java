package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Make;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Weston Gavin, Joseph Chica &&  Ronald Gedeon; 
 gitRepo: https://github.com/josephc2195/CarDealership.git 
 Implementation of MakeDao Interface
 */
@Repository
public class MakeDaoDB implements MakeDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Make getMakeById(int makeId) {
        final String SELECT_MAKEID = "SELECT * FROM make WHERE id = ?";

        // make id does not exist
        try {
            return jdbc.queryForObject(SELECT_MAKEID, new MakeMapper(), makeId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Make> getAllMakes() {
        final String SELECT_ALL_MAKES = "SELECT * FROM make";
        return jdbc.query(SELECT_ALL_MAKES, new MakeMapper());
    }
    
    @Override
    public Make addMake(Make make) {
        final String INSERT_MAKE = "INSERT INTO make(name, email, dateAdded) " 
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_MAKE, 
                make.getName(), 
                make.getEmail(), 
                make.getDate());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setId(newId);
        return make;
    }
    
    public static final class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int index) throws SQLException {
            Make make = new Make();
            make.setId(rs.getInt("id"));
            make.setName(rs.getString("name"));
            make.setEmail(rs.getString("email"));
            make.setDate(rs.getDate("dateAdded").toLocalDate());
            
            return make;
        }
        
    }

}
