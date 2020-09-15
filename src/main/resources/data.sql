alter sequence hibernate_sequence restart with 5;

insert into bean__teacher(id, first_name, last_name) values ('1','Mr.','John') ON CONFLICT DO NOTHING;
insert into bean__teacher(id, first_name, last_name) values ('2','Mr.','Smith') ON CONFLICT DO NOTHING;
insert into bean__teacher(id, first_name, last_name) values ('3','Ms.','Camile') ON CONFLICT DO NOTHING;
insert into bean__teacher(id, first_name, last_name) values ('4','Ms.','Serena') ON CONFLICT DO NOTHING;
insert into bean__teacher(id, first_name, last_name) values ('5','Mr.','Stark') ON CONFLICT DO NOTHING;

insert into bean__student (id, first_name, last_name, phone, id_teacher) values ('1','Cuong','Huynh Quoc','+84 39 382 2553', '1') ON CONFLICT DO NOTHING;
insert into bean__student (id, first_name, last_name, phone, id_teacher) values ('2','Bao','Lai Gia','+84 83 411 0501', '1') ON CONFLICT DO NOTHING;
insert into bean__student (id, first_name, last_name, phone, id_teacher) values ('3','Linh','Nguyen Hoang','+84 93 334 87 91','2') ON CONFLICT DO NOTHING;
insert into bean__student (id, first_name, last_name, phone, id_teacher) values ('4','Thai','Thanh Cong','+84 90 968 05 38','3') ON CONFLICT DO NOTHING;

insert into bean__course (id, course_name, room) VALUES ('1','Object Oriented Programming','A101') ON CONFLICT DO NOTHING;
insert into bean__course (id, course_name, room) VALUES ('2','Object Oriented Analysis and Design','A102') ON CONFLICT DO NOTHING;
insert into bean__course (id, course_name, room) VALUES ('3','Computer Network','A103') ON CONFLICT DO NOTHING;
insert into bean__course (id, course_name, room) VALUES ('4','Algorithm and Data Structure','A104') ON CONFLICT DO NOTHING;
insert into bean__course (id, course_name, room) VALUES ('5','Principles of Database Management','A105') ON CONFLICT DO NOTHING;
insert into bean__course (id, course_name, room) VALUES ('6','Software Engineering','A106') ON CONFLICT DO NOTHING;

insert into bean__course_student (id_course, id_student) VALUES ('1','2') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('2','2') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('3','1') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('3','1') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('4','4') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('1','3') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('1','4') ON CONFLICT DO NOTHING;
insert into bean__course_student (id_course, id_student) VALUES ('2','3') ON CONFLICT DO NOTHING;

insert into bean__course_teacher (id_course, id_teacher) VALUES ('1','2') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('1','3') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('2','4') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('2','3') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('3','1') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('4','1') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('5','1') ON CONFLICT DO NOTHING;
insert into bean__course_teacher (id_course, id_teacher) VALUES ('6','4') ON CONFLICT DO NOTHING;