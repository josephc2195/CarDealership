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
    featured tinyint not null,
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
(1, "Mazda", 'default@init.com', now()),
(2, "Subaru", 'init@default.com', now()),
(3, "Audi", 'default@finit.com', now()),
(4,"Ford",'1@3.com', now()),
(5,"Chevrolet",'1@4.com', now()),
(6,"Honda",'1@5.com', now()),
(7,"Mercedes",'1@6.com', now()),
(8,"Lincoln",'1@7.com', now()),
(9,"Mercury",'1@8.com', now()),
(10,"Dodge",'1@9.com', now()),
(11,"GMC",'1@10.com', now()),
(12,"Ram",'1@11.com', now()),
(13,"Aston Martin",'1@12.com', now()),
(14,"Lamborghini",'1@13.com', now()),
(15,"Maserati",'1@14.com', now()),
(16,"Saab",'1@15.com', now()),
(17,"Saturn",'1@16.com', now()),
(18,"Tesla",'1@17.com', now()),
(19,"Lucid",'1@18.com', now()),
(20,"Rivian",'1@19.com', now()),
(21,"Nissan",'1@20.com', now()),
(22,"Toyota",'1@21.com', now())
;

insert into model(id, `name`, email, dateAdded, makeId) 
values 
(1, "CX5", 'default@init.com', now(), 1),
(2, "Outback", 'init@default.com', now(), 2),
(3, "Q5", 'default@finit.com', now(), 3),
(4,"F-150",'1@3.com', now(), 4),
(5,"Cobalt",'1@4.com', now(), 5),
(6,"Accord",'1@5.com', now(), 6),
(7,"Benz",'1@6.com', now(), 7),
(8,"MKZ",'1@7.com', now(), 8),
(9,"Cougar",'1@8.com', now(), 9),
(10,"Durango",'1@9.com', now(), 10),
(11,"Sierra",'1@10.com', now(), 11),
(12,"1500",'1@11.com', now(), 12),
(13,"DB 11",'1@12.com', now(), 13),
(14,"Gallardo",'1@13.com', now(), 14),
(15,"Ghibli",'1@14.com', now(), 15),
(16,"9-3",'1@15.com', now(), 16),
(17,"Skye",'1@16.com', now(), 17),
(18,"Model X",'1@17.com', now(), 18),
(19,"Air",'1@18.com', now(), 19),
(20,"R1T",'1@19.com', now(), 20),
(21,"Altima",'1@20.com', now(), 21),
(22,"Corolla",'1@21.com', now(), 22),
(23,"Frontier", '2@22.com', now(), 21),
(24,"Titan", '2@23.com', now(), 21),
(25,"Pinto", '1@24.com', now(), 4);

