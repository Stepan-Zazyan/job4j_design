create table type
(
    id   serial primary key,
    name text unique
);

create table product
(
    id           serial primary key,
    name         text,
    type_id      int references type (id),
    expired_date date,
    price        numeric
);
drop table type;
drop table product;
insert into type (name)
values ('овощи'),
       ('МОЛОКО'),
       ('мясо'),
       ('СЫР');

insert into product (name, type_id, expired_date, price)
values ('сливочное мороженое', 2, '2019-02-12', 50.0),
       ('мороженое с шоколадом', 2, '2023-12-12', 100.0),
       ('колбаса', 3, '2022-12-12', 100.0),
       ('ветчина', 3, '2024-12-12', 500.0),
       ('лук', 1, '2025-12-12', 5000.0),
       ('морковь', 1, '2017-12-12', 90.0),
       ('картофель', 1, '2023-01-12', 90.0),
       ('сыр плавленный', 4, '2019-02-12', 90.0),
       ('мороженое с шоко', 2, '2023-12-12', 1020.0),
       ('сыр плавленный', 4, '2022-12-12', 1010.0),
       ('Сыр моцарелла', 4, '2024-12-12', 5040.0),
       ('лук', 1, '2025-12-12', 50030.0),
       ('морковь', 1, '2017-12-12', 902.0),
       ('картофель', 1, '2023-01-12', 905.0),
       ('лук', 1, '2025-12-12', 5030.0),
       ('морковь', 1, '2017-12-12', 92.0),
       ('картофель', 1, '2023-01-12', 95.0),
       ('лук', 1, '2025-12-12', 5.0),
       ('морковь', 1, '2017-12-12', 9.0),
       ('картофель', 1, '2023-01-12', 9.0);

select p.name
from product as p
         join type t on p.type_id = t.id
where t.id = 4;

select name
from product
where LOWER(name)  like '%мороженое%';

select name
from product
where expired_date < current_date;

select p.name, max(price)
from product as p
join type t on t.id = p.type_id
group by p.name;

select p.name, count(*)
from product as p
         join type t on t.id = p.type_id
group by p.name;

select name
from product
 where type_id = 4 or type_id = 2;

select t.name
from product as p
         join type t on t.id = p.type_id
group by t.name
having count(*) < 10;

select p.name, t.name
from product as p
join type as t on p.type_id = t.id;


