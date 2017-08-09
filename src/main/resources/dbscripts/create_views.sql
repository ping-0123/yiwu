create or replace view vappointment as select `chenkuserdb1`.`skt63`.`SKF846` AS `id`,`chenkuserdb1`.`skt63`.`SKF847` AS `courseHourId`,`chenkuserdb1`.`skt63`.`SKF848` AS `customerId`,`chenkuserdb1`.`skt63`.`SKF854` AS `status`,`chenkuserdb1`.`skt63`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt63`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt63`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt63`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt63`.`MachineCode` AS `machinecode`,`chenkuserdb1`.`skt63`.`Sf_Last_Sync_TimeStamp` AS `Sf_Last_Sync_TimeStamp` from `chenkuserdb1`.`skt63`;
create or replace view vcheck_ins as select `chenkuserdb1`.`skt23`.`SKF325` AS `id`,`chenkuserdb1`.`skt23`.`SKF326` AS `memberCard`,`chenkuserdb1`.`skt23`.`SKF327` AS `lessonId`,`chenkuserdb1`.`skt23`.`SKF328` AS `contract`,`chenkuserdb1`.`skt23`.`SKF334` AS `teacherId`,`chenkuserdb1`.`skt23`.`SKF704` AS `comsumeSd`,`chenkuserdb1`.`skt23`.`SKF705` AS `flag`,`chenkuserdb1`.`skt23`.`SKF965` AS `isRetroactive`,`chenkuserdb1`.`skt23`.`SKF983` AS `storemanCallroll`,`chenkuserdb1`.`skt23`.`SKF984` AS `uncallrollReason`,`chenkuserdb1`.`skt23`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt23`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt23`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt23`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt23`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt23`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt23`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt23`;
create or replace view vclassroom as select `chenkuserdb1`.`skt5`.`SKF41` AS `id`,`chenkuserdb1`.`skt5`.`SKF42` AS `name`,`chenkuserdb1`.`skt5`.`SKF119` AS `store_id`,`chenkuserdb1`.`skt5`.`SKF43` AS `storeName`,`chenkuserdb1`.`skt5`.`SKF44` AS `maxStudentCount`,`chenkuserdb1`.`skt5`.`SKF45` AS `minStudentCount`,`chenkuserdb1`.`skt5`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt5`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt5`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt5`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt5`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt5`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt5`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt5`;
create or replace view vcourse as select `chenkuserdb1`.`skt6`.`SKF50` AS `id`,`chenkuserdb1`.`skt6`.`SKF51` AS `name`,`chenkuserdb1`.`skt6`.`SKF52` AS `dance_id`,`chenkuserdb1`.`skt6`.`SKF53` AS `danceDesc`,`chenkuserdb1`.`skt6`.`SKF54` AS `store_id`,`chenkuserdb1`.`skt6`.`SKF55` AS `storeName`,`chenkuserdb1`.`skt6`.`SKF56` AS `teacher_id`,`chenkuserdb1`.`skt6`.`SKF57` AS `teacherName`,`chenkuserdb1`.`skt6`.`SKF58` AS `interval_id`,`chenkuserdb1`.`skt6`.`SKF59` AS `intervalDesc`,`chenkuserdb1`.`skt6`.`SKF60` AS `startTime`,`chenkuserdb1`.`skt6`.`SKF61` AS `endTime`,`chenkuserdb1`.`skt6`.`SKF62` AS `startDate`,`chenkuserdb1`.`skt6`.`SKF63` AS `endDate`,`chenkuserdb1`.`skt6`.`SKF64` AS `sumCourseHours`,`chenkuserdb1`.`skt6`.`SKF65` AS `classRoom_id`,`chenkuserdb1`.`skt6`.`SKF66` AS `classRoomName`,`chenkuserdb1`.`skt6`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt6`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt6`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt6`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt6`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`skt6`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt6`.`SKF88` AS `weeklyCourseHours`,`chenkuserdb1`.`skt6`.`SKF89` AS `delete_flag`,`chenkuserdb1`.`skt6`.`SKF93` AS `weeks`,`chenkuserdb1`.`skt6`.`SKF118` AS `courseType`,`chenkuserdb1`.`skt6`.`SKF165` AS `monInterval_id`,`chenkuserdb1`.`skt6`.`SKF166` AS `monRoom_id`,`chenkuserdb1`.`skt6`.`SKF167` AS `tueInterval_id`,`chenkuserdb1`.`skt6`.`SKF168` AS `tueRoom_id`,`chenkuserdb1`.`skt6`.`SKF169` AS `wedInterval_id`,`chenkuserdb1`.`skt6`.`SKF170` AS `wedRoom_id`,`chenkuserdb1`.`skt6`.`SKF171` AS `thuInterval_id`,`chenkuserdb1`.`skt6`.`SKF172` AS `thuRoom_id`,`chenkuserdb1`.`skt6`.`SKF173` AS `friInterval_id`,`chenkuserdb1`.`skt6`.`SKF174` AS `friRoom_id`,`chenkuserdb1`.`skt6`.`SKF175` AS `satInterval_id`,`chenkuserdb1`.`skt6`.`SKF176` AS `satRoom_id`,`chenkuserdb1`.`skt6`.`SKF177` AS `sunInterval_id`,`chenkuserdb1`.`skt6`.`SKF178` AS `sunRoom_id`,`chenkuserdb1`.`skt6`.`SKF197` AS `status`,`chenkuserdb1`.`skt6`.`SKF224` AS `StudentCount`,`chenkuserdb1`.`skt6`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`skt6`.`SKF367` AS `include_holeday_flag`,`chenkuserdb1`.`skt6`.`SKF388` AS `DanceGrade`,`chenkuserdb1`.`skt6`.`SKF868` AS `ShenheRen`,`chenkuserdb1`.`skt6`.`SKF919` AS `connotation`,`chenkuserdb1`.`skt6`.`SKF920` AS `help`,`chenkuserdb1`.`skt6`.`SKF921` AS `briefIntroduction`,`chenkuserdb1`.`skt6`.`SKF922` AS `picture`,`chenkuserdb1`.`skt6`.`SKF923` AS `videoURL`,`chenkuserdb1`.`skt6`.`SKF924` AS `audio`,`chenkuserdb1`.`skt6`.`SKF925` AS `audioURL`,`chenkuserdb1`.`skt6`.`SKF949` AS `danceIntroduction`,`chenkuserdb1`.`skt6`.`SKF1034` AS `subCourseType` from `chenkuserdb1`.`skt6`;
create or replace view vcustomer as select `chenkuserdb1`.`skt1`.`SKF223` AS `Id`,`chenkuserdb1`.`skt1`.`SKF225` AS `salesmanId`,`chenkuserdb1`.`skt1`.`SKF226` AS `audit_child`,`chenkuserdb1`.`skt1`.`SKF227` AS `isMember`,`chenkuserdb1`.`skt1`.`SKF1` AS `memberCard`,`chenkuserdb1`.`skt1`.`SKF2` AS `name`,`chenkuserdb1`.`skt1`.`SKF335` AS `sex`,`chenkuserdb1`.`skt1`.`SKF3` AS `mobilePhone`,`chenkuserdb1`.`skt1`.`SKF4` AS `residentId`,`chenkuserdb1`.`skt1`.`SKF5` AS `birthday`,`chenkuserdb1`.`skt1`.`SKF6` AS `age`,`chenkuserdb1`.`skt1`.`SKF7` AS `QQ`,`chenkuserdb1`.`skt1`.`SKF8` AS `wechat`,`chenkuserdb1`.`skt1`.`SKF9` AS `address`,`chenkuserdb1`.`skt1`.`SKF302` AS `company`,`chenkuserdb1`.`skt1`.`SKF303` AS `profession`,`chenkuserdb1`.`skt1`.`SKF304` AS `interesting`,`chenkuserdb1`.`skt1`.`SKF344` AS `photo`,`chenkuserdb1`.`skt1`.`SKF364` AS `customer_level`,`chenkuserdb1`.`skt1`.`SKF365` AS `source_of_customer`,`chenkuserdb1`.`skt1`.`SKF379` AS `date_of_next_track`,`chenkuserdb1`.`skt1`.`SKF726` AS `times_of_untrack`,`chenkuserdb1`.`skt1`.`SKF853` AS `times_of_remainder_course`,`chenkuserdb1`.`skt1`.`SKF864` AS `password`,`chenkuserdb1`.`skt1`.`SKF930` AS `credit`,`chenkuserdb1`.`skt1`.`SKF245` AS `earnest`,`chenkuserdb1`.`skt1`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt1`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt1`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt1`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt1`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`skt1`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`skt1`.`SF_Last_Change_Timestamp` AS `sf_last_change_timestamp` from `chenkuserdb1`.`skt1`;
create or replace view vdance as select `chenkuserdb1`.`skt4`.`SKF33` AS `id`,`chenkuserdb1`.`skt4`.`SKF34` AS `name`,`chenkuserdb1`.`skt4`.`SKF35` AS `danceClass`,`chenkuserdb1`.`skt4`.`SKF36` AS `remuneration`,`chenkuserdb1`.`skt4`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt4`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt4`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt4`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt4`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt4`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt4`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt4`;
create or replace view vdance_grade as select `chenkuserdb1`.`skt28`.`SKF381` AS `id`,`chenkuserdb1`.`skt28`.`SKF382` AS `name` from `chenkuserdb1`.`skt28`;
create or replace view vdepartment as select `chenkuserdb1`.`tbldept`.`ID` AS `Id`,`chenkuserdb1`.`tbldept`.`Name` AS `Name`,`chenkuserdb1`.`tbldept`.`SuperiorID` AS `superiorId`,`chenkuserdb1`.`tbldept`.`Path` AS `path`,`chenkuserdb1`.`tbldept`.`Manager1` AS `manager1`,`chenkuserdb1`.`tbldept`.`Manager2` AS `manager2`,`chenkuserdb1`.`tbldept`.`Description` AS `description`,`chenkuserdb1`.`tbldept`.`Creator` AS `sf_create_user`,`chenkuserdb1`.`tbldept`.`LastChanger` AS `sf_last_change_user`,`chenkuserdb1`.`tbldept`.`CreateTime` AS `sf_create_time`,`chenkuserdb1`.`tbldept`.`LastChangeTime` AS `sf_last_change_time`,`chenkuserdb1`.`tbldept`.`Removed` AS `removed`,`chenkuserdb1`.`tbldept`.`flag` AS `flag`,`chenkuserdb1`.`tbldept`.`wparam` AS `wparam`,`chenkuserdb1`.`tbldept`.`lparam` AS `lparam`,`chenkuserdb1`.`tbldept`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`tbldept`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`tbldept`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`tbldept`.`SKF725` AS `operationDistrict`,`chenkuserdb1`.`tbldept`.`SKF862` AS `city`,`chenkuserdb1`.`tbldept`.`SKF863` AS `officialAccount`,`chenkuserdb1`.`tbldept`.`SKF865` AS `logo`,`chenkuserdb1`.`tbldept`.`SKF901` AS `province` from `chenkuserdb1`.`tbldept`;
create or replace view velectric_contract as select `chenkuserdb1`.`skt79`.`SKF1041` AS `contractNo`,`chenkuserdb1`.`skt79`.`SKF1042` AS `customerName`,`chenkuserdb1`.`skt79`.`SKF1043` AS `gender`,`chenkuserdb1`.`skt79`.`SKF1044` AS `birthday`,`chenkuserdb1`.`skt79`.`SKF1045` AS `IdentityCardNo`,`chenkuserdb1`.`skt79`.`SKF1046` AS `mobiePhoneNo`,`chenkuserdb1`.`skt79`.`SKF1047` AS `qqNo`,`chenkuserdb1`.`skt79`.`SKF1048` AS `wechatNo`,`chenkuserdb1`.`skt79`.`SKF1049` AS `customerId`,`chenkuserdb1`.`skt79`.`SKF1050` AS `contactAddress`,`chenkuserdb1`.`skt79`.`SKF1051` AS `homeAddress`,`chenkuserdb1`.`skt79`.`SKF1053` AS `recommendMemberCardNo`,`chenkuserdb1`.`skt79`.`SKF1054` AS `memberCardNo`,`chenkuserdb1`.`skt79`.`SKF1055` AS `effectiveStart`,`chenkuserdb1`.`skt79`.`SKF1056` AS `effectiveEnd`,`chenkuserdb1`.`skt79`.`SKF1057` AS `timesOfLesson`,`chenkuserdb1`.`skt79`.`SKF1058` AS `price`,`chenkuserdb1`.`skt79`.`SKF1059` AS `amount`,`chenkuserdb1`.`skt79`.`SKF1060` AS `promotionPrice`,`chenkuserdb1`.`skt79`.`SKF1061` AS `rangeOfApplication`,`chenkuserdb1`.`skt79`.`SKF1062` AS `supplementalInstruction`,`chenkuserdb1`.`skt79`.`SKF1063` AS `uppcaseAmount`,`chenkuserdb1`.`skt79`.`SKF1064` AS `lowercaseAmount`,`chenkuserdb1`.`skt79`.`SKF1065` AS `payedDate`,`chenkuserdb1`.`skt79`.`SKF1066` AS `payedMethod`,`chenkuserdb1`.`skt79`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt79`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt79`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt79`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt79`.`SKF1071` AS `depositAmount`,`chenkuserdb1`.`skt79`.`SKF1072` AS `depositDate`,`chenkuserdb1`.`skt79`.`SKF1073` AS `finalPayment`,`chenkuserdb1`.`skt79`.`SKF1074` AS `finalDate`,`chenkuserdb1`.`skt79`.`SKF1075` AS `contractType`,`chenkuserdb1`.`skt79`.`SKF1076` AS `isConfirmed` from `chenkuserdb1`.`skt79`;
create or replace view velectric_contract_type as select `chenkuserdb1`.`skt81`.`SKF1085` AS `id`,`chenkuserdb1`.`skt81`.`SKF1086` AS `description`,`chenkuserdb1`.`skt81`.`SKF1087` AS `content`,`chenkuserdb1`.`skt81`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt81`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt81`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt81`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt81`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt81`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp` from `chenkuserdb1`.`skt81`;
create or replace view vemployee as select `chenkuserdb1`.`tblemployee`.`ID` AS `ID`,`chenkuserdb1`.`tblemployee`.`User` AS `User`,`chenkuserdb1`.`tblemployee`.`SeegleUserID` AS `SeegleUserID`,`chenkuserdb1`.`tblemployee`.`name` AS `name`,`chenkuserdb1`.`tblemployee`.`PassWord` AS `PassWord`,`chenkuserdb1`.`tblemployee`.`Sex` AS `Sex`,`chenkuserdb1`.`tblemployee`.`Birthday` AS `Birthday`,`chenkuserdb1`.`tblemployee`.`Tel` AS `Tel`,`chenkuserdb1`.`tblemployee`.`CellPhone` AS `CellPhone`,`chenkuserdb1`.`tblemployee`.`Fax` AS `Fax`,`chenkuserdb1`.`tblemployee`.`Email` AS `Email`,`chenkuserdb1`.`tblemployee`.`Disabled` AS `Disabled`,`chenkuserdb1`.`tblemployee`.`Creator` AS `sf_create_user`,`chenkuserdb1`.`tblemployee`.`LastChanger` AS `sf_last_change_user`,`chenkuserdb1`.`tblemployee`.`CreateTime` AS `CreateTime`,`chenkuserdb1`.`tblemployee`.`LastChangeTime` AS `sf_last_change_time`,`chenkuserdb1`.`tblemployee`.`Removed` AS `Removed`,`chenkuserdb1`.`tblemployee`.`wparam` AS `wparam`,`chenkuserdb1`.`tblemployee`.`lparam` AS `lparam`,`chenkuserdb1`.`tblemployee`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`tblemployee`.`SeegleUserName` AS `SeegleUserName`,`chenkuserdb1`.`tblemployee`.`SeegleSerialNum` AS `SeegleSerialNum`,`chenkuserdb1`.`tblemployee`.`UserType` AS `UserType`,`chenkuserdb1`.`tblemployee`.`AccessCode` AS `AccessCode`,`chenkuserdb1`.`tblemployee`.`BindMac` AS `BindMac`,`chenkuserdb1`.`tblemployee`.`Sf_Last_Sync_TimeStamp` AS `sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`tblemployee`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`tblemployee`.`SKF798` AS `fingerPrintNo`,`chenkuserdb1`.`tblemployee`.`SKF844` AS `departmentId`,`chenkuserdb1`.`tblemployee`.`SKF845` AS `defaultDuty`,`chenkuserdb1`.`tblemployee`.`OutUser` AS `OutUser`,`chenkuserdb1`.`tblemployee`.`ClusterServerIP` AS `ClusterServerIP`,`chenkuserdb1`.`tblemployee`.`ClusterServerPort` AS `ClusterServerPort`,`chenkuserdb1`.`tblemployee`.`ClusterToken` AS `ClusterToken`,`chenkuserdb1`.`tblemployee`.`Last_Online_TimeStamp` AS `Last_Online_TimeStamp` from `chenkuserdb1`.`tblemployee`;
create or replace view vemployee_post as select `chenkuserdb1`.`tblemployee_post`.`ID` AS `id`,`chenkuserdb1`.`tblemployee_post`.`EmployeeID` AS `employeeId`,`chenkuserdb1`.`tblemployee_post`.`PostID` AS `postID`,`chenkuserdb1`.`tblemployee_post`.`LastChanger` AS `lastChangerId`,`chenkuserdb1`.`tblemployee_post`.`LastChangeTime` AS `lastChangeTime`,`chenkuserdb1`.`tblemployee_post`.`Removed` AS `removed`,`chenkuserdb1`.`tblemployee_post`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`tblemployee_post`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimestamp`,`chenkuserdb1`.`tblemployee_post`.`SF_Last_Change_Timestamp` AS `lastChangeTimestamp` from `chenkuserdb1`.`tblemployee_post`;
create or replace view vinterval as select `chenkuserdb1`.`skt3`.`SKF24` AS `id`,`chenkuserdb1`.`skt3`.`SKF25` AS `name`,`chenkuserdb1`.`skt3`.`SKF26` AS `start`,`chenkuserdb1`.`skt3`.`SKF27` AS `end`,`chenkuserdb1`.`skt3`.`SKF28` AS `hours`,`chenkuserdb1`.`skt3`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt3`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt3`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt3`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt3`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt3`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_time_stamp`,`chenkuserdb1`.`skt3`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt3`;
create or replace view vlesson as select `chenkuserdb1`.`skt7`.`SKF139` AS `id`,`chenkuserdb1`.`skt7`.`SKF120` AS `courseId`,`chenkuserdb1`.`skt7`.`SKF72` AS `lessonDate`,`chenkuserdb1`.`skt7`.`SKF73` AS `startTime`,`chenkuserdb1`.`skt7`.`SKF74` AS `endTime`,`chenkuserdb1`.`skt7`.`SKF75` AS `courseDesc`,`chenkuserdb1`.`skt7`.`SKF76` AS `storeId`,`chenkuserdb1`.`skt7`.`SKF77` AS `storeName`,`chenkuserdb1`.`skt7`.`SKF78` AS `lessonTime`,`chenkuserdb1`.`skt7`.`SKF79` AS `yindaoTeacherId`,`chenkuserdb1`.`skt7`.`SKF80` AS `yindaoTeacherName`,`chenkuserdb1`.`skt7`.`SKF81` AS `classRoomId`,`chenkuserdb1`.`skt7`.`SKF82` AS `classRoomName`,`chenkuserdb1`.`skt7`.`SKF221` AS `shidaoTeacherId`,`chenkuserdb1`.`skt7`.`SKF222` AS `shidaoTeacherName`,`chenkuserdb1`.`skt7`.`SKF200` AS `lessonStatus`,`chenkuserdb1`.`skt7`.`SKF336` AS `courseType`,`chenkuserdb1`.`skt7`.`SKF1035` AS `subCourseType`,`chenkuserdb1`.`skt7`.`SKF83` AS `flag_delete`,`chenkuserdb1`.`skt7`.`SKF198` AS `start_date_time`,`chenkuserdb1`.`skt7`.`SKF199` AS `lessonTime_minutes`,`chenkuserdb1`.`skt7`.`SKF368` AS `appointed_huijiheyue`,`chenkuserdb1`.`skt7`.`SKF821` AS `yindaoRenshu`,`chenkuserdb1`.`skt7`.`SKF822` AS `tiyanRenshu`,`chenkuserdb1`.`skt7`.`SKF823` AS `dianmingRenshu`,`chenkuserdb1`.`skt7`.`SKF824` AS `shidaoRenshu`,`chenkuserdb1`.`skt7`.`SKF826` AS `qiandaoRenshu`,`chenkuserdb1`.`skt7`.`SKF827` AS `yuyueRenshu`,`chenkuserdb1`.`skt7`.`SKF855` AS `neihan`,`chenkuserdb1`.`skt7`.`SKF856` AS `help`,`chenkuserdb1`.`skt7`.`SKF857` AS `jianjie`,`chenkuserdb1`.`skt7`.`SKF858` AS `picture`,`chenkuserdb1`.`skt7`.`SKF859` AS `video`,`chenkuserdb1`.`skt7`.`SKF860` AS `audio`,`chenkuserdb1`.`skt7`.`SKF861` AS `audio_link`,`chenkuserdb1`.`skt7`.`SKF950` AS `dance_introduction`,`chenkuserdb1`.`skt7`.`SKF1001` AS `QRcode`,`chenkuserdb1`.`skt7`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt7`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt7`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt7`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt7`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt7`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt7`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt7`;
create or replace view vorder as select `chenkuserdb1`.`skt18`.`SKF254` AS `id`,`chenkuserdb1`.`skt18`.`SKF255` AS `memberCardNo`,`chenkuserdb1`.`skt18`.`SKF256` AS `product_id`,`chenkuserdb1`.`skt18`.`SKF257` AS `markedPrice`,`chenkuserdb1`.`skt18`.`SKF258` AS `preferential`,`chenkuserdb1`.`skt18`.`SKF369` AS `count`,`chenkuserdb1`.`skt18`.`SKF370` AS `discount`,`chenkuserdb1`.`skt18`.`SKF259` AS `payedAmount`,`chenkuserdb1`.`skt18`.`SKF380` AS `customer_id`,`chenkuserdb1`.`skt18`.`SKF706` AS `payed_date`,`chenkuserdb1`.`skt18`.`SKF276` AS `course_id`,`chenkuserdb1`.`skt18`.`SKF277` AS `checked_status`,`chenkuserdb1`.`skt18`.`SKF278` AS `store_id`,`chenkuserdb1`.`skt18`.`SKF293` AS `vip_attr`,`chenkuserdb1`.`skt18`.`SKF294` AS `contractNo`,`chenkuserdb1`.`skt18`.`SKF295` AS `validity`,`chenkuserdb1`.`skt18`.`SKF296` AS `validity_times`,`chenkuserdb1`.`skt18`.`SKF297` AS `startdate`,`chenkuserdb1`.`skt18`.`SKF298` AS `endDate`,`chenkuserdb1`.`skt18`.`SKF299` AS `remain_times`,`chenkuserdb1`.`skt18`.`SKF305` AS `product_type`,`chenkuserdb1`.`skt18`.`SKF1036` AS `product_subType`,`chenkuserdb1`.`skt18`.`SKF306` AS `recommender`,`chenkuserdb1`.`skt18`.`SKF307` AS `valid_storeids`,`chenkuserdb1`.`skt18`.`SKF308` AS `pic`,`chenkuserdb1`.`skt18`.`SKF321` AS `ask_for_leave_flag`,`chenkuserdb1`.`skt18`.`SKF354` AS `comments`,`chenkuserdb1`.`skt18`.`SKF366` AS `relative_SD`,`chenkuserdb1`.`skt18`.`SKF300` AS `check_privilege`,`chenkuserdb1`.`skt18`.`SKF301` AS `current_status`,`chenkuserdb1`.`skt18`.`SKF867` AS `remain_hours`,`chenkuserdb1`.`skt18`.`SKF963` AS `audit_flag`,`chenkuserdb1`.`skt18`.`SKF1037` AS `e_contract_text`,`chenkuserdb1`.`skt18`.`SKF1038` AS `e_contract_address`,`chenkuserdb1`.`skt18`.`SKF1039` AS `e_contract_picture_flag`,`chenkuserdb1`.`skt18`.`SKF1040` AS `e_contract_status`,`chenkuserdb1`.`skt18`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt18`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt18`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt18`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt18`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`skt18`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_TimeStamp`,`chenkuserdb1`.`skt18`.`SF_Last_Change_Timestamp` AS `sf_last_change_timestamp` from `chenkuserdb1`.`skt18`;
create or replace view vorder_payed_method as select `chenkuserdb1`.`skt16`.`SKF237` AS `id`,`chenkuserdb1`.`skt16`.`SKF238` AS `orderId`,`chenkuserdb1`.`skt16`.`SKF239` AS `payedMethodId`,`chenkuserdb1`.`skt16`.`SKF240` AS `amount`,`chenkuserdb1`.`skt16`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt16`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt16`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt16`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt16`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt16`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp`,`chenkuserdb1`.`skt16`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp` from `chenkuserdb1`.`skt16`;
create or replace view vpayed_method as select `chenkuserdb1`.`skt17`.`SKF246` AS `id`,`chenkuserdb1`.`skt17`.`SKF247` AS `name`,`chenkuserdb1`.`skt17`.`SF_CREATE_USER` AS `createUserId`,`chenkuserdb1`.`skt17`.`SF_LAST_CHANGE_USER` AS `lastChangeUserId`,`chenkuserdb1`.`skt17`.`SF_CREATE_TIME` AS `createTime`,`chenkuserdb1`.`skt17`.`SF_LAST_CHANGE_TIME` AS `lastChangeTime`,`chenkuserdb1`.`skt17`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt17`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimestamp`,`chenkuserdb1`.`skt17`.`SF_Last_Change_Timestamp` AS `lastChangeTimestamp` from `chenkuserdb1`.`skt17`;
create or replace view vpost as select `chenkuserdb1`.`tblpost`.`ID` AS `ID`,`chenkuserdb1`.`tblpost`.`Type` AS `Type`,`chenkuserdb1`.`tblpost`.`Name` AS `Name`,`chenkuserdb1`.`tblpost`.`Description` AS `Description`,`chenkuserdb1`.`tblpost`.`Creator` AS `Creator`,`chenkuserdb1`.`tblpost`.`LastChanger` AS `LastChanger`,`chenkuserdb1`.`tblpost`.`CreateTime` AS `CreateTime`,`chenkuserdb1`.`tblpost`.`LastChangeTime` AS `LastChangeTime`,`chenkuserdb1`.`tblpost`.`Removed` AS `Removed`,`chenkuserdb1`.`tblpost`.`flag` AS `flag`,`chenkuserdb1`.`tblpost`.`wparam` AS `wparam`,`chenkuserdb1`.`tblpost`.`lparam` AS `lparam`,`chenkuserdb1`.`tblpost`.`MachineCode` AS `MachineCode`,`chenkuserdb1`.`tblpost`.`Sf_Last_Sync_TimeStamp` AS `Sf_Last_Sync_TimeStamp`,`chenkuserdb1`.`tblpost`.`SF_Last_Change_Timestamp` AS `SF_Last_Change_Timestamp` from `chenkuserdb1`.`tblpost`;
create or replace view vproduct as select `chenkuserdb1`.`skt8`.`SKF94` AS `id`,`chenkuserdb1`.`skt8`.`SKF95` AS `name`,`chenkuserdb1`.`skt8`.`SKF100` AS `card_type`,`chenkuserdb1`.`skt8`.`SKF101` AS `customer_type`,`chenkuserdb1`.`skt8`.`SKF102` AS `marked_price`,`chenkuserdb1`.`skt8`.`SKF103` AS `useful_life`,`chenkuserdb1`.`skt8`.`SKF104` AS `useful_times`,`chenkuserdb1`.`skt8`.`SKF252` AS `obsolete_flag`,`chenkuserdb1`.`skt8`.`SKF253` AS `DY_RCP`,`chenkuserdb1`.`skt8`.`SKF322` AS `max_leave_times`,`chenkuserdb1`.`skt8`.`SF_CREATE_USER` AS `sf_create_user`,`chenkuserdb1`.`skt8`.`SF_LAST_CHANGE_USER` AS `sf_last_change_user`,`chenkuserdb1`.`skt8`.`SF_CREATE_TIME` AS `sf_create_time`,`chenkuserdb1`.`skt8`.`SF_LAST_CHANGE_TIME` AS `sf_last_change_time`,`chenkuserdb1`.`skt8`.`SF_Last_Change_Timestamp` AS `sf_last_change_timeStamp`,`chenkuserdb1`.`skt8`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt8`.`Sf_Last_Sync_TimeStamp` AS `sf_last_sync_timeStamp` from `chenkuserdb1`.`skt8`;
create or replace view vstore_callroll as select `chenkuserdb1`.`skt75`.`SKF992` AS `id`,`chenkuserdb1`.`skt75`.`SKF993` AS `lessonId`,`chenkuserdb1`.`skt75`.`SKF994` AS `memberCard`,`chenkuserdb1`.`skt75`.`SKF995` AS `flagCallroll`,`chenkuserdb1`.`skt75`.`SKF996` AS `unCallrollReason`,`chenkuserdb1`.`skt75`.`SF_CREATE_USER` AS `createUserId`,`chenkuserdb1`.`skt75`.`SF_LAST_CHANGE_USER` AS `lastChangeUserId`,`chenkuserdb1`.`skt75`.`SF_CREATE_TIME` AS `createTime`,`chenkuserdb1`.`skt75`.`SF_LAST_CHANGE_TIME` AS `lastChangeTime`,`chenkuserdb1`.`skt75`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt75`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimeStamp` from `chenkuserdb1`.`skt75`;
create or replace view vteacher_callroll as select `chenkuserdb1`.`skt56`.`SKF760` AS `id`,`chenkuserdb1`.`skt56`.`SKF757` AS `memberCard`,`chenkuserdb1`.`skt56`.`SKF758` AS `courseType`,`chenkuserdb1`.`skt56`.`SKF759` AS `lessonId`,`chenkuserdb1`.`skt56`.`SKF766` AS `callroll`,`chenkuserdb1`.`skt56`.`SKF797` AS `studentName`,`chenkuserdb1`.`skt56`.`SKF828` AS `slotCardId`,`chenkuserdb1`.`skt56`.`SKF938` AS `memberId`,`chenkuserdb1`.`skt56`.`SKF964` AS `unCallRollReason`,`chenkuserdb1`.`skt56`.`SKF966` AS `isFilledUp`,`chenkuserdb1`.`skt56`.`SF_CREATE_USER` AS `createUserid`,`chenkuserdb1`.`skt56`.`SF_LAST_CHANGE_USER` AS `lastChangeUserId`,`chenkuserdb1`.`skt56`.`SF_CREATE_TIME` AS `createTime`,`chenkuserdb1`.`skt56`.`SF_LAST_CHANGE_TIME` AS `lastChangeTime`,`chenkuserdb1`.`skt56`.`SF_LAST_CHANGE_TIMESTAMP` AS `lastChangeTimeStamp`,`chenkuserdb1`.`skt56`.`MachineCode` AS `machineCode`,`chenkuserdb1`.`skt56`.`Sf_Last_Sync_TimeStamp` AS `lastSyncTimeStamp` from `chenkuserdb1`.`skt56`;