insert into car(id, `year`, `type`, bodyStyle, interior, color, mileage, transmission, vin, msrp, salesPrice, available, featured, modelId, picture) 
values 
(1, 2022, 'new', 'suv', 'dark gray', 'blue', 23, 'auto', 'xbtznda8qbt7bg1p5', 65395.00, 63000.00, 1, 0, 3, "inventory-1"),
(2, 2022, 'new', 'suv', 'black', 'navy', 67, 'auto', '7fpdj8e8bz201y9jx', 30800.00, 30000.00, 1, 1, 2, "inventory-2"),
(3, 2019, 'used', 'suv', 'black', 'silver', 23000, 'auto', '6kezb8mzpq7h9jhpd', 18000.00, 17000.00, 1, 1, 1, "inventory-3"),
(4, 2022, 'new', 'truck', 'tan', 'white', 1, 'auto', '7fpdj8e8bz201y9jy', 51800.00, 50000.00, 1, 1, 4, "inventory-4"),
(5, 2022, 'new', 'coupe', 'black', 'navy', 2, 'manual', '7fpdj8e8bz201y9ax', 30800.00, 27000.00, 1, 1, 5, "inventory-5"),
(6, 2012, 'used', 'sedan', 'black', 'navy', 1001, 'auto', '7fpdj8e8bz201y9ja', 20800.00, 18000.00, 1, 0, 6, "inventory-6"),
(7, 2022, 'new', 'sedan', 'black', 'black', 3, 'auto', '7fpdj8e8bz200y9jb', 50800.00, 46000.00, 1, 1, 7, "inventory-7"),
(8, 2018, 'used', 'sedan', 'black', 'navy', 1002, 'auto', '7fpdj8e8bz200y9jc', 30800.00, 29000.00, 1, 0, 8, "inventory-8"),
(9, 1998, 'used', 'coupe', 'black', 'navy', 400000, 'auto', '7fpdj8e8bz201y9jd', 3800.00, 3000.00, 1, 1, 9, "inventory-9"),
(10, 2022, 'new', 'suv', 'black', 'navy', 5, 'auto', '7fpdj8e8bz201y9je', 40800.00, 38000.00, 0, 10, 1, "inventory-10"),
(11, 2020, 'used', 'truck', 'black', 'navy', 10003, 'auto', '7fpdj8e8bz201y9jf', 38000.00, 35000.00, 1, 0, 11, "inventory-11"),
(12, 2022, 'new', 'truck', 'black', 'navy', 6, 'auto', '7fpdj8e8bz201y9jg', 41800.00, 38000.00, 1, 0, 12, "inventory-12"),
(13, 2018, 'used', 'coupe', 'black', 'navy', 20004, 'auto', '7fpdj8e8bz201y9jh', 98000.00, 90000.00, 1, 0, 13, "inventory-13"),
(14, 1999, 'used', 'coupe', 'black', 'navy', 15000, 'auto', '7fpdj8e8bz201y9px', 30800.00, 30000.00, 1, 0, 14, "inventory-14"),
(15, 2022, 'new', 'suv', 'black', 'navy', 5, 'auto', '7fpdj8e8bz201y9jj', 78800.00, 75000.00, 1, 0, 15, "inventory-15"),
(16, 2022, 'new', 'sedan', 'black', 'navy', 8, 'auto', '7fpdj8e8bz201y9jk', 52800.00, 50000.00, 1, 0, 16, "inventory-16"),
(17, 2004, 'used', 'sedan', 'black', 'navy', 12000, 'auto', '7fpdj8e8bz201y9fx', 18800.00, 16000.00, 1, 0, 17, "inventory-17"),
(18, 2017, 'used', 'suv', 'black', 'navy', 22000, 'auto', '7fpdj8e8bz201y9jm', 50800.00, 49000.00, 1, 0, 18, "inventory-18"),
(19, 2021, 'used', 'sedan', 'black', 'navy', 32000, 'auto', '7fpdj8e8bz201y9jn', 77800.00, 76000.00, 1, 0, 19, "inventory-19"),
(20, 2021, 'used', 'truck', 'black', 'navy', 15000, 'auto', '7fpdj8e8bz201y9jp', 67800.00, 65000.00, 1, 0, 20, "inventory-20"),
(21, 2022, 'new', 'sedan', 'black', 'navy', 9, 'auto', '7fpdj8e8bz201y9jr', 30800.00, 30000.00, 1, 0, 22, "inventory-21"),
(22, 2022, 'new', 'truck', 'black', 'navy', 12, 'auto', '7fpdj8e8bz201y9js', 36800.00, 35000.00, 1, 1, 23, "inventory-22"),
(23, 2022, 'new', 'truck', 'black', 'navy', 13, 'auto', '7fpdj8e8bz201y9jt', 48800.00, 45000.00, 1, 1, 24, "inventory-23"),
(24, 1984, 'used', 'coupe', 'black', 'navy', 460000, 'auto', '7fpdj8e8bz201y9ju', 1800.00, 1200.00, 1, 0, 25, "inventory-24"),
(25, 2022, 'new', 'sedan', 'black', 'navy', 14, 'auto', '7fpdj8e8bz201y9jv', 34800.00, 33000.00, 1, 0, 21, "inventory-25"),
(26, 2012, 'used', 'suv', 'black', 'navy', 47000, 'auto', '7fpdj8e8bz201y9jw', 13800.00, 12000.00, 1, 0, 2, "inventory-26"),
(27, 2022, 'new', 'suv', 'black', 'navy', 115, 'auto', '7fpdj8e8ba201y9jx', 65800.00, 63000.00, 1, 0, 3, "inventory-27"),
(28, 2022, 'new', 'truck', 'black', 'navy', 255, 'auto', '7fpdj8e8bb201y9jx', 61800.00, 58000.00, 1, 0, 4, "inventory-28"),
(29, 2012, 'used', 'coupe', 'black', 'navy', 55000, 'auto', '7fpdj8e8bc201y9jx', 12800.00, 10000.00, 1, 0, 5, "inventory-29"),
(30, 2022, 'new', 'sedan', 'black', 'navy', 565, 'auto', '7fpdj8e8bd201y9jx', 30800.00, 30000.00, 1, 0, 6, "inventory-30"),
(31, 2022, 'new', 'sedan', 'black', 'navy', 500, 'auto', '7fpdj8e8be201y9jx', 60800.00, 58000.00, 1, 0, 7, "inventory-31"),
(32, 2021, 'used', 'sedan', 'black', 'navy', 15000, 'auto', '7fpdj8e8bf201y9jx', 28800.00, 26000.00, 1, 0, 8, "inventory-32"),
(33, 2022, 'new', 'suv', 'black', 'navy', 300, 'auto', '7fpdj8e8bg201y9jx', 77800.00, 73000.00, 1, 0, 18, "inventory-33"),
(34, 2022, 'new', 'suv', 'black', 'navy', 250, 'auto', '7fpdj8e8bh201y9jx', 40800.00, 38000.00, 1, 0, 10, "inventory-34"),
(35, 2013, 'used', 'truck', 'black', 'navy', 150000, 'auto', '7fpdj8e8bi201y9jx', 25800.00, 23000.00, 1, 0, 11, "inventory-35"),
(36, 2022, 'new', 'truck', 'black', 'navy', 300, 'auto', '7fpdj8e8bj201y9jx', 40800.00, 39000.00, 1, 0, 12, "inventory-36"),
(37, 2022, 'new', 'coupe', 'black', 'navy', 800, 'auto', '7fpdj8e8bk201y9jx', 98800.00, 96000.00, 1, 0, 13, "inventory-37"),
(38, 2003, 'used', 'coupe', 'black', 'navy', 25000, 'auto', '7fpdj8e8bl201y9jx', 30800.00, 30000.00, 1, 0, 14, "inventory-38"),
(39, 2022, 'new', 'sedan', 'black', 'navy', 954, 'auto', '7fpdj8e8bm201y9jx', 80800.00, 77000.00, 1, 0, 15, "inventory-39"),
(40, 2018, 'used', 'coupe', 'black', 'navy', 20000, 'auto', '7fpdj8e8bn201y9jx', 40800.00, 39000.00, 1, 0, 16, "inventory-40"),
(41, 2005, 'used', 'coupe', 'black', 'navy', 25001, 'auto', '7fpdj8e8bp201y9jx', 21800.00, 19000.00, 1, 0, 17, "inventory-41"),
(42, 2018, 'used', 'suv', 'black', 'navy', 29999, 'auto', '7fpdj8e8br201y9jx', 55800.00, 50000.00, 1, 0, 18, "inventory-42"),
(43, 2021, 'used', 'sedan', 'black', 'navy', 30000, 'auto', '7fpdj8e8bs201y9jx', 45800.00, 43000.00, 1, 0, 19, "inventory-43"),
(44, 2021, 'used', 'truck', 'black', 'navy', 30001, 'auto', '7fpdj8e8bt201y9jx', 58800.00, 55000.00, 1, 0, 20, "inventory-44"),
(45, 2017, 'used', 'sedan', 'black', 'navy', 80000, 'auto', '7fpdj8e8bu201y9jx', 21800.00, 19000.00, 1, 0, 21, "inventory-45"),
(46, 2018, 'used', 'sedan', 'black', 'navy', 18000, 'auto', '7fpdj8e8bv201y9jx', 23800.00, 22000.00, 1, 0, 22, "inventory-46")
;

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
(2, 'Special #2', 'Too good to be true special'),
(3, 'Special #3', 'Unbeleavably amazing special'),
(4, 'Special #4', 'Unbeleavably amazing special'),
(5, 'Special #5', 'Unbeleavably amazing special'),
(6, 'Special #6', 'Unbeleavably amazing special'),
(7, 'Special #7', 'Unbeleavably amazing special'),
(8, 'Special #8', 'Unbeleavably amazing special'),
(9, 'Special #9', 'Unbeleavably amazing special'),
(10, 'Special #10', 'Unbeleavably amazing special'),
(11, 'Special #11', 'Unbeleavably amazing special');





