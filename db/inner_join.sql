create table album
(
    id   serial primary key,
    name text unique,
    cost int
);

create table sale
(
    id       serial primary key,
    name     text unique,
    album_id int references album (id)
);

insert into album (name, cost)
values ('hello', 50),
       ('youtube', 0),
       ('nice', 500),
       ('wtf', 5000);


drop table sale;
insert into sale (name, album_id)
values ('hello', 1),
       ('youtube', null),
       ('nice', 3),
       ('niiiiiice', 4),
       ('wtf', 4);

select * from sale
join album on sale.album_id = album.id;

select * from sale as s
join album as a on s.name = a.name;

select * from sale as s
join album as a on s.name = a.name and s.album_id IS NOT NULL;