insert into rules (rule) values ('правило пользователя');
select * from rules;
insert into roles (role_name, rule_id) values ('обычный пользователь', 1);
insert into users (role_id) values (1);
insert into comments (comment) values ('comment');
insert into attachs (attach) values ('attach');
insert into category (category) values (2);
insert into state (state) values ('state');
insert into item (user_id, comment_id, attach_id, category_id, state_id) values (1,1,1,1,1);