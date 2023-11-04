create schema if not exists classes;
SET search_path = classes;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table students
(
    id         uuid primary key NOT NULL DEFAULT uuid_generate_v4(),
    first_name varchar,
    last_name  varchar,
    age        int
);

create table groups
(
    id   uuid primary key NOT NULL DEFAULT uuid_generate_v4(),
    name varchar
);

create table stu_gro
(
    student_id uuid,
    FOREIGN KEY (student_id) references students (id) ON DELETE CASCADE,
    group_id   uuid,
    FOREIGN KEY (group_id) references groups (id) ON DELETE CASCADE
);




