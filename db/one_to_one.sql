create table vin
(
    id serial primary key,
    number int
);

create table car_passport
(
    id     serial primary key,
    vin_id int references vin(id)
);


