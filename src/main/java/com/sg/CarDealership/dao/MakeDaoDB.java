package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Make;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Make> getAllMakes() {
        final String CMD = "SELECT * FROM make";
        return jdbc.query(CMD, new MakeMapper());
    }
    
    @Override
    public Make addMake(Make make) {
        final String CMD = "INSERT INTO make(`name`, email, dateAdded, carId) " 
                + "VALUES(?,?,?,?,?)";
        jdbc.update(CMD, make.getName(), make.getEmail(), make.getDate(), make.getCarId());
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setId(currId);
        return make;
    }
    
    public static final class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int rowNum) throws SQLException {
            Make make = new Make();
            make.setId(rs.getInt("id"));
            make.setDate(rs.getDate("date"));
            make.setName(rs.getString("`name`"));
            make.setEmail(rs.getString("email"));
            make.setCarId(rs.getInt("carId"));
            
            return make;
        }
        
    }


}
