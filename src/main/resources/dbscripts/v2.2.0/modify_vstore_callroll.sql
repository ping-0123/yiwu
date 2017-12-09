CREATE 
	or REPLACE
VIEW `vstore_callroll` AS
    SELECT 
        `chenkuserdb1`.`skt75`.`SKF992` AS `id`,
        `chenkuserdb1`.`skt75`.`SKF993` AS `lesson_id`,
        `chenkuserdb1`.`skt75`.`SKF994` AS `memberCard`,
        `chenkuserdb1`.`skt75`.`SKF995` AS `called`,
        `chenkuserdb1`.`skt75`.`SKF996` AS `unCalledrollReason`,
        `chenkuserdb1`.`skt75`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt75`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt75`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt75`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt75`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt75`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`
    FROM
        `chenkuserdb1`.`skt75`;

UPDATE vstore_callroll set lesson_id = null where lesson_id = '';
ALTER TABLE chenkuserdb1.skt75 MODIFY COLUMN skf993 int;
