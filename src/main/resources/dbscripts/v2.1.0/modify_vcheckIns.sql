alter TABLE chenkuserdb1.skt23 add COLUMN(dataStatus int not null DEFAULT 0, appointed boolean not null DEFAULT false);
CREATE 
	or REPLACE 
VIEW `vcheck_ins` AS
    SELECT 
        `chenkuserdb1`.`skt23`.`SKF325` AS `id`,
        `chenkuserdb1`.`skt23`.`distributer_id` AS `distributer_id`,
        `chenkuserdb1`.`skt23`.`SKF326` AS `memberCard`,
        `chenkuserdb1`.`skt23`.`SKF327` AS `lesson_id`,
        `chenkuserdb1`.`skt23`.`SKF328` AS `contractNo`,
        `chenkuserdb1`.`skt23`.`SKF334` AS `teacher_id`,
        `chenkuserdb1`.`skt23`.`SKF704` AS `comsumeSd`,
        `chenkuserdb1`.`skt23`.`SKF705` AS `flag`,
        `chenkuserdb1`.`skt23`.`SKF965` AS `isRetroactive`,
        `chenkuserdb1`.`skt23`.`SKF983` AS `storemanCallroll`,
        `chenkuserdb1`.`skt23`.`SKF984` AS `uncallrollReason`,
        appointed,
        dataStatus,
        `chenkuserdb1`.`skt23`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt23`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt23`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt23`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt23`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt23`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`skt23`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`skt23`;