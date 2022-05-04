package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Specials;
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
public class SpecialsDaoDB implements SpecialsDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Specials addSpecial(Specials special) {
        final String INSERT_SPECIALS = "INSERT INTO specials(title, description) " + 
                "VALUES(?, ?)";
        jdbc.update(INSERT_SPECIALS, 
                special.getTitle(), 
                special.getDescription());
        
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setId(currId);
        
        return special;

    }

    @Override
    public List<Specials> getAllSpecials() {
        final String SELECT_ALL_SPECIALS = "SELECT * FROM specials LIMIT 20";
        List<Specials> specials = jdbc.query(SELECT_ALL_SPECIALS, new SpecialMapper());
        
        return specials;
    }

    public static final class SpecialMapper implements RowMapper<Specials> {

        @Override
        public Specials mapRow(ResultSet rs, int index) throws SQLException {
            Specials special = new Specials();
            special.setId(rs.getInt("id"));
            special.setTitle(rs.getString("title"));
            special.setDescription(rs.getString("description"));
            
            return special;
        }
        
    }
}
