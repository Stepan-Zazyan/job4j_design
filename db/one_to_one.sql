create table vin
(
    id serial primary key,
    number int unique
);

create table car_passport
(
    id     serial primary key,
    vin_id int references vin(id)  unique
);

