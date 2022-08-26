create database authentication;
use authentication;
show databases;
CREATE TABLE Login(
username varchar( 30 ),
password varchar( 128 ) ,
emp_id int , emp_name varchar(30) 
);
desc Login;
ALTER TABLE login
ADD COLUMN Name VARCHAR(50),
ADD COLUMN ID int;
desc Login;

INSERT INTO Login(username,password,Name,ID) VALUES ('admin@intimetec','1234','abc',12);
INSERT INTO Login(username,password,Name,ID) VALUES ('userOT@domain.com','5678','xyz',123);
select * from Login;
desc login;
SET SQL_SAFE_UPDATES=0;
DELETE FROM login WHERE password='1234';
