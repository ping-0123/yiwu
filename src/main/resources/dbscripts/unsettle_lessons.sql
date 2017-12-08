-- 封闭式未结算的课时
SELECT * from vlesson t1 
join vcheck_ins t2 on (t1.id = t2.lesson_id)
where t1.shidaoTeacherId is null 
 and t1.lessonDate BETWEEN '2017-12-01' and '2017-12-07'
and t2.teacher_id is not null and t2.sf_create_time <='2017-12-08'
and t1.lessonStatus <> '未审核'
and t1.courseType = '封闭式'
;


-- 私教课结算有问题
SELECT DISTINCT t1.* from vlesson t1 
join vcheck_ins t2 on (t1.id = t2.lesson_id)
join vcheck_ins t3 on (t1.id = t3.lesson_id )
where t1.shidaoTeacherId is null 
 and t1.lessonDate BETWEEN '2017-12-01' and '2017-12-07'
and t1.lessonStatus <> '未审核'
and t1.courseType = '私教课'
and t2.teacher_id is not null and t2.sf_create_time <='2017-12-08'
and (t3.contractNo is not null and t3.contractNo <> '')
and find_in_set(t3.contractNo, replace(t1.appointedContracts, ';',','))
;


-- 开放式结算有问题
SELECT * from vlesson t1 
join vcheck_ins t2 on (t1.id = t2.lesson_id)
JOIN (SELECT t1.lesson_id, count(1) count from vcheck_ins t1 
	where t1.teacher_id is  null and (contractNo is not null and contractNo <> '')
		and lesson_id is not null GROUP BY t1.lesson_id HAVING count(1) >=3 ) t3
	on (t1.id = t3.lesson_id)
where t1.shidaoTeacherId is null 
 and t1.lessonDate BETWEEN '2017-12-01' and '2017-12-07'
and t1.lessonStatus <> '未审核'
and t1.courseType = '开放式'
and t2.teacher_id is not null and t2.sf_create_time <='2017-12-08';

SELECT t1.lesson_id, group_concat(t1.contractNo) count from vcheck_ins t1 
where t1.teacher_id is  null and (contractNo is not null and contractNo <> '')
	and lesson_id is not null GROUP BY t1.lesson_id having count(*)>1 ;