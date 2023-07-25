INSERT INTO t_roles (name) VALUES ('ADMIN');
INSERT INTO t_roles (name) VALUES ('USER');

INSERT INTO t_users (u_name, u_email, U_password) VALUES ('rustam', 'rustam@ya.ru' ,'password');

INSERT INTO t_users_roles (users_id, roles_id) VALUES (1, 2);