drop table if exists `user`;

create table `user` (
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    password varchar(40) not null,
    is_teacher boolean default false,
    is_student boolean default false,
    primary key(id)
);