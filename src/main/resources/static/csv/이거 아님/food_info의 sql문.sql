set @a := 0;
select
( @a := @a+1 ) as fno,
ff.*
from
(SELECT
	f2.food_name,
    round(avg(ncsa), 0) as ncsa,
    round(avg(kcal), 0) as kcal,
	round(avg(protein), 0) as protein,
	round(avg(province), 0) as province,
	round(avg(carbohydrate), 0) as carbohydrate 
FROM
	healthdb.food_info f1,
	(select
		distinct
        left(food_code, 14) as food_code,
        SUBSTRING_INDEX(food_name, '_', 1) as food_name
        from healthdb.food_info) f2
	where
    left(f1.food_code, 14) = f2.food_code
    group by f2.food_name) ff