INSERT INTO tb_role(name) VALUES ('USER');

INSERT INTO tb_user(login, password, name) VALUES ('ritinha', '$2a$16$wbJbFL1fR2fgz88ANOAm3e9MLozBvimPBDOYEKrTePZIIMT7L7KBy', 'Ritinha');
INSERT INTO tb_user(login, password, name) VALUES ('zequinha', '$2a$16$wbJbFL1fR2fgz88ANOAm3e9MLozBvimPBDOYEKrTePZIIMT7L7KBy', 'Zequinha');

INSERT INTO tb_todo(name, description, done, priority, user_id) VALUES ('Lavar a louça', 'Lorem ipsum dolor sit amet', 1, 'BAIXA', 1);
INSERT INTO tb_todo(name, description, done, priority, user_id) VALUES ('Organizar a despensa', 'Lorem ipsum dolor sit amet', 0, 'BAIXA', 1);
INSERT INTO tb_todo(name, description, done, priority, user_id) VALUES ('Passar aspirador na sala', 'Lorem ipsum dolor sit amet', 1, 'BAIXA', 2);
INSERT INTO tb_todo(name, description, done, priority, user_id) VALUES ('Trocar a roupa de cama', 'Lorem ipsum dolor sit amet', 0, 'BAIXA', 2);
INSERT INTO tb_todo(name, description, done, priority, user_id) VALUES ('Regar as plantas', 'Lorem ipsum dolor sit amet', 0, 'BAIXA', 1);
INSERT INTO tb_todo(name, description, done, priority, user_id) VALUES ('Tirar o lixo', 'Lorem ipsum dolor sit amet', 1, 'BAIXA', 2);

INSERT INTO tb_user_role(user_id, role_id) VALUES(1, 1);
INSERT INTO tb_user_role(user_id, role_id) VALUES(2, 1);