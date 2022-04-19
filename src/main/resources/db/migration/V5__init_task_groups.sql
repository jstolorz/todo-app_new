drop table if exists task_groups;

create table task_groups(
                id int primary key auto_increment,
                description varchar(100) not null,
                done bit
);

alter table task_groups add column created_on datetime null;
alter table task_groups add column updated_on datetime null;

alter table tasks add column task_group_id int null;
alter table tasks add foreign key (task_group_id) references task_groups (id);