package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.CarDao;
import com.sg.CarDealership.dao.MakeDao;
import com.sg.CarDealership.dao.ModelDao;
import com.sg.CarDealership.dao.PersonDao;
import com.sg.CarDealership.dao.SpecialsDao;
import com.sg.CarDealership.dao.UserDao;
import com.sg.CarDealership.dto.AggregateCar;
import com.sg.CarDealership.dto.AggregateSoldCar;
import com.sg.CarDealership.dto.Car;
import com.sg.CarDealership.dto.CarModel;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Person;
import com.sg.CarDealership.dto.Specials;
import com.sg.CarDealership.dto.AggregateUnsoldCar;
import com.sg.CarDealership.dto.User;
import java.util.List;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author westo
 */
@Controller
@RequestMapping("guildcars.com/")
public class MainController {

    private String newMakeName;
    private final CarDao carDao;
    private final SpecialsDao specialDao;
    private final UserDao userDao;
    private final PersonDao personDao;
    private final MakeDao makeDao;
    private final ModelDao modelDao;

    @Autowired
    public MainController(CarDao carDao, MakeDao makeDao, ModelDao modelDao, SpecialsDao specialDao, UserDao userDao, PersonDao personDao) {
        this.carDao = carDao;
        this.makeDao = makeDao;
        this.modelDao = modelDao;
        this.specialDao = specialDao;
        this.userDao = userDao;
        this.personDao = personDao;
    }

    @GetMapping("index")
    public String getFeaturedCars(Model model) {
        List<Car> cars = carDao.getFeaturedCars();
        model.addAttribute("cars", cars);
        return "index";
    }

    @GetMapping("inventoryNew")
    public String getNewInventory(Model model) {
        List<AggregateUnsoldCar> unsoldCars = carDao.getNewCars();
        model.addAttribute("unsoldCars", unsoldCars);
        return "inventoryNew";
    }

    @GetMapping("inventoryUsed")
    public String getUsedInventory(Model model) {
        List<AggregateUnsoldCar> unsoldCars = carDao.getUsedCars();
        model.addAttribute("unsoldCars", unsoldCars);
        return "inventoryUsed";
    }

    @GetMapping("carDetails")
    public String getCarDetails(@RequestParam(value = "carId", required = false) int carId, Model model) {
        Car car = carDao.getCarById(carId);
        model.addAttribute("car", car);
        return "carDetails";
    }

    @GetMapping("specials")
    public String getSpecials(Model model) {
        List<Specials> specials = specialDao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specials";
    }

    @GetMapping("contact")
    public String setContact(Model model) {
        //List<Car> cars = carDao.getUsedCars();
        //model.addAttribute("cars", cars);
        return "contact";
    }

    @GetMapping("specialsDetails")
    public String setSpecials(Model model) {
        List<Specials> specials = specialDao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specialsDetails";
    }

    @GetMapping("salesIndex")
    public String getSalesIndex(Model model) {
        List<AggregateUnsoldCar> unsoldCars = carDao.getUnsoldCars();
        model.addAttribute("unsoldCars", unsoldCars);
        return "salesIndex";
    }

    @GetMapping("addCar")
    public String addCar() {
        return "addCar";
    }
    @PostMapping("addCar")
    public String addCar(HttpServletRequest req){
        String make = req.getParameter("make");
        String model = req.getParameter("model");
        String type = req.getParameter("type");
        String body = req.getParameter("bodyStyle");
        String year = req.getParameter("year");
        String trans = req.getParameter("tranmission");
        String color = req.getParameter("color");
        String interior = req.getParameter("interior");
        String mileage = req.getParameter("mileage");
        String vin = req.getParameter("vin");
        String msrp = req.getParameter("msrp");
        String sales = req.getParameter("salesPrice");
        String description = req.getParameter("description");
        String picture = req.getParameter("picture");
        
        Car car = new Car();
        car.setBodyStyle(body);
        car.setColor(color);
        car.setDescription(description);
        car.setMileage(Integer.valueOf(mileage));
        car.setInterior(interior);
        car.setType(type);
        car.setYear(Integer.valueOf(year));
        car.setTransmission(trans);
        car.setVin(vin);
        car.setMsrp(Double.valueOf(msrp));
        car.setSalesPrice(Double.valueOf(sales));
        if(!makeDao.getAllMakes().contains(make)) {
            Make addedMake = new Make();
            addedMake.setName(make);
            addedMake.setDate(LocalDate.now());
            addedMake.setEmail("chicaj@gmail.com");
            makeDao.addMake(addedMake);
            if(!modelDao.getAllModels().contains(model)) {
                CarModel addedModel = new CarModel();
                addedModel.setDate(LocalDate.now());
                addedModel.setEmail("chicaj@gmail.com");
                addedModel.setMake(addedMake);
                addedModel.setName(make);
                modelDao.addModel(addedMake.getId(), addedModel);
            }
        } 
        if(!modelDao.getAllModels().contains(model)) {
                CarModel addedModel = new CarModel();
                addedModel.setDate(LocalDate.now());
                addedModel.setEmail("chicaj@gmail.com");
                addedModel.setMake(makeDao.getMakeByName(make));
                addedModel.setName(make);
                modelDao.addModel(makeDao.getMakeByName(make).getId(), addedModel);
                carDao.addCar(addedModel.getId(), car);
        }
        else{
            carDao.addCar(modelDao.getModelByName(model).getId(), car);
        }
        return "redirect:/guildcars.com/cars";
    }
    
