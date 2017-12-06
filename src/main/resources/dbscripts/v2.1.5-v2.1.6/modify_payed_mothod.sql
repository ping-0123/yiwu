CREATE 
	or REPLACE
VIEW `vpayed_method` AS
    SELECT 
        `chenkuserdb1`.`skt17`.`SKF246` AS `id`,
        `chenkuserdb1`.`skt17`.`SKF247` AS `name`,
        `chenkuserdb1`.`skt17`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt17`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt17`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt17`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt17`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt17`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`skt17`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`skt17`;