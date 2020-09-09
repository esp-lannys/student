alter sequence hibernate_sequence restart with 5;
insert into teacher(id, first_name, last_name) values ('1','Mr.','John') ON CONFLICT DO NOTHING;
insert into teacher(id, first_name, last_name) values ('2','Mr.','Smith') ON CONFLICT DO NOTHING;
insert into teacher(id, first_name, last_name) values ('3','Ms.','Camile') ON CONFLICT DO NOTHING;
insert into teacher(id, first_name, last_name) values ('4','Ms.','Serena') ON CONFLICT DO NOTHING;


insert into student (id, first_name, last_name, phone, teacher_id) values ('1','Cuong','Huynh Quoc','+84 39 382 2553', '1') ON CONFLICT DO NOTHING;
insert into student (id, first_name, last_name, phone, teacher_id) values ('2','Bao','Lai Gia','+84 83 411 0501', '1') ON CONFLICT DO NOTHING;
insert into student (id, first_name, last_name, phone, teacher_id) values ('3','Linh','Nguyen Hoang','+84 93 334 87 91','2') ON CONFLICT DO NOTHING;
insert into student (id, first_name, last_name, phone, teacher_id) values ('4','Thai','Thanh Cong','+84 90 968 05 38','3') ON CONFLICT DO NOTHING;