    @GetMapping("addUser") 
    public String addUser() {
        return "addUser";
    }
    
    @PostMapping("addUser")
    public String addUser(HttpServletRequest req){
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String pw = req.getParameter("password");
        String confirmPw = req.getParameter("confirmPw");
        
        Person p1 = new Person();
        p1.setEmail(email);
        p1.setFirstName(firstName);
        p1.setLastName(lastName);
        User user = new User();
        user.setUsername(lastName + firstName);
        if(confirmPw.equals(pw)) {
            user.setPassword(pw);
        }
        personDao.addPerson(p1);
        user.setPerson(p1);
        user.setRole(role);
        userDao.addUser(p1.getId(), user);
        return "redirect:/guildcars.com/users";
    }

    @GetMapping("car")
    public String showCar() {
        return "car";
    }

    @GetMapping("changePassword")
    public String changePassword() {
        return "changePassword";
    }

    @GetMapping("editCar")
    public String editCar(){
        return "editCar";
    }
    @PostMapping("editCar")
    public String editCar(HttpServletRequest req){
        String make = req.getParameter("make");
        String model = req.getParameter("model");
        String type = req.getParameter("type");
        String body = req.getParameter("bodyStyle");
        String year = req.getParameter("year");
        String trans = req.getParameter("tranmission");
        String color = req.getParameter("color");
        String interior = req.getParameter("interior");
        String mileage = req.getParameter("mileage");
        String vin = req.getParameter("vin");
        String msrp = req.getParameter("msrp");
        String sales = req.getParameter("salesPrice");
        String description = req.getParameter("description");
        String picture = req.getParameter("picture");
        
        Car car = new Car();
        car.setBodyStyle(body);
        car.setColor(color);
        car.setDescription(description);
        car.setMileage(Integer.valueOf(mileage));
        car.setInterior(interior);
        car.setType(type);
        car.setYear(Integer.valueOf(year));
        car.setTransmission(trans);
        car.setVin(vin);
        car.setMsrp(Double.valueOf(msrp));
        car.setSalesPrice(Double.valueOf(sales));
        
        carDao.updateCar(car);
        return "editCar";
    }
    @GetMapping("editUser")
    public String editUser(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.getUserById(id);
        
        model.addAttribute("user", user);
        return "editUser";
    }
    @PostMapping("editUser") 
    public String editUser(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id")); 
        User user = userDao.getUserById(id);
        user.getPerson().setEmail(req.getParameter("email"));
        user.getPerson().setFirstName(req.getParameter("firstName"));
        user.getPerson().setLastName(req.getParameter("lastName"));
        user.setRole(req.getParameter("role"));
        String pw = req.getParameter("password");
        String confirmedPw = req.getParameter("confirmedPw");
        if(pw.equals(confirmedPw)) {
            user.setPassword(pw);
        }
        userDao.updateUser(user);
        return "redirect:/guildcars.com/users";
    }

    @GetMapping("inventoryReport")
    public String getInventoryReport(Model model) {
        List<AggregateCar> newCars = carDao.getAggregateNewCars();
        List<AggregateCar> usedCars = carDao.getAggregateUsedCars();

        model.addAttribute("newCars", newCars);
        model.addAttribute("usedCars", usedCars);

        return "inventoryReport";
    }

    @PostMapping("addMake")
    public String addMake(HttpServletRequest request) {
        newMakeName = request.getParameter("inputMakeName");
        Make make = new Make();
        make.setName(newMakeName);
        make.setDate(LocalDate.now());
        make.setEmail("admin@admin");
        makeDao.addMake(make);
        
        return "redirect:/make";
    }
    
    @GetMapping("make")
    public String getMake(Model model) {
               
        List<Make> makes = makeDao.getAllMakes();
        model.addAttribute("makes", makes);
        return "make";
    }
    
      @GetMapping("model")
    public String getModel(Model model) {
        List<CarModel> carModels = modelDao.getAllModels();
        model.addAttribute("carModels", carModels);
        return "model";
    }


    @GetMapping("addmodel")
    public String addModel() {
        return "redirect:/model";
    }

    @GetMapping("report")
    public String getReports() {
        return "report";
    }

    @GetMapping("salesRecords")
    public String getSalesRecords() {
        return "salesRecords";
    }

    @GetMapping("salesReport")
    public String getSalesReport(Model model) {
        List<AggregateSoldCar> soldCars = userDao.getSoldCars();
        model.addAttribute("soldCars", soldCars);
        return "salesReport";
    }

    @GetMapping("users")
    public String getUser(Model model){
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}

