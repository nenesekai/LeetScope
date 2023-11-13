drop table if exists `submission`;
drop table if exists `assignment`;
drop table if exists `user`;

create table `user` (
    id bigint not null auto_increment,
    name varchar(30) not null unique,
    password varchar(40) not null,
    is_teacher boolean default false,
    is_student boolean default false,
    primary key(id)
);

create table `assignment` (
    id bigint not null auto_increment,
    uid bigint not null,
    title varchar(60) not null,
    description varchar(255) default null,
    create_time datetime not null,
    deadline datetime not null,
    allowed_attempts int default 1,
    primary key(id),
    foreign key(uid) references user(id)
);

create table `submission` (
    id bigint not null auto_increment,
    uid bigint not null,
    assignment_id bigint not null,
    file_name varchar(255) not null,
    is_graded boolean default false,
    is_pass boolean default false,
    create_time datetime not null,
    primary key(id),
    foreign key(uid) references user(id),
    foreign key(assignment_id) references assignment(id)
);