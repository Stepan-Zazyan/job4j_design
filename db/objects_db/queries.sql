CREATE TABLE customers(
                          id serial primary key,
                          first_name text,
                          last_name text,
                          age int,
                          country text
);

insert into customers (first_name, last_name, age, country) VALUES
        ('kolya', 'evov', 41, 'bolgaria'),
        ('petya', 'evovan', 15, 'russia'),
        ('mick', 'smevov', 3, 'usa');

select first_name, min(age)
from customers
where age = (select min(age) from customers)
group by first_name;

CREATE TABLE orders(
                       id serial primary key,
                       amount int,
                       customer_id int references customers(id)
);

insert into orders (amount, customer_id) values
        (8, 3),
        (24, 3),
        (100, 1),
        (300, 1),
        (200, 1);

select first_name from customers
where id not in (select customer_id from orders)

