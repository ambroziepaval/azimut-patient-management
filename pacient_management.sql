drop table pacient_file;
drop table appointment;
drop table pacient;
drop table medic;
drop table user;

create table user (
	id int auto_increment,
    username varchar(50) not null,
    password varchar(50) not null,
    email varchar(50) not null,
    type varchar(30) not null,
    primary key(id)
);
insert into user(username, password, email, type) value ('drHouse', 'password', 'drHouse@mailinator.com', 'MEDIC');
insert into user(username, password, email, type) value ('john.doe', 'password', 'john.doe@mailinator.com', 'PACIENT');
insert into user(username, password, email, type) value ('jane.doe', 'password', 'jane.doe@mailinator.com', 'PACIENT');
insert into user(username, password, email, type) value ('johnny.doe', 'password', 'johnny.doe@mailinator.com', 'PACIENT');
select * from user;
alter table user add constraint username_uc unique (username);

create table medic (
	id int auto_increment,
    last_name varchar(50) not null,
    first_name varchar(50) not null,
    birth_date date,
    specialty varchar(100) not null,
    user_id int not null,
    foreign key(user_id) references user(id),
    primary key(id)
);
insert into medic(last_name, first_name, birth_date, specialty, user_id) values ('House', 'Gregory', '1989-06-11', 'Diagnostician', 1);
select * from medic;
alter table medic add constraint user_id_uc unique (user_id);

create table patient (
	id int auto_increment,
    last_name varchar(50) not null,
    first_name varchar(50) not null,
    birth_date date,
    user_id int not null,
    foreign key(user_id) references user(id),
    primary key(id)
);
insert into patient(last_name, first_name, birth_date, user_id) values ('Doe', 'John', '1993-08-29', 2);
insert into patient(last_name, first_name, birth_date, user_id) values ('Doe', 'Jane', '2001-08-29', 6);
insert into patient(last_name, first_name, birth_date, user_id) values ('Doe', 'Johnny', '2010-10-10', 7);
select * from patient;
alter table patient add constraint user_id_uc unique (user_id);


create table appointment (
	id int auto_increment,
    patient_id int not null,
    medic_id int not null,
    appointment_date datetime not null,
    completed boolean not null default false,
    foreign key(patient_id) references patient(id),
    foreign key(medic_id) references medic(id),
    primary key(id)
);
insert into appointment(patient_id, medic_id, appointment_date) values (1, 1, '2021-07-03 10:00');
select * from appointment;

create table patient_file (
	id int auto_increment,
    patient_id int not null,
    medic_id int not null,
    diagnostic varchar(255),
    treatment varchar(255),
    foreign key(patient_id) references patient(id),
    foreign key(medic_id) references medic(id),
    primary key(id)
);
insert into patient_file(patient_id, medic_id) values (1,1);
insert into patient_file(patient_id, medic_id) values (3,1);
insert into patient_file(patient_id, medic_id) values (4,1);
select * from patient_file;

select exists(
	select * from user where username = 'drHouse' and password = 'password'
);
select exists(
	select * from user 
    where username = 'drHouse' 
    and password = 'wrong_password'
) as correct_credentials;