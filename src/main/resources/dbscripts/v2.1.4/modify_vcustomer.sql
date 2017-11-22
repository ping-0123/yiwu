CREATE 
	or REPLACE
VIEW `vcustomer` AS
    SELECT 
        `chenkuserdb1`.`skt1`.`SKF223` AS `id`,
        `chenkuserdb1`.`skt1`.`SKF225` AS `salesman_id`,
        `chenkuserdb1`.`skt1`.`SKF226` AS `audit_child`,
        `chenkuserdb1`.`skt1`.`SKF227` AS `isMember`,
        `chenkuserdb1`.`skt1`.`SKF1` AS `memberCard`,
        `chenkuserdb1`.`skt1`.`SKF2` AS `name`,
        `chenkuserdb1`.`skt1`.`SKF335` AS `sex`,
        `chenkuserdb1`.`skt1`.`SKF3` AS `mobilePhone`,
        `chenkuserdb1`.`skt1`.`SKF4` AS `residentId`,
        `chenkuserdb1`.`skt1`.`SKF5` AS `birthday`,
        `chenkuserdb1`.`skt1`.`SKF6` AS `age`,
        `chenkuserdb1`.`skt1`.`SKF7` AS `QQ`,
        `chenkuserdb1`.`skt1`.`SKF8` AS `wechat`,
        `chenkuserdb1`.`skt1`.`SKF9` AS `address`,
        `chenkuserdb1`.`skt1`.`SKF302` AS `company`,
        `chenkuserdb1`.`skt1`.`SKF303` AS `profession`,
        `chenkuserdb1`.`skt1`.`SKF304` AS `interesting`,
        `chenkuserdb1`.`skt1`.`SKF344` AS `photo`,
        `chenkuserdb1`.`skt1`.`SKF364` AS `customer_level`,
        `chenkuserdb1`.`skt1`.`SKF365` AS `source_of_customer`,
        `chenkuserdb1`.`skt1`.`SKF379` AS `date_of_next_track`,
        `chenkuserdb1`.`skt1`.`SKF726` AS `times_of_untrack`,
        `chenkuserdb1`.`skt1`.`SKF853` AS `times_of_remainder_course`,
        `chenkuserdb1`.`skt1`.`SKF864` AS `password`,
        `chenkuserdb1`.`skt1`.`SKF930` AS `credit`,
        `chenkuserdb1`.`skt1`.`SKF245` AS `earnest`,
        `chenkuserdb1`.`skt1`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt1`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt1`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt1`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt1`.`MachineCode` AS `MachineCode`,
        `chenkuserdb1`.`skt1`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,
        `chenkuserdb1`.`skt1`.`SF_Last_Change_Timestamp` AS `sf_last_change_timestamp`
    FROM
        `chenkuserdb1`.`skt1`;
