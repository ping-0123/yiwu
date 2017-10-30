alter TABLE chenkuserdb1.tblemployee_post add FOREIGN KEY fk_employeePost_employee_id(EmployeeID) REFERENCES chenkuserdb1.tblemployee(id);
alter TABLE chenkuserdb1.tblemployee_post add COLUMN
	(department_id int,
	start date, 
	end DATE, 
	is_default BOOLEAN DEFAULT FALSE,
	sf_create_user int,
	sf_create_time DATETIME, 
	dataStatus int not null default 0);
alter TABLE chenkuserdb1.tblemployee_post MODIFY COLUMN id int not null auto_increment;

CREATE 
    or REPLACE
VIEW `vemployee_post` AS
    SELECT 
        `chenkuserdb1`.`tblemployee_post`.`ID` AS `id`,
        `chenkuserdb1`.`tblemployee_post`.`EmployeeID` AS `employee_id`,
        `chenkuserdb1`.`tblemployee_post`.`PostID` AS `post_id`,
        department_id,
        start,
        end,
        is_default,
		`chenkuserdb1`.`tblemployee_post`.`Removed` AS `removed`,
        dataStatus,
        sf_create_user,
        sf_create_time,
        `chenkuserdb1`.`tblemployee_post`.`LastChanger` AS `sf_last_change_user`,
        `chenkuserdb1`.`tblemployee_post`.`LastChangeTime` AS `sf_last_change_time`,
        `chenkuserdb1`.`tblemployee_post`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`tblemployee_post`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`tblemployee_post`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`tblemployee_post`;
        
UPDATE vemployee_post set post_id = 1 where post_id = -1;
update vemployee_post set dataStatus=2 where removed=TRUE;
UPDATE vemployee_post set datastatus=0 where datastatus is null;