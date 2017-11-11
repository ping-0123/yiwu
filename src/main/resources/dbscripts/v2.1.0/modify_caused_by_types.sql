-- 先修改 yiwu_type

-- 修改 yiwu_income_factor
update yiwu_income_factor t1 
	JOIN yiwu_type t2 on(t1.eventType_id = t2.id)
    join yiwu_type t3 on (t1.incomeType_id = t3.id)
    join yiwu_type t4 on (t1.relation_id = t4.id)
set t1.eventType = t2.name,
	t1.incomeType = t3.name,
    t1.relation =t4.name;
    
alter TABLE yiwu_income_factor DROP COLUMN delete_flag;

-- 修改 yiwu_distribuber_income
update yiwu_distributer_income t1
	join yiwu_type t2 on (t1.incomeType_id = t2.id)
set t1.type = t2.name;

-- 修改 yiwu_income_grade
update yiwu_income_grade t1
	join yiwu_type t2 on (t1.incomeType_id = t2.id)
set t1.incomeType = t2.name;

-- 修改 yiwu_income_record
update yiwu_income_record t1 
	join yiwu_type t2 on (t1.con_ben_relation_id = t2.id)
    join yiwu_type t3 on (t1.incomeType_id  = t3.id)
    join yiwu_income_event t4 on(t1.incomeEvent_id = t4.id)
    join yiwu_type t5 on (t4.type_id= t5.id)
set t1.incomeType = t3.name,
t1.con_ben_relation = t2.name,
t1.eventType = t5.name;