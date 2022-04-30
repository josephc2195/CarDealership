package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.MakeDaoDB.MakeMapper;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Model;
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
public class ModelDaoDB implements ModelDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    MakeDao makeDao;
    
    @Override
    public Model addModel(int makeId, Model model) { // id: is makeId
        final String INSERT_MODEL = "INSERT INTO model(name, email, dateAdded, makeId) " +
                "VALUES(?,?,?,?)";
/*       if (id == 0) {
            throw new InvalidRequestParametersException("Invalid parameters - Missing  makeId");
*/         
        // check if make id exists.
        Make make = makeDao.getMakeById(makeId);
        if (make == null) {
            System.out.println("Make doesn't exists"); // throw new MakeNotFoundException(mameId);
        }

        jdbc.update(INSERT_MODEL, 
                model.getName(), 
                model.getEmail(), 
                model.getDate(), 
                model.getMake().getId());
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(currId);
        
        return model;
    }

    @Override
    public List<Model> getAllModels() {
        final String SELECT_ALL_MODELS = "SELECT * FROM model";
        List<Model> models = jdbc.query(SELECT_ALL_MODELS, new ModelMapper());
        
        for(Model model: models){
            getMakeForModel(model);
        }
        return models;
    }

    // fetch and populate the Make field in Car class
    private Make getMakeForModel(Model model) {
        final String SELECT_MAKE_FOR_CAR = "SELECT m.* FROM make m "
                + "JOIN model mo ON m.id = mo.makeId WHERE mo.id = ?";
        return jdbc.queryForObject(SELECT_MAKE_FOR_CAR, new MakeMapper(), model.getId());
    }
    
      public static final class ModelMapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int index) throws SQLException {
            Model model = new Model();
            model.setId(rs.getInt("id"));
            model.setName(rs.getString("name"));
            model.setEmail(rs.getString("email"));
            model.setDate(rs.getDate("dateAdded").toLocalDate());
            
            return model;
        }
        
    }
}
