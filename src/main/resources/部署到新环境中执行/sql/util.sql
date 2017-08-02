-- 生成修改Auto increment 厨师值 ddl
select concat('alter table ' ,table_name ,' AUTO_INCREMENT = ',1000000 * rowno, ';')
from (SELECT @ROWNO := @ROWNO + 1 AS ROWNO, t1.* 
	FROM (select * from information_schema.TABLES  
	where 
		TABLE_SCHEMA = 'yiwu' 
        and TABLE_TYPE = 'BASE TABLE' 
        and table_name like 'yiwu%' 
        and table_name not in ('yiwu_type', 'yiwu_income_factor', 'yiwu_income_grade') 
	) t1,(SELECT @ROWNO := 0) t2
	) t3;

-- 生成视图的创建语句
	select concat('create or replace view ', table_schema, '.', table_name, ' as ', view_definition, ';')  from information_schema.views where TABLE_SCHEMA = 'yiwu';

-- 清空表数据（指定不清空的表)
	select concat('delete from ', TABLE_NAME ,';') 
from information_schema.TABLES 
where TABLE_SCHEMA='yiwu' 
 and TABLE_TYPE ='BASE TABLE' 
 AND TABLE_NAME LIKE 'yiwu_%'
 and TABLE_NAME not in('yiwu_income_factor','yiwu_income_grade','yiwu_plan_revenue','yiwu_product_type','yiwu_product_type_relation','yiwu_store_info','yiwu_type');