/*
    Page Name: testData.sql
    Author: Jon Beharrell
    Version: 1.0
    Description: This is a file to load test data into the database.
    Dependencies: This requires the database to be built first.
    Change History: 2015.02.07 Original version by JB
*/

USE someme;

#Inserting data into users table
INSERT INTO users(username, password, email, first_name, last_name)
VALUES (
	'admin',
	'cf937e31bb7e8f98f668026cf404124de786812a',
	'admin@test.com',
	'Admin',
	'Admin'
);

INSERT INTO users(username, password, email, first_name, last_name)
VALUES (
	'jbeharrell',
	'cf937e31bb7e8f98f668026cf404124de786812a',
	'jonbeharrell@test.com',
	'Jon',
	'Beharrell'
);

INSERT INTO users(username, password, email, first_name, last_name)
VALUES (
	'rtaylor',
	'cf937e31bb7e8f98f668026cf404124de786812a',
	'robtaylor@test.com',
	'Robert',
	'Taylor'
);

INSERT INTO users(username, password, email, first_name, last_name)
VALUES (
	'imori',
	'cf937e31bb7e8f98f668026cf404124de786812a',
	'ianmori@test.com',
	'Ian',
	'Mori'
);

INSERT INTO users(username, password, email, first_name, last_name)
VALUES (
	'rforrester',
	'cf937e31bb7e8f98f668026cf404124de786812a',
	'ryanforrester@test.com',
	'Ryan',
	'Forrester'
);

#Inserting data into memes table
INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Duck me',
	'Look at this',
	'http://i.imgur.com/Jzl5Xw7.png',
	1
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Yup',
	'This about sums it up',
	'http://i.imgur.com/7B6V5fg.png',
	1
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Batman Shark',
	'Getting on that #sharktheme',
	'http://i.imgur.com/FvNvgii.gif',
	1
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Manta Ray',
	'More like manta slay',
	'http://i.imgur.com/nb4eyRe.jpg',
	2
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Star Wars',
	'This is a thing',
	'http://i.imgur.com/yvzDW9A.jpg',
	2
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Pasta',
	'No.',
	'http://i.imgur.com/VAePGRh.jpg',
	2
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Cute Cat',
	'moar sharks',
	'http://i.imgur.com/y0u9iAo.jpg',
	3
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Kind of sharks',
	'This makes me sick',
	'http://i.imgur.com/q1y0JbI.jpg',
	3
);

INSERT INTO memes(title, content, source_link, user_id)
VALUES(
	'Sick Roll',
	'These are srs descriptz',
	'http://i.imgur.com/1FF4oGT.jpg',
	3
);

#Inserting data into comments table
INSERT INTO comments(meme_id, user_id, content)
VALUES(
	1,
	1,
	'Sweet picture' 
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	1,
	2,
	'I really like this'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	1,
	3,
	'Did you expect me to make seperate comments for each meme?  This is test data!'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	2,
	1,
	'Sweet picture'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	2,
	2,
	'I really like this'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	2,
	3,
	'Did you expect me to make seperate comments for each meme?  This is test data!'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	3,
	1,
	'Sweet picture'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	3,
	2,
	'I really like this'
);

INSERT INTO comments(meme_id, user_id, content)
VALUES(
	3,
	3,
	'Did you expect me to make seperate comments for each meme?  This is test data!'
);

#Inserting test data into meme_votes table
INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	1,
	1,
	0
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	1,
	2,
	1
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	1,
	3,
	1
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	2,
	1,
	1
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	2,
	2,
	0
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	2,
	3,
	1
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	3,
	1,
	1
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	3,
	2,
	1
);

INSERT INTO meme_votes(user_id, meme_id, is_positive)
VALUES(
	3,
	3,
	0
);