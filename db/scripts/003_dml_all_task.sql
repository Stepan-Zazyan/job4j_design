insert into company(id, name)
values (1, 'Sasya'),
       (12, 'Dasya'),
       (3, 'vasya'),
       (5, 'Qvasya'),
       (2, 'Tasya'),
       (6, 'Masya');

insert into person(id, name, company_id)
values (1, 'Dasha', 12),
       (2, 'Pasha', 5),
       (3, 'Sasha', 12),
       (4, 'Qasha', 5 ),
       (5, 'Natasha', 12),
       (6, 'Masha', 2);

select p.name, c.name from person as p
join company as c on p.company_id = c.id
where company_id != 5;

SELECT c.name, COUNT(p.name)
FROM company AS c
         JOIN person AS p ON c.id = p.company_id
GROUP BY c.name
HAVING COUNT(p.name) = (
    SELECT MAX(count)
    FROM (
             SELECT COUNT(*) AS count
             FROM person
             GROUP BY company_id
         ) AS counts
)
LIMIT 5;
