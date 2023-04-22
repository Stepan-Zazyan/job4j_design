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