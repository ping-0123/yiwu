alter TABLE chenkuserdb1.tblemployee_post add COLUMN(department_id int, start date, end DATE, is_default BOOLEAN DEFAULT FALSE);
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
        `chenkuserdb1`.`tblemployee_post`.`LastChanger` AS `lastChangerId`,
        `chenkuserdb1`.`tblemployee_post`.`LastChangeTime` AS `lastChangeTime`,
        `chenkuserdb1`.`tblemployee_post`.`Removed` AS `removed`,
        `chenkuserdb1`.`tblemployee_post`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`tblemployee_post`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimestamp`,
        `chenkuserdb1`.`tblemployee_post`.`SF_Last_Change_Timestamp` AS `lastChangeTimestamp`
    FROM
        `chenkuserdb1`.`tblemployee_post`;
        
	