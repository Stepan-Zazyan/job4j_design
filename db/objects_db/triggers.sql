create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

drop table products;



insert into products (name, producer, count, price)
values ('огурец', 'land', 5, 50),
       ('tomato', 'land', 10, 80),
       ('milk', 'cow', 2, 100);

create or replace function tax_for_statement()
    returns trigger as
$$
begin
    update products
    set count = count + count * 2
    where id = new.id and new.id in (select id from inserted);
    return new;
end;
$$
    language 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure tax_for_statement();

drop trigger  tax_trigger on products;

insert into products (name, producer, count, price)
values ('onion', 'land', 20, 1000);

select *
from products;

-------------------------------------------------------------------------------

create or replace function
    tax_before_paste()
    returns trigger as
$$
begin
    update products
    set count = count + count * 2
    where id = new.id;
    return new;
end;
$$
    language 'plpgsql';

drop trigger tax_trigger_before on products;

create trigger tax_trigger_before
    before insert
    on products
    for each row
execute procedure tax_before_paste();

insert into products (name, producer, count, price)
values ('new chipooos', 'factory', 8, 100);

select *
from products;











----------------------------------------------------------------------
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function paste_in_table()
    returns trigger as
$$
BEGIN
    insert into history_of_price (name, price, date)
    VALUES (new.name, new.price, current_date);
    return new;
end;
$$
    language 'plpgsql';

create trigger paste
    after insert
    on products
    for each row
execute procedure paste_in_table();

insert into products (name, producer, count, price)
values ('pancakes', 'mother', 100, 100000);

select *
from history_of_price;

