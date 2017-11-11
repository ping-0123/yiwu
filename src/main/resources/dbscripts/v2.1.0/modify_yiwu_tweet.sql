update yiwu_tweet t1 
JOIN yiwu_tweet_content t2 on (t1.tweetContent_id = t2.id)
join yiwu_type t3 on (t1.tweetType_id = t3.id) set t1.type = t3.name, t1.content = t2.content;

alter table yiwu_tweet drop column tweetContent_id;
alter table yiwu_tweet drop column tweetType_id;

drop table yiwu_tweet_content;

-- 修改yiwu_message
ALTER TABLE `yiwu`.`yiwu_message` 
DROP FOREIGN KEY `fk_Message_messageType_id`;
ALTER TABLE `yiwu`.`yiwu_message` 
DROP INDEX `fk_Message_messageType_id` ;
alter TABLE yiwu_message drop COLUMN messageType_id;