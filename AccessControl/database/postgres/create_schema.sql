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



create table event_status (
id smallint not null,
description varchar(60) not null
);

alter table event_status add constraint event_status_pk primary key (id);

alter table event_status add constraint event_status_uk_desc unique (description);




create table system_user (
id bigint not null,
name varchar(100) not null, 
login varchar(30) not null,
email varchar(100) not null, 
active boolean not null default true,
manager_id bigint null
);

alter table system_user add constraint system_user_pk primary key (id);

alter table system_user add constraint system_user_uk_login unique (login);

alter table system_user add constraint system_user_fk_manager foreign key (manager_id) references system_user (id);




create table feature (
id bigint not null,
parent_id bigint null, 
code varchar(50) not null, 
name varchar(100) not null, 
description varchar(200) not null,
feature_type_id smallint not null, 
owner_id bigint null
);

alter table feature add constraint feature_pk primary key (id);

alter table feature add constraint feature_uk_logic unique (parent_id, name);

alter table feature add constraint feature_uk_code unique (code);

alter table feature add constraint feature_fk_parent foreign key (parent_id) references feature (id);

alter table feature add constraint feature_fk_type foreign key (feature_type_id) references feature_type (id);

alter table feature add constraint feature_fk_owner foreign key (owner_id) references system_user (id);



create table role (
id bigint not null,
name varchar(100) not null,
description varchar(200) not null
);

alter table role add constraint role_pk primary key (id);

alter table role add constraint role_uk_name unique (name);



create table user_role (
user_id bigint not null, 
role_id bigint not null
);


alter table user_role add constraint user_role_pk primary key (user_id, role_id);

alter table user_role add constraint user_role_fk_user foreign key (user_id) references system_user (id);

alter table user_role add constraint user_role_fk_role foreign key (role_id) references role (id);



create table access (
role_id bigint not null,
feature_id bigint not null
);

alter table access add constraint access_pk primary key (role_id, feature_id);

alter table access add constraint access_fk_role foreign key (role_id) references role (id);

alter table access add constraint access_fk_feature foreign key (feature_id) references feature (id);



create table version (
id bigint not null, 
feature_id bigint not null,
release_date date not null,
version_number numeric(5, 2) not null,
name varchar(100) not null,
description varchar(200) not null,
mandatory boolean not null default false
);

alter table version add constraint version_pk primary key (id);

alter table version add constraint version_uk_logic unique (feature_id, version_number);

alter table version add constraint version_fk_feature foreign key (feature_id) references feature (id);


create table session (
id bigint not null, 
user_id bigint null,
start_date date not null,
end_date date not null,
ip_address varchar(50) not null,
host_name varchar(50) not null,
external_id varchar(50) null
);

alter table session add constraint session_pk primary key (id);

alter table session add constraint session_fk_user foreign key (user_id) references system_user (id);




create table event (
id bigint not null, 
event_type_id smallint not null,
event_status_id smallint not null, 
feature_id bigint not null,
session_id bigint null,
event_date date not null
);

alter table event add constraint event_pk primary key (id);

alter table event add constraint event_fk_type foreign key (event_type_id) references event_type (id);

alter table event add constraint event_fk_status foreign key (event_status_id) references event_status (id);

alter table event add constraint event_fk_feature foreign key (feature_id) references feature (id);

alter table event add constraint event_fk_session foreign key (session_id) references session (id);



create table event_detail (
id bigint not null,
event_id bigint not null, 
field_name varchar(4000) not null,
field_value varchar(4000) not null
);

alter table event_detail add constraint event_detail_pk primary key (id);

alter table event_detail add constraint event_detail_fk_event foreign key (event_id) references event (id);






create sequence user_seq;

create sequence feature_seq;

create sequence role_seq;

create sequence version_seq;

create sequence event_seq;

create sequence event_detail_seq;

create sequence session_seq;
