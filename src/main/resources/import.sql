# dummy test data
INSERT INTO users (id,name,email,username,password,gender,status,roles) VALUES (null,'Ahmed','ahmed.aau3@gmail.com', 'ahmed', '$2a$10$Mq.47goQCEy6Fls.TvicEelE6qs1OhsvCoUEQm8CGvZ29u7bZJDxi','MALE', 'ACTIVE','ADMIN');
INSERT INTO users (id,name,email,username,password,gender,status,roles) VALUES (null,'Bassam','bassam@lendo.sa', 'bassam', '$2a$10$Mq.47goQCEy6Fls.TvicEelE6qs1OhsvCoUEQm8CGvZ29u7bZJDxi','MALE', 'ACTIVE', 'USER');
INSERT INTO users (id,name,email,username,password,gender,status,roles) VALUES (null,'Omer','omer.aau3@gmail.com', 'omer','$2a$10$Mq.47goQCEy6Fls.TvicEelE6qs1OhsvCoUEQm8CGvZ29u7bZJDxi' ,'MALE', 'ACTIVE','USER');

INSERT INTO posts (id,title,body,user_id) VALUES (null,'Post 1','Post 1 Description', 1);
INSERT INTO posts (id,title,body,user_id) VALUES (null,'Post 2','Post 2 Description', 2);
INSERT INTO posts (id,title,body,user_id) VALUES (null,'Post 3','Post 3 Description', 3);

INSERT INTO posts_comments (id,body,email,post_id) VALUES (null,'Comment 1','ahmed.aau3@gmail.com', 1);
INSERT INTO posts_comments (id,body,email,post_id) VALUES (null,'Comment 1','ahmed.aau3@gmail.com', 2);
INSERT INTO posts_comments (id,body,email,post_id) VALUES (null,'Comment 1','ahmed.aau3@gmail.com', 3);

INSERT INTO posts_comments (id,body,email,post_id) VALUES (null,'Comment 2','bassam@lendo.sa', 1);
INSERT INTO posts_comments (id,body,email,post_id) VALUES (null,'Comment 1','bassam@lendo.sa', 2);
INSERT INTO posts_comments (id,body,email,post_id) VALUES (null,'Comment 1','bassam@lendo.sa', 3);
