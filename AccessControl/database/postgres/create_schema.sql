create table feature_type (
id smallint not null,
description varchar(60) not null
);

alter table feature_type add constraint feature_type_pk primary key (id);

alter table feature_type add constraint feature_type_uk_desc unique (description);



create table event_type (
id smallint not null,
description varchar(60) not null
);

alter table event_type add constraint event_type_pk primary key (id);

alter table event_type add constraint event_type_uk_desc unique (description);



create table system_user (
id bigint not null,
name varchar(100) not null, 
login varchar(30) not null,
email varchar(100) not null, 
active boolean not null default true,
manager_id bigint null
);

alter table 





create table feature (
id bigint not null,
parent_id bigint null, 
code varchar(50) not null, 
name varchar(100) not null, 
description varchar(200) not null,
feature_type_id smallint not null, 
owner_id bigint null
);





create sequence user_seq;

create sequence feature_seq;