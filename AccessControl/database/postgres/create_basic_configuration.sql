insert into feature_type (id, description) values (1, 'System');
insert into feature_type (id, description) values (2, 'Feature');
insert into feature_type (id, description) values (3, 'Task');
insert into feature_type (id, description) values (4, 'Menu');
insert into feature_type (id, description) values (5, 'Action');
insert into feature_type (id, description) values (6, 'Access');


insert into event_type (id, description) values (1, 'Login');
insert into event_type (id, description) values (2, 'Logout');
insert into event_type (id, description) values (3, 'Create');
insert into event_type (id, description) values (4, 'Update');
insert into event_type (id, description) values (5, 'Delete');
insert into event_type (id, description) values (6, 'Search');
insert into event_type (id, description) values (7, 'Invalid Access');
insert into event_type (id, description) values (8, 'Access');
insert into event_type (id, description) values (9, 'Action');
insert into event_type (id, description) values (10, 'Invalid Version');


insert into event_status (id, description) values (1, 'Created');
insert into event_status (id, description) values (2, 'OK');
insert into event_status (id, description) values (3, 'Error');
insert into event_status (id, description) values (4, 'Invalid Form');



commit;