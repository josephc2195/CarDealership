drop database if exists CarDealership;
create database CarDealership;
 
use CarDealership;

create table make (
	id int not null AUTO_INCREMENT,
	`name` varchar(30) not null,
    email varchar(50) not null,
    dateAdded datetime not null,
    constraint pk_make 
		primary key(id)
);

create table model(
	id int not null AUTO_INCREMENT, 
    `name` varchar(30) not null,
    email varchar(50) not null, 
    dateAdded datetime not null,
    makeId int not null,
    constraint pk_model
		primary key(id),
	constraint fk_modelmake
		foreign key(makeId)
        references make(id)
);

create table car (
	id int not null AUTO_INCREMENT,
	`year` int not null, 
    `type` varchar(4) not null,
    bodyStyle varchar(10) not null, 
    interior varchar(15) not null,
    color varchar(15) not null,
    mileage int not null,
    transmission varchar(10) not null,
    vin char(17) not null,
    msrp decimal(7,2) not null,
    salesPrice decimal(7,2) not null,
    `description` varchar(255),
    picture varchar(25),
    available tinyint not null, 
    modelId int not null,
    constraint pk_car 
		primary key(id),
    constraint fk_carmodel
		foreign key(modelId)
        references model(id)    
);

create table person (
	id int not null AUTO_INCREMENT,
    firstName varchar(30) not null,
    lastName varchar(50) not null,
    email varchar(50),
	phone varchar(20) not null,
    address varchar(100),
    constraint pk_person
		primary key(id)
);

create table `user` (
	id int not null AUTO_INCREMENT,
    `role` varchar(20) not null,
    username varchar(30) not null,
    `password` varchar(255) not null,
    personId int not null,
    constraint pk_user
		primary key(id),
    constraint fk_userperson
		foreign key(personId)
        references person(id)    
);

create table customer (
	id int not null AUTO_INCREMENT, 
    message varchar(255) not null,
    personId int not null,
    constraint pk_customer
		primary key(id),
    constraint fk_customerperson
		foreign key(personId)
		references person(id)    
);

create table sales (
	id int not null AUTO_INCREMENT, 
    purchasePrice decimal(7,2) not null,
    purchaseType varchar(25) not null,
    purchaseDate datetime not null,
    carId int not null,
    customerId int not null,
    constraint pk_sales
		primary key(id),
	constraint fk_carsales
		foreign key(carId)
        references car(id),
	constraint fk_salesCustomer
		foreign key(customerId)
        references customer(id)
);

create table specials(
	id int not null AUTO_INCREMENT,
    title varchar(30) not null, 
    `description` varchar(255) not null,
    constraint pk_specials
		primary key(id)
);

insert into make(id, `name`, email, dateAdded) 
values 
(1, 'Mazda', 'default@init.com', now()),
(2, 'Subaru', 'init@default.com', now()),
(3, 'Audi', 'default@finit.com', now());

insert into model(id, `name`, email, dateAdded, makeId) 
values 
(1, 'CX5', 'default@init.com', now(), 1),
(2, 'Outback', 'init@default.com', now(), 2),
(3, 'Q5', 'default@finit.com', now(), 3);

insert into car(id, `year`, `type`, bodyStyle, interior, color, mileage, transmission, vin, msrp, salesPrice, available, modelId) 
values 
(1, 2022, 'new', 'sedan', 'dark gray', 'blue', 23, 'auto', 'xbtznda8qbt7bg1p5', 25395.00, 23000.00, 1, 3),
(2, 2022, 'new', 'suv', 'black', 'navy', 67, 'auto', '7fpdj8e8bz201y9jx', 30800.00, 30000.00, 1, 2),
(3, 2019, 'used', 'suv', 'black', 'silver', 23000, 'auto', '6kezb8mzpq7h9jhpd', 18000.00, 17000.00, 1, 1);

insert into person(id, firstName, lastName, email, phone, address) 
values 
(1, 'Joseph', 'Chica', 'josephc@init.com', '+1(516) 408-5837', '707 Madison Av. NY 41105'),
(2, 'Weston', 'Gavin', 'wstong@init.com', '+1(305) 904-8025', '63282 Rotuma Street. Florida 32757'),
(3, 'Ronald', 'Gedeon', 'ronaldg@init.com', '+1(514) 490-5429', '5-3461 rue Mousseau, Montreal Qc H1K 2W3'),
(4, 'First', 'Customer', 'fcustomer@test.com', '+1(888) 444-3333', '221B Baker Street, London UK NW1 6XE'),
(5, 'Second', 'Customerx', 'scustomer@test.com', '+1(816) 555-7777', '1600 Pennsylvania Av. NW, Washington, DC 20500');

insert into user(id, `role`, username, `password`, personId) 
values 
(1, 'user', 'joseph', 'jpw2022', 1),
(2, 'user', 'weston', 'wpw2022', 2),
(3, 'user', 'ronald', 'rpw2022', 3);

insert into customer(id, message, personId) 
values 
(1, 'Sherlock Holmes', 4),
(2, 'White House Perosnnel', 5);

insert into sales(id, purchasePrice, purchaseType, purchaseDate, carId, customerId) 
values 
(1, 24125.25, 'Cash', now(), 1, 1),
(2, 29260.00, 'Bank Finance', now(), 2, 2);

insert into specials(id, title, `description`) 
values 
(1, 'Special #1', 'Unbeleavably amazing special'),
(2, 'Special #2', 'Too good to be true special');


