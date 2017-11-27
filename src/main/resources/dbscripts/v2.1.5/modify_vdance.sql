ALTER TABLE chenkuserdb1.skt4 add COLUMN (super_dance_id VARCHAR(32));
show CREATE view vdance;
CREATE 
	or REPLACE
VIEW `vdance` AS
    SELECT 
        `chenkuserdb1`.`skt4`.`SKF33` AS `id`,
        `chenkuserdb1`.`skt4`.`SKF34` AS `name`,
        super_dance_id,
        `chenkuserdb1`.`skt4`.`SKF35` AS `danceClass`,
        `chenkuserdb1`.`skt4`.`SKF36` AS `remuneration`,
        `chenkuserdb1`.`skt4`.`dataStatus` AS `dataStatus`,
        `chenkuserdb1`.`skt4`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt4`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt4`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt4`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt4`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt4`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`skt4`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`skt4`;