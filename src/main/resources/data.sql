insert into users(id, username, password,first_name) values(111, 'nikos','$2a$10$wKUQoxSq376tCgtFcuV0PubcCn9MI666eQxER4FBbuXrDib6cnrVe','Nikos');
insert into users(id, username, password,first_name) values(222, 'johndoe','$2a$10$wKUQoxSq376tCgtFcuV0PubcCn9MI666eQxER4FBbuXrDib6cnrVe','Jonh Doe');
insert into movies(id, title, user_id, publication_date) values(1,'movie1', 111, sysdate-0.1);
insert into movies(id, title, user_id, publication_date) values(2,'movie2', 111, sysdate-1);
insert into movies(id, title, user_id, publication_date) values(3,'movie3', 111, sysdate-35);
insert into movies(id, title, user_id, publication_date) values(4,'movie4', 111, sysdate-200);
insert into movies(id, title, user_id, publication_date) values(5,'movie5', 222, sysdate-400);

insert into movie_like values(111,1);
insert into movie_hate values(111,2);

update movies set description='Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sit amet dui odio.
Mauris scelerisque eget nisi vel dignissim. Ut fermentum, magna a convallis commodo, sem mauris volutpat felis, at
posuere mi eros ut urna. Suspendisse in augue ut augue cursus tempor nec vel quam. Nullam neque sem, cursus at massa
sagittis, imperdiet elementum velit';