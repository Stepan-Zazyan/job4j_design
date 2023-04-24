create table departments
(
    id   serial primary key,
    name text unique
);

create table employees
(
    id            serial primary key,
    name          text,
    department_id int references departments (id)
);

insert into departments (name)
values ('IT'),
       ('Marketing'),
       ('Supply chain');

insert into employees (name, department_id)
values ('kolya', 1),
       ('petya', 2),
       ('vasya', 1),
       ('sveta', 2),
       ('tanya', 2);

select * from employees as e left join departments as d on e.department_id = d.id;
select * from departments as d left join employees as e on e.department_id = d.id;

select * from employees as e right join departments as d on e.department_id = d.id;
select * from departments as d right join employees as e on e.department_id = d.id;

select * from employees as e full join departments as d on e.department_id = d.id;
select * from employees as e cross join departments as d;

select * from departments as d left join employees as e on e.department_id = d.id
where department_id is null;

select * from departments as d left join employees as e on e.department_id = d.id
where department_id = 2;

select * from departments as d right join employees as e on e.department_id = d.id
where department_id = 2;

create table teens (
    id serial primary key,
    name text,
    gender char
);

insert into teens (name, gender)
values ('kolya','m'),
       ('ira', 'f'),
       ('vasya', 'm'),
       ('sveta', 'f');

select tee.name, t.name from teens as tee cross join teens as t
where tee.gender!=t.gender;