update yiwu_tweet t1 
JOIN yiwu_tweet_content t2 on (t1.tweetContent_id = t2.id)
join yiwu_type t3 on (t1.tweetType_id = t3.id) set t1.type = t3.name, t1.content = t2.content;