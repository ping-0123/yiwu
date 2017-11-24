delete from sys_user;
ALTER TABLE SYS_USER AUTO_INCREMENT = 1000000;
INSERT into sys_user(username, password, salt, employee_id, createDate, lastModifiedDate, dataStatus)
SELECT username, yiwu_password, salt , id , current_timestamp(), current_timestamp(), 0 from vemployee where removed = false;