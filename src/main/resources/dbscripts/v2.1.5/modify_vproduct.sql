ALTER TABLE chenkuserdb1.skt8 add COLUMN (usableRangeType varchar(32), usableDepartments VARCHAR(128), company_id int);
ALTER TABLE chenkuserdb1.skt8 add COLUMN (dance_id varchar(32), danceGrade_id int, courseType varchar(32), subCourseType varchar(32));
ALTER TABLE chenkuserdb1.skt8 add COLUMN(performanceType varchar(32));
CREATE 
	or REPLACE
VIEW `vproduct` AS
    SELECT 
        `chenkuserdb1`.`skt8`.`SKF94` AS `id`,
        `chenkuserdb1`.`skt8`.`SKF95` AS `name`,
        `chenkuserdb1`.`skt8`.`SKF100` AS `card_type`,
        `chenkuserdb1`.`skt8`.`SKF101` AS `customer_type`,
        performanceType,
        `chenkuserdb1`.`skt8`.`SKF102` AS `marked_price`,
        `chenkuserdb1`.`skt8`.`SKF103` AS `useful_life`,
        `chenkuserdb1`.`skt8`.`SKF104` AS `useful_times`,
        `chenkuserdb1`.`skt8`.`SKF252` AS `obsolete_flag`,
        `chenkuserdb1`.`skt8`.`SKF253` AS `DY_RCP`,
        `chenkuserdb1`.`skt8`.`SKF322` AS `max_leave_times`,
        `chenkuserdb1`.`skt8`.`SKF799` AS `organization_code`,
        `chenkuserdb1`.`skt8`.`SKF866` AS `useful_hours`,
        `chenkuserdb1`.`skt8`.`SKF952` AS `start_date`,
        `chenkuserdb1`.`skt8`.`SKF953` AS `end_date`,
        `chenkuserdb1`.`skt8`.`SKF1093` AS `econtract_type_id`,
        dance_id,
        danceGrade_id,
        courseType,
        subCourseType,
        usableRangeType,
        usableDepartments,
        company_id,
        `chenkuserdb1`.`skt8`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt8`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt8`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt8`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt8`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,
        `chenkuserdb1`.`skt8`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt8`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`
    FROM
        `chenkuserdb1`.`skt8`;
        
      UPDATE vproduct set obsolete_flag = true where end_date < curdate();
UPDATE vproduct set obsolete_flag = FALSE where end_date >= curdate();