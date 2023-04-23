create table airplane (
    id serial primary key,
    number int
);

create table flights (
    id serial primary key,
    airplane_id int references airplane(id)
);

