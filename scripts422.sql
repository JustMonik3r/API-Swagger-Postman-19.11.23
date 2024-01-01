create table car (
	id int primary key,
	car_brand text,
	car_model text,
	price int
);

create table person (
	id int primary key,
	person_name text,
	age int,
	have_license bool,
	car_id int,
	foreign key (car_id) references car(id)
);