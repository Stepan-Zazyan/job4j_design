create table car_bodies
(
    id   serial primary key,
    name text unique
);

create table car_engines
(
    id   serial primary key,
    name text unique
);

create table car_transmissions
(
    id   serial primary key,
    name text unique
);

create table cars
(
    id              serial primary key,
    name            text,
    body_id         int references car_bodies (id),
    engine_id       int references car_engines (id),
    transmission_id int references car_transmissions (id)
);

insert into car_bodies (name)
values ('седан'),
       ('хэтчбек'),
       ('фургон');

insert into car_engines (name)
values ('1.6'),
       ('1.8'),
       ('2.0'),
       ('3.0');


insert into car_transmissions (name)
values ('auto'),
       ('mechanic'),
       ('robot');


insert into cars (name, body_id, engine_id, transmission_id)
VALUES ('toyota camry', null, 1, 1),
       ('toyota corolla', 1, null, 2),
       ('toyota lobster', 1, 2, 2),
       ('toyota hipster', null, 2, null),
       ('kia rio', 1, 3, 2),
       ('kia cerato', 1, null, 2),
       ('kia lol', 1, 2, 2),
       ('kia boob', 1, 2, null);

select c.id, c.name, cb.name, ce.name, ct.name
from cars as c
         LEFT join car_bodies cb on c.body_id = cb.id
         left JOIN car_engines ce on ce.id = c.engine_id
         LEFT join car_transmissions ct on c.transmission_id = ct.id;

select b.name
from cars as c
         right join car_bodies as b on b.id = c.body_id
where c.body_id is null;


select e.name
from cars as c
         right join car_engines as e on e.id = c.engine_id
where c.engine_id is null;

select t.name
from cars as c
         right join car_transmissions as t on t.id = c.transmission_id
where c.transmission_id is null;

create view sedan_auto_transmission_sum
as
select cb.name as body_name, sum(cb.id)
from cars as c
         join car_bodies as cb on c.body_id = cb.id
join car_transmissions ct on c.transmission_id = ct.id
where transmission_id = 2
group by cb.name;

select  * from sedan_auto_transmission_sum;
