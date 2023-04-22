<<<<<<< HEAD
select version();
create table flights (
                         id serial primary key,
                         model integer,
                         city text,
                         distance numeric
);
insert into flights (model, city, distance) values (134, 'ТУ', 500.0);
update flights
set model = 747;
select * from flights;
delete from flights;
=======

create table users (
    id serial primary key ,
    role_id int references roles(id)
);

create table roles (
    id serial primary key,
    role_name text,
    rule_id int references rules(id)
);

create table rules (
    id serial primary key,
    rule text
);

create table comments (
    id serial primary key,
    comment text
);

create table attachs (
    id serial primary key,
    attach text
);

create table category (
    id serial primary key,
    category int
);

create table state (
    id serial primary key,
    state text
);

create table item (
    id serial primary key,
    user_id int references users(id),
    comment_id int references comments(id),
    attach_id int references attachs(id),
    category_id int references category(id),
    state_id int references state(id)
);
>>>>>>> 11ff1e0 (1. Схема прав пользователи и роли. [#1093])
