create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices (name, price)
values ('ipad', 60.0),
       ('ifon', 50.0),
       ('redmi', 5000.0),
       ('ipad', 80.0),
       ('ifon', 90.0),
       ('redmi', 7000.0);
drop table devices;
drop table devices_people;
insert into people (name)
values ('kolya'),
       ('petya'),
       ('vasya');

insert into devices_people (device_id, people_id)
VALUES (1,3),
       (2,1),
       (3,2),
       (4,3),
       (5,1),
       (6,2);

select avg(price)
from devices;

select p.name,avg(price)
from devices as d
join devices_people dp on d.id = dp.device_id
join people p on dp.people_id = p.id
group by p.name
having avg(price) > 5000.0;

