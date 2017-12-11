CREATE  PROCEDURE `p8`()
BEGIN
  DECLARE v_dl ,v_tname VARCHAR(32);
  DECLARE v_n , v_tid ,v_snum ,v_tnum,v_kcid INT(32);
  DECLARE done INT DEFAULT FALSE;
  DECLARE cur CURSOR FOR SELECT DISTINCT skf327 FROM skt23 JOIN skt7 ON skf139=skf327 WHERE LEFT(skt23.`SF_CREATE_TIME`,10)>=DATE_ADD(CURDATE(), INTERVAL -5 DAY) AND IFNULL(skf705,0)=0;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;


 OPEN cur;
  read_loop: LOOP
 SET v_dl='';  /**课程类型**/
    SET v_tname='';
    SET v_n=0;
    SET v_tid=0;
    SET v_snum=0;
    SET v_tnum=0;

    FETCH cur INTO v_kcid;
    
    IF done THEN
      LEAVE read_loop;
    END IF;
   /** 取课程类型**/
  SELECT skf336 INTO v_dl FROM skt7 WHERE skf139=v_kcid;  
  /** 学员刷卡数量 **/
    SELECT COUNT(*) INTO v_n FROM skt23 WHERE skf326!='' AND skf327=v_kcid;  
    /** 签到老师Id  选最近刷卡的一个**/
 SELECT skf334 INTO v_tid FROM `skt23` JOIN `tblemployee_post` ON skf334=`EmployeeID` AND `PostID`=6 AND skf327=v_kcid
                                     JOIN  tblemployee ON skf334=tblemployee.id  ORDER BY skf325 DESC LIMIT 1  ;   
    /**签到老师姓名 **/                             
    SELECT NAME INTO v_tname FROM `skt23` JOIN `tblemployee_post` ON skf334=`EmployeeID` AND `PostID`=6 AND skf327=v_kcid
                                     JOIN  tblemployee ON skf334=tblemployee.id    ORDER BY skf325 DESC LIMIT 1  ;  
    IF v_dl='开放式' AND v_n>=3 THEN
    
     /** 设置课时的上课老师 **/
    UPDATE skt7 SET skf200='已开课' ,skf221=v_tid ,skf222=v_tname WHERE skf139=v_kcid; 
     /** 减扣所签到的合约 **/
    UPDATE skt18 JOIN (SELECT skf328,COUNT(*) cn FROM skt23  WHERE skf327=v_kcid AND skf328!='' AND IFNULL(skf705,0)=0 GROUP BY skf328) t_a ON skf294=skf328 SET skf299=skf299-cn ; 
    /** 标记该签到已结算 **/
    UPDATE skt23 SET skf705=1  WHERE skf327=v_kcid;  
    END IF;
  
    IF v_dl='私教课'  THEN
/*  SELECT COUNT(*) INTO v_snum FROM skt7 JOIN skt18 ON CONCAT(';',skf368,';') LIKE CONCAT('%;',skf294,';%')  WHERE skf139=v_kcid AND skf294!='' 
    AND NOT EXISTS (SELECT skf328 FROM skt23 WHERE skf294=skf328);*/
  SELECT COUNT(*) INTO v_snum  FROM skt18 WHERE skf276=v_kcid  AND NOT EXISTS (SELECT skf328 FROM skt23 WHERE skf294=skf328);    
    
    
    SELECT COUNT(*) INTO v_tnum FROM skt23 WHERE skf327=v_kcid AND IFNULL(skf334,0)>0;
    
    IF IFNULL(v_snum,0)=0 AND v_tnum>=1 AND v_n>0 THEN 
    
    UPDATE skt7 SET skf200='已开课' ,skf221=v_tid ,skf222=v_tname WHERE skf139=v_kcid;
  
 /*UPDATE skt18 JOIN skt23 ON skf294=skf328 JOIN skt7 ON CONCAT(';',skf368,';') LIKE CONCAT('%;',skf294,';%')  SET  skf299=skf299-skf78 WHERE skf139=v_kcid AND skf294!='' AND IFNULL(skf705,0)=0;*/

 UPDATE skt18 JOIN skt23 ON skf294=skf328 JOIN skt7 ON skf276=skf139 SET  skf299=skf299-skf78 WHERE skf139=v_kcid AND skf294!='' AND IFNULL(skf705,0)=0;   
  /* INSERT  INTO skt23 ( skf326,skf327,skf328,`SF_CREATE_TIME`)
    SELECT skf255,v_kcid,skf294,NOW()
    FROM skt18 JOIN skt7 ON skf368 LIKE CONCAT('%',skf294,'%')  WHERE skf139=v_kcid AND skf294!=''; */
    UPDATE skt23 SET skf705=1  WHERE skf327=v_kcid;
      END IF;

    END IF;
    
    IF v_dl='封闭式'  THEN
    
        UPDATE skt7 SET skf200='已开课' ,skf221=v_tid ,skf222=v_tname WHERE skf139=v_kcid;
    UPDATE skt18  SET skf299=skf299-1 WHERE skf276=(SELECT  skf120 FROM skt7 WHERE skf139=v_kcid) AND skf277='已审核' ;
    
    
    INSERT  INTO skt23 ( skf326,skf327,skf328,`SF_CREATE_TIME`)
    SELECT skf255,v_kcid,skf294,NOW()
    FROM skt18 JOIN skt7 ON skf276=skf120 WHERE skf139=v_kcid;
    
  
    UPDATE skt23 SET skf705=1  WHERE skf327=v_kcid;
    
    END IF;
    
  END LOOP;
  CLOSE cur;

UPDATE skt7 JOIN (SELECT skf327,COUNT(DISTINCT skf328) AS cnt FROM skt23 WHERE DATE_ADD(LEFT(`SF_CREATE_TIME`,10),INTERVAL 1 DAY)>=CURDATE() AND skf328 !='' GROUP BY skf327) a
       ON  skf139=skf327 SET skf826=cnt;
    END
