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




create sequence user_seq;

create sequence feature_seq;