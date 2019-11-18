drop database if exists  hrms;

create database hrms;
use hrms;
create table employee(
id int primary key auto_increment,
emp_id varchar(32) not null unique,
name varchar(256) not null,
email varchar(256) unique,
mobile long,
joining_date date not null
);

insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-100','TEST', 'test@gmail.com', 90908106230, '2018-01-15');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-123','MARTIN', 'martin@gmail.com', 8768798760, '2018-02-01');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-455','TURNER', 'turner@gmail.com', 8795636259, '2018-05-03');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-456','ADAMS', 'adams@gmail.com', 9998886660, '2018-06-15');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-501','JAMES', 'james@gmail.com', 9595957545, '2018-06-23');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-529','MILLER', 'miller@gmail.com', 8495623156, '2018-07-02');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-723','SCOTT', 'scott@gmail.com', 8998965310, '2018-08-10');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-230','SMITH', 'smith@gmail.com', 6868685953, '2018-08-11');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-120','WARD', 'ward@gmail.com', 8889977551, '2018-08-12');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-125','ALLEN', 'allen@gmail.com', 77897568982, '2018-09-01');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-823','BLAKE', 'blake@gmail.com', 849756213, '2018-09-02');
insert into employee(emp_id,name,email,mobile,joining_date) values('ABC-924','FORD', 'ford@gmail.com', 9845623519, '2019-03-03');


SELECT * FROM hrms.employee;
