alter TABLE chenkuserdb1.skt5 add COLUMN dataStatus tinyint not null default 0;
update vlesson set classRoomId =null where classRoomId ='';
CREATE 
	or REPLACE
VIEW `vclassroom` AS
    SELECT 
        `chenkuserdb1`.`skt5`.`SKF41` AS `id`,
        `chenkuserdb1`.`skt5`.`SKF42` AS `name`,
        `chenkuserdb1`.`skt5`.`SKF119` AS `store_id`,
        `chenkuserdb1`.`skt5`.`SKF43` AS `storeName`,
        `chenkuserdb1`.`skt5`.`SKF44` AS `maxStudentCount`,
        `chenkuserdb1`.`skt5`.`SKF45` AS `minStudentCount`,
        dataStatus,
        `chenkuserdb1`.`skt5`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt5`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt5`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt5`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt5`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt5`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`skt5`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`skt5`;