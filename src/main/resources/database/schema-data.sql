INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1724639444616646657, 'Kusanagi Nene', 'beb26e235e2733423ceb4d8be1da6d00', 1, 0);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726114832190545921, 'teacher1', '41c8949aa55b8cb5dbec662f34b62df3', 1, 0);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726114863513608194, 'teacher2', 'ccffb0bb993eeb79059b31e1611ec353', 1, 0);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726114895948161026, 'teacher3', '82470256ea4b80343b27afccbca1015b', 1, 0);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726114941821263873, 'teacher4', '93dacda950b1dd917079440788af3321', 1, 0);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726114978492063745, 'student1', '5e5545d38a68148a2d5bd5ec9a89e327', 0, 1);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726115026583953409, 'student2', '213ee683360d88249109c2f92789dbc3', 0, 1);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726115060830445570, 'student3', '8e4947690532bc44a8e41e9fb365b76a', 0, 1);
INSERT INTO leetscope.user (id, name, password, is_teacher, is_student) VALUES (1726115113670287361, 'student4', '166a50c910e390d922db4696e4c7747b', 0, 1);

insert into `assignment` (uid, title, description, create_time, deadline, allowed_attempts) values
    (1724639444616646657, 'Hello World', 'Write a program that prints Hello World', '2023-11-11T11:11:11.111-08:00', '2023-12-25T23:59:59.000-08:00', 1),
    (1724639444616646657, 'A+B', 'Write a program that calculate the sum of A and B', '2023-11-11T11:11:11.111-08:00', '2023-12-25T23:59:59.000-08:00', 1),
    (1724639444616646657, 'Hacker', 'Write a program that breach the database of MiHoYo', '2023-11-11T11:11:11.111-08:00', '2023-10-25T23:59:59.000-08:00', 1),
    (1724639444616646657, 'Genshin Impact', 'Write a Genshin Impact Rip-Off', '2023-11-11T11:11:11.111-08:00', '2023-11-25T23:59:59.000-08:00', 1),
    (1724639444616646657, 'Honkai Star Rail', 'Write a Honkai Star Rail Rip-Off', '2023-11-11T11:11:11.111-08:00', '2023-11-11T23:59:59.000-08:00', 1);