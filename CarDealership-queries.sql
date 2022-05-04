use carDealership;

select * from car limit 20;
select * from customer;
select * from make;
select * from model;
select * from person;
select * from sales;
select * from specials;
select * from `user`;

delete  from make where id = 4;
drop view aggregateCar;

create view aggregateNewCar as
select year, make.name as make, model.name as model, count(car.id) as count, sum(car.msrp) as stockValue
	from car 
    join model on model.id = car.modelId
    join make on make.id = model.makeId
    where car.type = "new"
    group by year, make, model
    limit 20;
    
create view aggregateUsedCar as
select year, make.name as make, model.name as model, count(car.id) as count, sum(car.msrp) as stockValue
	from car 
    join model on model.id = car.modelId
    join make on make.id = model.makeId
    where car.type = "used"
    group by year, make, model
    limit 20;    
    
create view unSoldCar as
select CONCAT(car.year, ' ', make.name, ' ', model.name) tag, bodyStyle, transmission, color, interior, mileage, vin, car.salesPrice as salesPrice, msrp
	from car 
    join model on model.id = car.modelId
    join make on make.id = model.makeId
    where car.available = 1
    limit 20;     
    
    create view aggregateSoldCar as
select CONCAT(person.firstName, ' ', person.lastName) as `User`, sum(sales.purchasePrice) as TotalSales, count(car.id) as TotalVehicles
	from car 
    join user on car.id = `user`.carId
    join person on person.id = user.personId
    join sales on sales.carId = `user`.carId
    group by `user`.id
    limit 20;   
    
select * from aggregateNewCar;

select * from aggregateUsedCar;    

select * from user;

UPDATE `user` 
SET 
    carId = 1
WHERE
    `user`.id = 2;