ALTER TABLE chenkuserdb1.tblpost add COLUMN(dataStatus int);

CREATE 
	or REPLACE
VIEW `vpost` AS
    SELECT 
        chenkuserdb1.`tblpost`.`ID` AS `id`,
        `chenkuserdb1`.`tblpost`.`Type` AS `type`,
        `chenkuserdb1`.`tblpost`.`Name` AS `name`,
        `chenkuserdb1`.`tblpost`.`Description` AS `description`,
        `chenkuserdb1`.`tblpost`.`Creator` AS `sf_create_user`,
        `chenkuserdb1`.`tblpost`.`CreateTime` AS `sf_create_time`,
        `chenkuserdb1`.`tblpost`.`LastChanger` AS `sf_last_change_user`,
        `chenkuserdb1`.`tblpost`.`LastChangeTime` AS `sf_last_change_time`,
        `chenkuserdb1`.`tblpost`.`Removed` AS `removed`,
        dataStatus,
        `chenkuserdb1`.`tblpost`.`flag` AS `flag`,
        `chenkuserdb1`.`tblpost`.`wparam` AS `wparam`,
        `chenkuserdb1`.`tblpost`.`lparam` AS `lparam`,
        `chenkuserdb1`.`tblpost`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`tblpost`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`tblpost`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
        
    FROM
        `chenkuserdb1`.`tblpost`;