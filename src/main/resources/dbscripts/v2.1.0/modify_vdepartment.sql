alter TABLE chenkuserdb1.tbldept add COLUMN(nation varchar(32), district varchar(32), address varchar(128), longitude FLOAT, latitude FLOAT);
CREATE 
or REPLACE 
VIEW `vdepartment` AS
    SELECT 
        `chenkuserdb1`.`tbldept`.`ID` AS `id`,
        `chenkuserdb1`.`tbldept`.`Name` AS `Name`,
        `chenkuserdb1`.`tbldept`.`SuperiorID` AS `superiorId`,
        `chenkuserdb1`.`tbldept`.`Path` AS `path`,
        `chenkuserdb1`.`tbldept`.`Manager1` AS `manager1`,
        `chenkuserdb1`.`tbldept`.`Manager2` AS `manager2`,
        `chenkuserdb1`.`tbldept`.`Description` AS `description`,
        `chenkuserdb1`.`tbldept`.`Creator` AS `sf_create_user`,
        `chenkuserdb1`.`tbldept`.`LastChanger` AS `sf_last_change_user`,
        `chenkuserdb1`.`tbldept`.`CreateTime` AS `sf_create_time`,
        `chenkuserdb1`.`tbldept`.`LastChangeTime` AS `sf_last_change_time`,
        `chenkuserdb1`.`tbldept`.`Removed` AS `removed`,
        `chenkuserdb1`.`tbldept`.`flag` AS `flag`,
        `chenkuserdb1`.`tbldept`.`wparam` AS `wparam`,
        `chenkuserdb1`.`tbldept`.`lparam` AS `lparam`,
        `chenkuserdb1`.`tbldept`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`tbldept`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,
        `chenkuserdb1`.`tbldept`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,
        `chenkuserdb1`.`tbldept`.`SKF725` AS `operationDistrict`,
        `chenkuserdb1`.`tbldept`.`SKF863` AS `officialAccount`,
        `chenkuserdb1`.`tbldept`.`SKF865` AS `logo`,
        nation,
        `chenkuserdb1`.`tbldept`.`SKF901` AS `province`,
		`chenkuserdb1`.`tbldept`.`SKF862` AS `city`,
         district,
         address,
         longitude,
         latitude
    FROM
        `chenkuserdb1`.`tbldept`;