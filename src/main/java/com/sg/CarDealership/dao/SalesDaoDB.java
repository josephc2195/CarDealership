package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.CarDaoDB.CarMapper;
import com.sg.CarDealership.dao.CustomerDaoDB.CustomerMapper;
import com.sg.CarDealership.dto.Car;
import com.sg.CarDealership.dto.Customer;
import com.sg.CarDealership.dto.CarModel;
import com.sg.CarDealership.dto.Person;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.sg.CarDealership.dto.Sales;
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
public class SalesDaoDB implements SalesDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    CarDaoDB carDaoDB;
    
    @Autowired
    ModelDaoDB modelDaoDB;
    
    @Autowired
    CustomerDaoDB customerDaoDB;
    
    @Override
    public Sales addSales(int carId, int customerId, Sales sales) {
        final String INSERT_SALES = "INSERT INTO sales(purchasePrice, purchaseType, purchaseDate, carId, customerId) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_SALES, 
                sales.getPurchasePrice(),
                sales.getPurchaseType(),
                sales.getPurchaseDate(),
                carId,
                customerId);
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sales.setId(currId);
        return sales;
    }

    @Override
    public List<Sales> getAllSales() {
        final String SELECT_ALL_SALES = "SELECT * FROM sales";
        List<Sales> salesList = jdbc.query(SELECT_ALL_SALES, new SalesMapper());
        
        for(Sales sales: salesList){
            
            Car car = getCarForSales(sales);
            CarModel model = carDaoDB.getModelForCar(car);
            model.setMake(modelDaoDB.getMakeForModel(model));
            car.setModel(model); // populate model field in Car class
            sales.setCar(car); // populate car field in Sales class
            
            Customer customer = getCustomerForSales(sales);
            Person person = customerDaoDB.getPersonForCustomer(customer);
            customer.setPerson(person); // populate person field in Customer class
            sales.setCustomer(customer); // populate customer field in Sales class
        }
        return salesList;
    }
    
    // fetch and populate the car field in sales class
    private Car getCarForSales(Sales sales) {
        final String SELECT_CAR_FOR_SALES = "SELECT c.* FROM car c "
                + "JOIN sales s ON c.id = s.carId WHERE s.id = ?";
        return jdbc.queryForObject(SELECT_CAR_FOR_SALES, new CarMapper(), sales.getId());
    }
    
    // fetch and populate the customer field in sales class
    private Customer getCustomerForSales(Sales sales) {
        final String SELECT_CUSTOMER_FOR_SALES = "SELECT c.* FROM customer c "
                + "JOIN sales s ON c.id = s.customerId WHERE s.id = ?";
        return jdbc.queryForObject(SELECT_CUSTOMER_FOR_SALES, new CustomerMapper(), sales.getId());
    }
    
    // Specific field to Customer class
    public static final class SalesMapper implements RowMapper<Sales> {

        @Override
        public Sales mapRow(ResultSet rs, int index) throws SQLException {
            Sales sales = new Sales();
            sales.setId(rs.getInt("id"));
            sales.setPurchasePrice(rs.getDouble("purchasePrice"));
            sales.setPurchaseType(rs.getString("purchaseType"));
            sales.setPurchaseDate(rs.getDate("purchaseDate").toLocalDate());
            
            return sales;
        }
    }
}
