create table girls
(
    id   serial primary key,
    name varchar(255)
);

create table boys
(
    id   serial primary key,
    name varchar(255)
);

create table couples
(
    id   serial primary key,
    boy_id int references boys(id),
    girl_id int references girls(id)
);