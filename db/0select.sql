create table fauna (
                       id serial primary key,
                       name text,
                       avg_age int,
                       discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
VALUES
('fish', 50, '2019-09-01'),
('dog', 15000, '2019-09-01'),
('dog', 70, '1300-09-01'),
('dog', 70, null)
;

select * from fauna
where name = 'fish';

select * from fauna
where avg_age >= 10000 and avg_age <= 21000;

select * from fauna
where discovery_date IS NULL;

select * from fauna
where discovery_date < '1950-01-01';

