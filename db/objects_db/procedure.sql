create or replace procedure delete_data(p_name varchar, p_roducer varchar,
                                        p_count integer, p_price integer)
    language 'plpgsql' as
$$
begin
    delete
    from products
    where price = p_price
       or name = p_name
       or count = p_count
       or producer = p_roducer;
end;
$$;

select *
from products;

call delete_data('milk', '', 0, 0);

create or replace function delete_func_data(p_name varchar, p_roducer varchar)
    returns varchar as
$$
    declare result varchar;
begin
    delete
    from products
    where name = p_name
       or producer = p_roducer;
    return result;
end
$$
language 'plpgsql';

select delete_func_data('tomato', '');
