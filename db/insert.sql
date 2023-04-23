insert into rules (rule) values ('правило пользователя');
insert into roles (role_name) values ('обычный пользователь');
insert into rules_n_roles (role_id, rule_id) values (1, 1);
insert into users (role_id) values (1);
insert into category (category) values (2);
insert into state (state) values ('state');
insert into item (user_id, category_id, state_id) values (1,1,1);
insert into comments (item_id, comment) values (1, 'comment');
insert into attachs (item_id, attach) values (1, 'attach');
