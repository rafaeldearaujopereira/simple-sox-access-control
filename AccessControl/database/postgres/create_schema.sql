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

