
create table student_tracker.users
(
    username varchar(50) not null
        primary key,
    password varchar(68) not null,
    enabled  tinyint     not null
);


create table student_tracker.authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint authorities_idx_1
        unique (username, authority),
    constraint authorities_ibfk_1
        foreign key (username) references student_tracker.users (username)
);

create table student_tracker.members
(
    user_id varchar(50) not null
        primary key,
    pw      char(68)    not null,
    active  tinyint     not null
);

create table student_tracker.roles
(
    user_id varchar(50) not null,
    role    varchar(50) not null,
    constraint roles_user_id_idx_1
        unique (user_id, role),
    constraint roles_user_id_fk
        foreign key (user_id) references student_tracker.members (user_id)
);

create table student_tracker.student
(
    id         int auto_increment
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table student_tracker.employee
(
    id         int auto_increment
        primary key,
    first_name varchar(45) null,
    last_name  varchar(45) null,
    email      varchar(45) null
);

create table instructors
(
    id         int auto_increment primary key,
    first_name nvarchar(45) not null,
    last_name  nvarchar(45) not null,
    email      nvarchar(45) not null
);

create table instructor_details
(
    id              int auto_increment
        primary key,
    instructor_id   int           not null,
    youtube_channel nvarchar(128) null,
    hobby           nvarchar(45)  null,
    constraint instructor_details_instructors_id_fk
        foreign key (instructor_id) references instructors (id)
);

create table courses
(
    id            int auto_increment primary key,
    title         nvarchar(128) null,
    instructor_id int,
    constraint courses_instructors_id_fk
        foreign key (instructor_id) references instructors (id),
    constraint courses_pk unique (title)
);

create table reviews
(
    id        int auto_increment
        primary key,
    comment   nvarchar(256) not null,
    course_id int           not null,
    constraint reviews_courses_id_fk
        foreign key (course_id) references courses (id)
);

create table course_students
(
    course_id  int not null,
    student_id int not null,
    constraint course_students_pk
        primary key (course_id, student_id),
    constraint course_students_courses_id_fk
        foreign key (student_id) references courses (id),
    constraint course_students_student_id_fk
        foreign key (student_id) references student (id)
);
