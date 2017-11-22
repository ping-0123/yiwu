CREATE 
	or REPLACE 
VIEW `vdance_grade` AS
    SELECT 
        `chenkuserdb1`.`skt28`.`SKF381` AS `id`,
        `chenkuserdb1`.`skt28`.`SKF382` AS `name`,
        sf_create_user,
        sf_create_time,
        sf_last_change_user,
        sf_last_change_time,
        machineCode,
        sf_last_sync_timeStamp,
        sf_last_change_timeStamp
    FROM
        `chenkuserdb1`.`skt28`;