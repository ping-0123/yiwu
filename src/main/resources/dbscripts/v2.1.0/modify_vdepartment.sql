alter TABLE chenkuserdb1.tbldept add COLUMN(nation varchar(32), district varchar(32), address varchar(128), longitude FLOAT, latitude FLOAT);
alter TABLE chenkuserdb1.tbldept add COLUMN(dataStatus int not null default 0);
alter table chenkuserdb1.tbldept add COLUMN(type varchar(32));
alter table chenkuserdb1.tbldept add COLUMN(telePhone varchar(32));
alter TABLE chenkuserdb1.tbldept MODIFY COLUMN id int not null AUTO_INCREMENT;


CREATE 
or REPLACE 
VIEW `vdepartment` AS
    SELECT 
        `chenkuserdb1`.`tbldept`.`ID` AS `id`,
        `chenkuserdb1`.`tbldept`.`Name` AS `Name`,
        type,
        `chenkuserdb1`.`tbldept`.`SuperiorID` AS `superiorId`,
        `chenkuserdb1`.`tbldept`.`Path` AS `path`,
        `chenkuserdb1`.`tbldept`.`Manager1` AS `manager1`,
        `chenkuserdb1`.`tbldept`.`Manager2` AS `manager2`,
        `chenkuserdb1`.`tbldept`.`Description` AS `description`,
        `chenkuserdb1`.`tbldept`.`Removed` AS `removed`,
        datastatus,
        `chenkuserdb1`.`tbldept`.`flag` AS `flag`,
        `chenkuserdb1`.`tbldept`.`wparam` AS `wparam`,
        `chenkuserdb1`.`tbldept`.`lparam` AS `lparam`,
        telePhone,
        `chenkuserdb1`.`tbldept`.`SKF725` AS `operationDistrict`,
        `chenkuserdb1`.`tbldept`.`SKF863` AS `officialAccount`,
        `chenkuserdb1`.`tbldept`.`SKF865` AS `logo`,
        nation,
        `chenkuserdb1`.`tbldept`.`SKF901` AS `province`,
		`chenkuserdb1`.`tbldept`.`SKF862` AS `city`,
         district,
         address,
         longitude,
         latitude,
		`chenkuserdb1`.`tbldept`.`Creator` AS `sf_create_user`,
        `chenkuserdb1`.`tbldept`.`LastChanger` AS `sf_last_change_user`,
        `chenkuserdb1`.`tbldept`.`CreateTime` AS `sf_create_time`,
        `chenkuserdb1`.`tbldept`.`LastChangeTime` AS `sf_last_change_time`,
		`chenkuserdb1`.`tbldept`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`tbldept`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,
        `chenkuserdb1`.`tbldept`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`tbldept`;
        
 UPDATE vdepartment set superiorId=null where id = 1;
 update vdepartment set dataStatus=2 where removed=TRUE;
UPDATE vdepartment set datastatus=0 where datastatus is null;

alter table chenkuserdb1.tbldept add CONSTRAINT fk_department_parent_Id FOREIGN KEY (SuperiorID) REFERENCES chenkuserdb1.tbldept(id);
UPDATE vdepartment set type = 'STORE' WHERE NAME LIKE '%店';

update vdepartment t1 join yiwu_store_info t2
 on (t1.id = t2.id)
 set t1.province = t2.province,
 t1.city = t2.city,
 t1.nation = t2.nation,
 t1.district = t2.district,
 t1.address = t2.address,
 t1.telePhone = t2.telePhone;
