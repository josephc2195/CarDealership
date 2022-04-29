drop database if exists CarDealership;
create database CarDealership;
 
use CarDealership;

create table car (
	id int not null,
	year int not null, 
    make varchar(15) not null,
    model varchar(15) not null,
    type varchar(4) not null,
    color varchar(15) not null,
    mileage int not null,
    transmision varchar(10) not null,
    vin char(17) not null,
    msrp decimal(7,2) not null,
    salesPrice decimal(7,2) not null,
    description varchar(255),
    picture varchar(25),
    available tinyint not null, 
    constraint pk_car 
		primary key(id)
);

create table user (
	id int not null,
    role varchar(20) not null,
    username varchar(20) not null,
    pw varchar(255) not null,
    constraint pk_user
		primary key(id)
);

create table customer (
	id int not null, 
    message varchar(255) not null,
    constraint pk_user
		primary key(id)
);

create table person (
	id int not null,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    email varchar(20),
	phone varchar(12) not null,
    address varchar(100),
    userId int,
    customerId int, 
    constraint pk_person
		primary key(id),
	constraint fk_user
		foreign key(userId)
        references user(id),
	constraint fk_customer
		foreign key(customerId)
		references customer(id)
);

create table sales (
	id int not null, 
    purchasePrice decimal(7,2) not null,
    purchaseType varchar(20) not null,
    carId int not null,
    customerId int not null,
    purchaseDate date not null,
    constraint pk_sales
		primary key(id),
	constraint fk_car
		foreign key(carId)
        references car(id),
	constraint fk_salesCustomer
		foreign key(customerId)
        references customer(id)
);

create table specials(
	id int not null,
    title varchar(255) not null, 
    description varchar(255) not null
);

