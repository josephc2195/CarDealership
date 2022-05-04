package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.MakeDaoDB.MakeMapper;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.CarModel;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public CarModel addModel(int makeId, CarModel model) { // id: is makeId
        final String INSERT_MODEL = "INSERT INTO model(name, email, dateAdded, makeId) " +
                "VALUES(?,?,?,?)";

        jdbc.update(INSERT_MODEL, 
                model.getName(), 
                model.getEmail(), 
                model.getDate(), 
                makeId);
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(currId);
        
        return model;
    }
    
    @Override
    public CarModel getModelById(int modelId) {
        final String SELECT_MODELID = "SELECT * FROM model WHERE id = ?";

        // model id not exist
        try {
            CarModel model = jdbc.queryForObject(SELECT_MODELID, new ModelMapper(), modelId);
            model.setMake(getMakeForModel(model)); // populate make field in model class
            return model;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public CarModel getModelByName(String modelName) {
        final String SELECT_MODELID = "SELECT * FROM model WHERE name = ?";

        // model id not exist
        try {
            CarModel model = jdbc.queryForObject(SELECT_MODELID, new ModelMapper(), modelName);
            model.setMake(getMakeForModel(model)); // populate make field in model class
            return model;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<CarModel> getAllModels() {
        final String SELECT_ALL_MODELS = "SELECT * FROM model LIMIT 20";
        List<CarModel> models = jdbc.query(SELECT_ALL_MODELS, new ModelMapper());
        
        for(CarModel singleModel: models){
            singleModel.setMake(getMakeForModel(singleModel)); // populate make field in model class
        }
        return models;
    }

    // fetch and populate the Make field in Model class
    public Make getMakeForModel(CarModel model) {
        final String SELECT_MAKE_FOR_MODEL = "SELECT ma.* FROM make ma "
                + "JOIN model mo ON ma.id = mo.makeId WHERE mo.id = ?";
        return jdbc.queryForObject(SELECT_MAKE_FOR_MODEL, new MakeMapper(), model.getId());
    }
    
      public static final class ModelMapper implements RowMapper<CarModel> {

        @Override
        public CarModel mapRow(ResultSet rs, int index) throws SQLException {
            CarModel model = new CarModel();
            model.setId(rs.getInt("id"));
            model.setName(rs.getString("name"));
            model.setEmail(rs.getString("email"));
            model.setDate(rs.getDate("dateAdded").toLocalDate());
            
            return model;
        }   
    }
}
