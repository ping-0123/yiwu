ALTER table chenkuserdb1.skt6 add COLUMN(dataStatus int not null DEFAULT 0);
ALTER TABLE chenkuserdb1.skt6 add COLUMN(sumLessonTimes int);
alter TABLE chenkuserdb1.skt6 add COLUMN(videoPosterUri VARCHAR(128), videoTitle VARCHAR(128));

CREATE 
	or REPLACE
VIEW `vcourse` AS
    SELECT 
        `chenkuserdb1`.`skt6`.`SKF50` AS `id`,
        `chenkuserdb1`.`skt6`.`SKF51` AS `name`,
        `chenkuserdb1`.`skt6`.`SKF52` AS `dance_id`,
        `chenkuserdb1`.`skt6`.`SKF53` AS `danceDesc`,
        `chenkuserdb1`.`skt6`.`SKF54` AS `store_id`,
        `chenkuserdb1`.`skt6`.`SKF55` AS `storeName`,
        `chenkuserdb1`.`skt6`.`SKF56` AS `teacher_id`,
        `chenkuserdb1`.`skt6`.`SKF57` AS `teacherName`,
        `chenkuserdb1`.`skt6`.`SKF58` AS `interval_id`,
        `chenkuserdb1`.`skt6`.`SKF59` AS `intervalDesc`,
        `chenkuserdb1`.`skt6`.`SKF60` AS `startTime`,
        `chenkuserdb1`.`skt6`.`SKF61` AS `endTime`,
        `chenkuserdb1`.`skt6`.`SKF62` AS `startDate`,
        `chenkuserdb1`.`skt6`.`SKF63` AS `endDate`,
        `chenkuserdb1`.`skt6`.`sumLessonTimes` AS `sumLessonTimes`,
        `chenkuserdb1`.`skt6`.`SKF64` AS `sumCourseHours`,
        `chenkuserdb1`.`skt6`.`SKF65` AS `classRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF66` AS `classRoomName`,
        `chenkuserdb1`.`skt6`.`dataStatus` AS `dataStatus`,
        `chenkuserdb1`.`skt6`.`SF_CREATE_USER` AS `sf_create_user`,
        `chenkuserdb1`.`skt6`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,
        `chenkuserdb1`.`skt6`.`SF_CREATE_TIME` AS `sf_create_time`,
        `chenkuserdb1`.`skt6`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,
        `chenkuserdb1`.`skt6`.`MachineCode` AS `MachineCode`,
        `chenkuserdb1`.`skt6`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,
        `chenkuserdb1`.`skt6`.`SKF88` AS `weeklyCourseHours`,
        `chenkuserdb1`.`skt6`.`SKF89` AS `delete_flag`,
        `chenkuserdb1`.`skt6`.`SKF93` AS `weeks`,
        `chenkuserdb1`.`skt6`.`SKF118` AS `courseType`,
        `chenkuserdb1`.`skt6`.`SKF165` AS `monInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF166` AS `monRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF167` AS `tueInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF168` AS `tueRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF169` AS `wedInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF170` AS `wedRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF171` AS `thuInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF172` AS `thuRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF173` AS `friInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF174` AS `friRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF175` AS `satInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF176` AS `satRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF177` AS `sunInterval_id`,
        `chenkuserdb1`.`skt6`.`SKF178` AS `sunRoom_id`,
        `chenkuserdb1`.`skt6`.`SKF197` AS `status`,
        `chenkuserdb1`.`skt6`.`SKF224` AS `studentCount`,
        `chenkuserdb1`.`skt6`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,
        `chenkuserdb1`.`skt6`.`SKF367` AS `include_holeday_flag`,
        `chenkuserdb1`.`skt6`.`SKF388` AS `DanceGrade`,
        `chenkuserdb1`.`skt6`.`SKF868` AS `ShenheRen`,
        `chenkuserdb1`.`skt6`.`SKF919` AS `connotation`,
        `chenkuserdb1`.`skt6`.`SKF920` AS `help`,
        `chenkuserdb1`.`skt6`.`SKF921` AS `briefIntroduction`,
        `chenkuserdb1`.`skt6`.`SKF922` AS `picture`,
        `chenkuserdb1`.`skt6`.`SKF923` AS `videoURL`,
        `chenkuserdb1`.`skt6`.`videoPosterUri` AS `videoPosterUri`,
        `chenkuserdb1`.`skt6`.`videoTitle` AS `videoTitle`,
        `chenkuserdb1`.`skt6`.`SKF924` AS `audio`,
        `chenkuserdb1`.`skt6`.`SKF925` AS `audioURL`,
        `chenkuserdb1`.`skt6`.`SKF949` AS `danceIntroduction`,
        `chenkuserdb1`.`skt6`.`SKF1034` AS `subCourseType`
    FROM
        `chenkuserdb1`.`skt6`;
        
update vcourse set courseType= null where courseType = '所有课程类型';
UPDATE vcourse set courseType = null where courseType = '';
