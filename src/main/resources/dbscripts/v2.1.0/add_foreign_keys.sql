alter table yiwu_lesson_praise 
       add constraint fk_LessonPraise_lesson_id 
       foreign key (lesson_id) 
       references chenkuserdb1.skt7(skf139);
       
   alter table yiwu_lesson_interactive 
       add constraint fk_lessonInteractive_lesson_id 
       foreign key (lesson_id) 
       references chenkuserdb1.skt7(skf139);
       
   alter table yiwu_lesson_comment 
       add constraint fk_lessonCommnet_lesson_id 
       foreign key (lesson_id) 
       references chenkuserdb1.skt7(skf139);
     
      delete from  vemployee_post  where post_id = -1;
      alter table chenkuserdb1.tblemployee_post
       add constraint fk_employeePost_post_id 
       foreign key (PostID) 
       references chenkuserdb1.tblpost (id);
       
     alter table chenkuserdb1.tblemployee_post
       add constraint fk_employeePost_employee_id 
       foreign key (EmployeeID) 
       references chenkuserdb1.tblemployee (id);
       
      alter table chenkuserdb1.tbldept 
       add constraint fk_department_parent_id 
       foreign key (SuperiorID) 
       references  chenkuserdb1.tbldept (Id);