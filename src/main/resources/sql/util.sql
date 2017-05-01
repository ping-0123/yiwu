-- 生成修改Auto increment 厨师值 ddl
select concat('alter table ' ,table_name ,' AUTO_INCREMENT = ',1000000 * rowno, ';')
from (SELECT @ROWNO := @ROWNO + 1 AS ROWNO, t1.* 
		FROM (select * from information_schema.TABLES  where TABLE_SCHEMA = 'yiwu' and TABLE_TYPE = 'BASE TABLE' ) t1,(SELECT @ROWNO := 0) t2
	) t3;