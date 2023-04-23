/*users - role = many-to-one
role - rules = many-to-many
item - users = many-to-one
item - comments = one-to-many
item - attachs = one-to-many
item - category = many-to-one
item - state = many-to-one*/
create table rules
(
    id   serial primary key,
    rule text
);

create table roles
(
    id        serial primary key,
    role_name text
);

create table rules_n_roles
(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table users
(
    id      serial primary key,
    role_id int references roles (id)
);

create table category
(
    id       serial primary key,
    category int
);

create table state
(
    id    serial primary key,
    state text
);

create table item
(
    id          serial primary key,
    user_id     int references users (id),
    category_id int references category (id),
    state_id    int references state (id)
);

drop table comments;
drop table attachs;

create table comments
(
    id      serial primary key,
    item_id int references item(id),
    comment text
);

create table attachs
(
    id     serial primary key,
    item_id int references item(id),
    attach text
);



