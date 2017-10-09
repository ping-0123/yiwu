ALTER table chenkuserdb1.skt7 add COLUMN(dataStatus int DEFAULT 0);
alter table chenkuserdb1.skt7 add column(ordinalNo int);
alter table chenkuserdb1.skt7 add COLUMN(puzzleVideoUri varchar(128), practicalVideoUri varchar(128));

CREATE 
	or REPLACE
VIEW  yiwu.vlesson AS
    SELECT 
        `chenkuserdb1`.`skt7`.`SKF139` AS `id`,
        `chenkuserdb1`.`skt7`.`SKF120` AS `course_id`,
    	ordinalNo,
        `chenkuserdb1`.`skt7`.`SKF72` AS `lessonDate`,
        `chenkuserdb1`.`skt7`.`SKF73` AS `startTime`,
        `chenkuserdb1`.`skt7`.`SKF74` AS `endTime`,
        `chenkuserdb1`.`skt7`.`SKF75` AS `courseDesc`,
        `chenkuserdb1`.`skt7`.`SKF76` AS `storeId`,
        `chenkuserdb1`.`skt7`.`SKF77` AS `storeName`,
        `chenkuserdb1`.`skt7`.`SKF78` AS `lessonTime`,
        `chenkuserdb1`.`skt7`.`SKF79` AS `yindaoTeacherId`,
        `chenkuserdb1`.`skt7`.`SKF80` AS `yindaoTeacherName`,
        `chenkuserdb1`.`skt7`.`SKF81` AS `classRoomId`,
        `chenkuserdb1`.`skt7`.`SKF82` AS `classRoomName`,
        `chenkuserdb1`.`skt7`.`SKF221` AS `shidaoTeacherId`,
        `chenkuserdb1`.`skt7`.`SKF222` AS `shidaoTeacherName`,
        `chenkuserdb1`.`skt7`.`SKF200` AS `lessonStatus`,
        `chenkuserdb1`.`skt7`.`SKF336` AS `courseType`,
        `chenkuserdb1`.`skt7`.`SKF1035` AS `subCourseType`,
        `chenkuserdb1`.`skt7`.`SKF83` AS `flag_delete`,
        `chenkuserdb1`.`skt7`.`SKF198` AS `start_date_time`,
        `chenkuserdb1`.`skt7`.`SKF199` AS `lessonTime_minutes`,
        `chenkuserdb1`.`skt7`.`SKF368` AS `appointed_huijiheyue`,
        `chenkuserdb1`.`skt7`.`SKF821` AS `yindaoRenshu`,
        `chenkuserdb1`.`skt7`.`SKF822` AS `tiyanRenshu`,
        `chenkuserdb1`.`skt7`.`SKF823` AS `dianmingRenshu`,
        `chenkuserdb1`.`skt7`.`SKF824` AS `shidaoRenshu`,
        `chenkuserdb1`.`skt7`.`SKF826` AS `qiandaoRenshu`,
        `chenkuserdb1`.`skt7`.`SKF827` AS `yuyueRenshu`,
        `chenkuserdb1`.`skt7`.`SKF855` AS `neihan`,
        `chenkuserdb1`.`skt7`.`SKF856` AS `help`,
        `chenkuserdb1`.`skt7`.`SKF857` AS `jianjie`,
        `chenkuserdb1`.`skt7`.`SKF858` AS `picture`,
        `chenkuserdb1`.`skt7`.`SKF859` AS `video`,
        puzzleVideoUri,
        practicalVideoUri,
        `chenkuserdb1`.`skt7`.`SKF860` AS `audio`,
        `chenkuserdb1`.`skt7`.`SKF861` AS `audio_link`,
        `chenkuserdb1`.`skt7`.`SKF950` AS `dance_introduction`,
        `chenkuserdb1`.`skt7`.`SKF1001` AS `QRcode`,
        dataStatus,
        `chenkuserdb1`.`skt7`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt7`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt7`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt7`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt7`.`MachineCode` AS `machineCode`,
        `chenkuserdb1`.`skt7`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`skt7`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`
    FROM
        `chenkuserdb1`.`skt7`;
