alter TABLE chenkuserdb1.tblemployee add COLUMN(yiwu_password varchar(128), salt varchar(128));
alter TABLE chenkuserdb1.tblemployee add COLUMN(dataStatus int);
CREATE 
	or REPLACE
VIEW `vemployee` AS
    SELECT 
        `chenkuserdb1`.`tblemployee`.`ID` AS `id`,
        `chenkuserdb1`.`tblemployee`.`User` AS `username`,
        yiwu_password,
        salt,
        `chenkuserdb1`.`tblemployee`.`SeegleUserID` AS `seegle_user_id`,
        `chenkuserdb1`.`tblemployee`.`name` AS `name`,
        `chenkuserdb1`.`tblemployee`.`PassWord` AS `chenk_password`,
        `chenkuserdb1`.`tblemployee`.`Sex` AS `gender`,
        `chenkuserdb1`.`tblemployee`.`Birthday` AS `birthday`,
        `chenkuserdb1`.`tblemployee`.`Tel` AS `telephone`,
        `chenkuserdb1`.`tblemployee`.`CellPhone` AS `cellphone`,
        `chenkuserdb1`.`tblemployee`.`Fax` AS `fax`,
        `chenkuserdb1`.`tblemployee`.`Email` AS `email`,
        `chenkuserdb1`.`tblemployee`.`Disabled` AS `disabled`,
        dataStatus,
        `chenkuserdb1`.`tblemployee`.`Creator` AS `sf_create_user`,
        `chenkuserdb1`.`tblemployee`.`LastChanger` AS `sf_last_change_user`,
        `chenkuserdb1`.`tblemployee`.`CreateTime` AS `sf_create_time`,
        `chenkuserdb1`.`tblemployee`.`LastChangeTime` AS `sf_last_change_time`,
        `chenkuserdb1`.`tblemployee`.`Removed` AS `removed`,
        `chenkuserdb1`.`tblemployee`.`wparam` AS `wparam`,
        `chenkuserdb1`.`tblemployee`.`lparam` AS `lparam`,
        `chenkuserdb1`.`tblemployee`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`tblemployee`.`SeegleUserName` AS `seegle_user_name`,
        `chenkuserdb1`.`tblemployee`.`SeegleSerialNum` AS `seegle_serial_num`,
        `chenkuserdb1`.`tblemployee`.`UserType` AS `userType`,
        `chenkuserdb1`.`tblemployee`.`AccessCode` AS `accessCode`,
        `chenkuserdb1`.`tblemployee`.`BindMac` AS `bindMac`,
        `chenkuserdb1`.`tblemployee`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timestamp`,
        `chenkuserdb1`.`tblemployee`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,
        `chenkuserdb1`.`tblemployee`.`SKF798` AS `fingerprint_code`,
        `chenkuserdb1`.`tblemployee`.`SKF844` AS `department_id`,
        `chenkuserdb1`.`tblemployee`.`SKF845` AS `default_duty`,
        `chenkuserdb1`.`tblemployee`.`OutUser` AS `out_user`,
        `chenkuserdb1`.`tblemployee`.`ClusterServerIP` AS `cluster_server_ip`,
        `chenkuserdb1`.`tblemployee`.`ClusterServerPort` AS `cluster_server_port`,
        `chenkuserdb1`.`tblemployee`.`ClusterToken` AS `cluster_token`,
        `chenkuserdb1`.`tblemployee`.`Last_Online_TimeStamp` AS `last_online_timestamp`
    FROM
        `chenkuserdb1`.`tblemployee`;