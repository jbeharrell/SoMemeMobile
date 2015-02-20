/*
    Page Name: someme.sql
    Author: Jon Beharrell
    Version: 1.0
    Description: This is the SQL file that will create the database, tables, and fields required for the SoMeme web application.
    Dependencies: This file has no dependencies.
    Change History: 2015.01.29 Original version by JB
*/

CREATE DATABASE IF NOT EXISTS someme;

USE someme;

CREATE TABLE users
(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
username VARCHAR(15) NOT NULL UNIQUE,
password VARCHAR(40) NOT NULL,
email VARCHAR(40) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
dob DATE,
gender VARCHAR(1),
country VARCHAR(20),
date_joined TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
default_sort NUMERIC(1) DEFAULT 1,
PRIMARY KEY (id)
);

CREATE TABLE memes
(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
title VARCHAR(40) NOT NULL,
content TEXT(300) NOT NULL,
source_link VARCHAR(100) NOT NULL,
user_id INT UNSIGNED NOT NULL,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
views NUMERIC(20) DEFAULT 0,
downloads NUMERIC(20) DEFAULT 0,
user_views NUMERIC(20) DEFAULT 0,
FOREIGN KEY (user_id) REFERENCES users(id),
PRIMARY KEY (id)
);

CREATE TABLE comments
(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
meme_id INT UNSIGNED NOT NULL,
user_id INT UNSIGNED NOT NULL,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
content TEXT(300) NOT NULL,
parent_id INT UNSIGNED,
FOREIGN KEY (meme_id) REFERENCES memes(id),
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (parent_id) REFERENCES comments(id),
PRIMARY KEY (id)
);

CREATE TABLE meme_votes
(
user_id INT UNSIGNED NOT NULL,
meme_id INT UNSIGNED NOT NULL,
is_positive BOOLEAN NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (meme_id) REFERENCES memes(id),
PRIMARY KEY (user_id, meme_id)
);

CREATE TABLE comment_votes
(
user_id INT UNSIGNED NOT NULL,
comment_id INT UNSIGNED NOT NULL,
is_positive BOOLEAN NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (comment_id) REFERENCES comments(id),
PRIMARY KEY (user_id, comment_id)
);

CREATE TABLE messages
(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
user_id_to INT UNSIGNED NOT NULL,
user_id_from INT UNSIGNED NOT NULL,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
title VARCHAR(40) NOT NULL,
content TEXT(300) NOT NULL,
FOREIGN KEY (user_id_to) REFERENCES users(id),
FOREIGN KEY (user_id_from) REFERENCES users(id),
PRIMARY KEY (id)
);

CREATE TABLE notifications
(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
user_id INT UNSIGNED NOT NULL,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
source_link VARCHAR(100) NOT NULL,
content VARCHAR(200) NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
PRIMARY KEY (id)
);

CREATE TABLE favorites
(
user_id INT UNSIGNED NOT NULL,
meme_id INT UNSIGNED NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (meme_id) REFERENCES memes(id),
PRIMARY KEY (user_id, meme_id)
);

CREATE TABLE country_codes
(
code VARCHAR(2) NOT NULL,
country VARCHAR(29) NOT NULL,
PRIMARY KEY (code)
);

CREATE TABLE error_logging
(
error_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
error_code INT NOT NULL,
error_desc VARCHAR(1000) NOT NULL,
error_source VARCHAR(1000) NOT NULL,
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (error_id)
);

#Inserting the country codes for users to choose.
INSERT INTO country_codes(code, country) VALUES('AF', 'Afghanistan');
INSERT INTO country_codes(code, country) VALUES('AL', 'Albania');
INSERT INTO country_codes(code, country) VALUES('DZ', 'Algeria');
INSERT INTO country_codes(code, country) VALUES('AS', 'American Samoa');
INSERT INTO country_codes(code, country) VALUES('AD', 'Andorra');
INSERT INTO country_codes(code, country) VALUES('AO', 'Angola');
INSERT INTO country_codes(code, country) VALUES('AI', 'Anguilla');
INSERT INTO country_codes(code, country) VALUES('AG', 'Antigua &amp; Barbuda');
INSERT INTO country_codes(code, country) VALUES('AR', 'Argentina');
INSERT INTO country_codes(code, country) VALUES('AA', 'Armenia');
INSERT INTO country_codes(code, country) VALUES('AW', 'Aruba');
INSERT INTO country_codes(code, country) VALUES('AU', 'Australia');
INSERT INTO country_codes(code, country) VALUES('AT', 'Austria');
INSERT INTO country_codes(code, country) VALUES('AZ', 'Azerbaijan');
INSERT INTO country_codes(code, country) VALUES('BS', 'Bahamas');
INSERT INTO country_codes(code, country) VALUES('BH', 'Bahrain');
INSERT INTO country_codes(code, country) VALUES('BD', 'Bangladesh');
INSERT INTO country_codes(code, country) VALUES('BB', 'Barbados');
INSERT INTO country_codes(code, country) VALUES('BY', 'Belarus');
INSERT INTO country_codes(code, country) VALUES('BE', 'Belgium');
INSERT INTO country_codes(code, country) VALUES('BZ', 'Belize');
INSERT INTO country_codes(code, country) VALUES('BJ', 'Benin');
INSERT INTO country_codes(code, country) VALUES('BM', 'Bermuda');
INSERT INTO country_codes(code, country) VALUES('BT', 'Bhutan');
INSERT INTO country_codes(code, country) VALUES('BO', 'Bolivia');
INSERT INTO country_codes(code, country) VALUES('BL', 'Bonaire');
INSERT INTO country_codes(code, country) VALUES('BA', 'Bosnia &amp; Herzegovina');
INSERT INTO country_codes(code, country) VALUES('BW', 'Botswana');
INSERT INTO country_codes(code, country) VALUES('BR', 'Brazil');
INSERT INTO country_codes(code, country) VALUES('BC', 'British Indian Ocean Ter');
INSERT INTO country_codes(code, country) VALUES('BN', 'Brunei');
INSERT INTO country_codes(code, country) VALUES('BG', 'Bulgaria');
INSERT INTO country_codes(code, country) VALUES('BF', 'Burkina Faso');
INSERT INTO country_codes(code, country) VALUES('BI', 'Burundi');
INSERT INTO country_codes(code, country) VALUES('KH', 'Cambodia');
INSERT INTO country_codes(code, country) VALUES('CM', 'Cameroon');
INSERT INTO country_codes(code, country) VALUES('CA', 'Canada');
INSERT INTO country_codes(code, country) VALUES('IC', 'Canary Islands');
INSERT INTO country_codes(code, country) VALUES('CV', 'Cape Verde');
INSERT INTO country_codes(code, country) VALUES('KY', 'Cayman Islands');
INSERT INTO country_codes(code, country) VALUES('CF', 'Central African Republic');
INSERT INTO country_codes(code, country) VALUES('TD', 'Chad');
INSERT INTO country_codes(code, country) VALUES('CD', 'Channel Islands');
INSERT INTO country_codes(code, country) VALUES('CL', 'Chile');
INSERT INTO country_codes(code, country) VALUES('CN', 'China');
INSERT INTO country_codes(code, country) VALUES('CI', 'Christmas Island');
INSERT INTO country_codes(code, country) VALUES('CS', 'Cocos Island');
INSERT INTO country_codes(code, country) VALUES('CO', 'Colombia');
INSERT INTO country_codes(code, country) VALUES('CC', 'Comoros');
INSERT INTO country_codes(code, country) VALUES('CG', 'Congo');
INSERT INTO country_codes(code, country) VALUES('CK', 'Cook Islands');
INSERT INTO country_codes(code, country) VALUES('CR', 'Costa Rica');
INSERT INTO country_codes(code, country) VALUES('CT', 'Cote D\'Ivoire');
INSERT INTO country_codes(code, country) VALUES('HR', 'Croatia');
INSERT INTO country_codes(code, country) VALUES('CU', 'Cuba');
INSERT INTO country_codes(code, country) VALUES('CB', 'Curacao');
INSERT INTO country_codes(code, country) VALUES('CY', 'Cyprus');
INSERT INTO country_codes(code, country) VALUES('CZ', 'Czech Republic');
INSERT INTO country_codes(code, country) VALUES('DK', 'Denmark');
INSERT INTO country_codes(code, country) VALUES('DJ', 'Djibouti');
INSERT INTO country_codes(code, country) VALUES('DM', 'Dominica');
INSERT INTO country_codes(code, country) VALUES('DO', 'Dominican Republic');
INSERT INTO country_codes(code, country) VALUES('TM', 'East Timor');
INSERT INTO country_codes(code, country) VALUES('EC', 'Ecuador');
INSERT INTO country_codes(code, country) VALUES('EG', 'Egypt');
INSERT INTO country_codes(code, country) VALUES('SV', 'El Salvador');
INSERT INTO country_codes(code, country) VALUES('GQ', 'Equatorial Guinea');
INSERT INTO country_codes(code, country) VALUES('ER', 'Eritrea');
INSERT INTO country_codes(code, country) VALUES('EE', 'Estonia');
INSERT INTO country_codes(code, country) VALUES('ET', 'Ethiopia');
INSERT INTO country_codes(code, country) VALUES('FA', 'Falkland Islands');
INSERT INTO country_codes(code, country) VALUES('FO', 'Faroe Islands');
INSERT INTO country_codes(code, country) VALUES('FJ', 'Fiji');
INSERT INTO country_codes(code, country) VALUES('FI', 'Finland');
INSERT INTO country_codes(code, country) VALUES('FR', 'France');
INSERT INTO country_codes(code, country) VALUES('GF', 'French Guiana');
INSERT INTO country_codes(code, country) VALUES('PF', 'French Polynesia');
INSERT INTO country_codes(code, country) VALUES('FS', 'French Southern Ter');
INSERT INTO country_codes(code, country) VALUES('GA', 'Gabon');
INSERT INTO country_codes(code, country) VALUES('GM', 'Gambia');
INSERT INTO country_codes(code, country) VALUES('GE', 'Georgia');
INSERT INTO country_codes(code, country) VALUES('DE', 'Germany');
INSERT INTO country_codes(code, country) VALUES('GH', 'Ghana');
INSERT INTO country_codes(code, country) VALUES('GI', 'Gibraltar');
INSERT INTO country_codes(code, country) VALUES('GR', 'Greece');
INSERT INTO country_codes(code, country) VALUES('GL', 'Greenland');
INSERT INTO country_codes(code, country) VALUES('GD', 'Grenada');
INSERT INTO country_codes(code, country) VALUES('GP', 'Guadeloupe');
INSERT INTO country_codes(code, country) VALUES('GU', 'Guam');
INSERT INTO country_codes(code, country) VALUES('GT', 'Guatemala');
INSERT INTO country_codes(code, country) VALUES('GN', 'Guinea');
INSERT INTO country_codes(code, country) VALUES('GY', 'Guyana');
INSERT INTO country_codes(code, country) VALUES('HT', 'Haiti');
INSERT INTO country_codes(code, country) VALUES('HW', 'Hawaii');
INSERT INTO country_codes(code, country) VALUES('HN', 'Honduras');
INSERT INTO country_codes(code, country) VALUES('HK', 'Hong Kong');
INSERT INTO country_codes(code, country) VALUES('HU', 'Hungary');
INSERT INTO country_codes(code, country) VALUES('IS', 'Iceland');
INSERT INTO country_codes(code, country) VALUES('IN', 'India');
INSERT INTO country_codes(code, country) VALUES('ID', 'Indonesia');
INSERT INTO country_codes(code, country) VALUES('IA', 'Iran');
INSERT INTO country_codes(code, country) VALUES('IQ', 'Iraq');
INSERT INTO country_codes(code, country) VALUES('IR', 'Ireland');
INSERT INTO country_codes(code, country) VALUES('IM', 'Isle of Man');
INSERT INTO country_codes(code, country) VALUES('IL', 'Israel');
INSERT INTO country_codes(code, country) VALUES('IT', 'Italy');
INSERT INTO country_codes(code, country) VALUES('JM', 'Jamaica');
INSERT INTO country_codes(code, country) VALUES('JP', 'Japan');
INSERT INTO country_codes(code, country) VALUES('JO', 'Jordan');
INSERT INTO country_codes(code, country) VALUES('KZ', 'Kazakhstan');
INSERT INTO country_codes(code, country) VALUES('KE', 'Kenya');
INSERT INTO country_codes(code, country) VALUES('KI', 'Kiribati');
INSERT INTO country_codes(code, country) VALUES('NK', 'Korea North');
INSERT INTO country_codes(code, country) VALUES('KS', 'Korea South');
INSERT INTO country_codes(code, country) VALUES('KW', 'Kuwait');
INSERT INTO country_codes(code, country) VALUES('KG', 'Kyrgyzstan');
INSERT INTO country_codes(code, country) VALUES('LA', 'Laos');
INSERT INTO country_codes(code, country) VALUES('LV', 'Latvia');
INSERT INTO country_codes(code, country) VALUES('LB', 'Lebanon');
INSERT INTO country_codes(code, country) VALUES('LS', 'Lesotho');
INSERT INTO country_codes(code, country) VALUES('LR', 'Liberia');
INSERT INTO country_codes(code, country) VALUES('LY', 'Libya');
INSERT INTO country_codes(code, country) VALUES('LI', 'Liechtenstein');
INSERT INTO country_codes(code, country) VALUES('LT', 'Lithuania');
INSERT INTO country_codes(code, country) VALUES('LU', 'Luxembourg');
INSERT INTO country_codes(code, country) VALUES('MO', 'Macau');
INSERT INTO country_codes(code, country) VALUES('MK', 'Macedonia');
INSERT INTO country_codes(code, country) VALUES('MG', 'Madagascar');
INSERT INTO country_codes(code, country) VALUES('MY', 'Malaysia');
INSERT INTO country_codes(code, country) VALUES('MW', 'Malawi');
INSERT INTO country_codes(code, country) VALUES('MV', 'Maldives');
INSERT INTO country_codes(code, country) VALUES('ML', 'Mali');
INSERT INTO country_codes(code, country) VALUES('MT', 'Malta');
INSERT INTO country_codes(code, country) VALUES('MH', 'Marshall Islands');
INSERT INTO country_codes(code, country) VALUES('MQ', 'Martinique');
INSERT INTO country_codes(code, country) VALUES('MR', 'Mauritania');
INSERT INTO country_codes(code, country) VALUES('MU', 'Mauritius');
INSERT INTO country_codes(code, country) VALUES('YT', 'Mayotte');
INSERT INTO country_codes(code, country) VALUES('MX', 'Mexico');
INSERT INTO country_codes(code, country) VALUES('MI', 'Midway Islands');
INSERT INTO country_codes(code, country) VALUES('MD', 'Moldova');
INSERT INTO country_codes(code, country) VALUES('MC', 'Monaco');
INSERT INTO country_codes(code, country) VALUES('MN', 'Mongolia');
INSERT INTO country_codes(code, country) VALUES('MS', 'Montserrat');
INSERT INTO country_codes(code, country) VALUES('MA', 'Morocco');
INSERT INTO country_codes(code, country) VALUES('MZ', 'Mozambique');
INSERT INTO country_codes(code, country) VALUES('MM', 'Myanmar');
INSERT INTO country_codes(code, country) VALUES('NA', 'Nambia');
INSERT INTO country_codes(code, country) VALUES('NU', 'Nauru');
INSERT INTO country_codes(code, country) VALUES('NP', 'Nepal');
INSERT INTO country_codes(code, country) VALUES('AN', 'Netherland Antilles');
INSERT INTO country_codes(code, country) VALUES('NL', 'Netherlands (Holland, Europe)');
INSERT INTO country_codes(code, country) VALUES('NV', 'Nevis');
INSERT INTO country_codes(code, country) VALUES('NC', 'New Caledonia');
INSERT INTO country_codes(code, country) VALUES('NZ', 'New Zealand');
INSERT INTO country_codes(code, country) VALUES('NI', 'Nicaragua');
INSERT INTO country_codes(code, country) VALUES('NE', 'Niger');
INSERT INTO country_codes(code, country) VALUES('NG', 'Nigeria');
INSERT INTO country_codes(code, country) VALUES('NW', 'Niue');
INSERT INTO country_codes(code, country) VALUES('NF', 'Norfolk Island');
INSERT INTO country_codes(code, country) VALUES('NO', 'Norway');
INSERT INTO country_codes(code, country) VALUES('OM', 'Oman');
INSERT INTO country_codes(code, country) VALUES('PK', 'Pakistan');
INSERT INTO country_codes(code, country) VALUES('PW', 'Palau Island');
INSERT INTO country_codes(code, country) VALUES('PS', 'Palestine');
INSERT INTO country_codes(code, country) VALUES('PA', 'Panama');
INSERT INTO country_codes(code, country) VALUES('PG', 'Papua New Guinea');
INSERT INTO country_codes(code, country) VALUES('PY', 'Paraguay');
INSERT INTO country_codes(code, country) VALUES('PE', 'Peru');
INSERT INTO country_codes(code, country) VALUES('PH', 'Philippines');
INSERT INTO country_codes(code, country) VALUES('PO', 'Pitcairn Island');
INSERT INTO country_codes(code, country) VALUES('PL', 'Poland');
INSERT INTO country_codes(code, country) VALUES('PT', 'Portugal');
INSERT INTO country_codes(code, country) VALUES('PR', 'Puerto Rico');
INSERT INTO country_codes(code, country) VALUES('QA', 'Qatar');
INSERT INTO country_codes(code, country) VALUES('ME', 'Republic of Montenegro');
INSERT INTO country_codes(code, country) VALUES('RE', 'Reunion');
INSERT INTO country_codes(code, country) VALUES('RO', 'Romania');
INSERT INTO country_codes(code, country) VALUES('RU', 'Russia');
INSERT INTO country_codes(code, country) VALUES('RW', 'Rwanda');
INSERT INTO country_codes(code, country) VALUES('NT', 'St Barthelemy');
INSERT INTO country_codes(code, country) VALUES('EU', 'St Eustatius');
INSERT INTO country_codes(code, country) VALUES('HE', 'St Helena');
INSERT INTO country_codes(code, country) VALUES('KN', 'St Kitts-Nevis');
INSERT INTO country_codes(code, country) VALUES('LC', 'St Lucia');
INSERT INTO country_codes(code, country) VALUES('MB', 'St Maarten');
INSERT INTO country_codes(code, country) VALUES('PM', 'St Pierre &amp; Miquelon');
INSERT INTO country_codes(code, country) VALUES('VC', 'St Vincent &amp; Grenadines');
INSERT INTO country_codes(code, country) VALUES('SP', 'Saipan');
INSERT INTO country_codes(code, country) VALUES('SO', 'Samoa');
INSERT INTO country_codes(code, country) VALUES('SM', 'San Marino');
INSERT INTO country_codes(code, country) VALUES('ST', 'Sao Tome &amp; Principe');
INSERT INTO country_codes(code, country) VALUES('SA', 'Saudi Arabia');
INSERT INTO country_codes(code, country) VALUES('SN', 'Senegal');
INSERT INTO country_codes(code, country) VALUES('RS', 'Serbia');
INSERT INTO country_codes(code, country) VALUES('SC', 'Seychelles');
INSERT INTO country_codes(code, country) VALUES('SL', 'Sierra Leone');
INSERT INTO country_codes(code, country) VALUES('SG', 'Singapore');
INSERT INTO country_codes(code, country) VALUES('SK', 'Slovakia');
INSERT INTO country_codes(code, country) VALUES('SI', 'Slovenia');
INSERT INTO country_codes(code, country) VALUES('SB', 'Solomon Islands');
INSERT INTO country_codes(code, country) VALUES('OI', 'Somalia');
INSERT INTO country_codes(code, country) VALUES('ZA', 'South Africa');
INSERT INTO country_codes(code, country) VALUES('ES', 'Spain');
INSERT INTO country_codes(code, country) VALUES('LK', 'Sri Lanka');
INSERT INTO country_codes(code, country) VALUES('SD', 'Sudan');
INSERT INTO country_codes(code, country) VALUES('SR', 'Suriname');
INSERT INTO country_codes(code, country) VALUES('SZ', 'Swaziland');
INSERT INTO country_codes(code, country) VALUES('SE', 'Sweden');
INSERT INTO country_codes(code, country) VALUES('CH', 'Switzerland');
INSERT INTO country_codes(code, country) VALUES('SY', 'Syria');
INSERT INTO country_codes(code, country) VALUES('TA', 'Tahiti');
INSERT INTO country_codes(code, country) VALUES('TW', 'Taiwan');
INSERT INTO country_codes(code, country) VALUES('TJ', 'Tajikistan');
INSERT INTO country_codes(code, country) VALUES('TZ', 'Tanzania');
INSERT INTO country_codes(code, country) VALUES('TH', 'Thailand');
INSERT INTO country_codes(code, country) VALUES('TG', 'Togo');
INSERT INTO country_codes(code, country) VALUES('TK', 'Tokelau');
INSERT INTO country_codes(code, country) VALUES('TO', 'Tonga');
INSERT INTO country_codes(code, country) VALUES('TT', 'Trinidad &amp; Tobago');
INSERT INTO country_codes(code, country) VALUES('TN', 'Tunisia');
INSERT INTO country_codes(code, country) VALUES('TR', 'Turkey');
INSERT INTO country_codes(code, country) VALUES('TU', 'Turkmenistan');
INSERT INTO country_codes(code, country) VALUES('TC', 'Turks &amp; Caicos Is');
INSERT INTO country_codes(code, country) VALUES('TV', 'Tuvalu');
INSERT INTO country_codes(code, country) VALUES('UG', 'Uganda');
INSERT INTO country_codes(code, country) VALUES('UA', 'Ukraine');
INSERT INTO country_codes(code, country) VALUES('AE', 'United Arab Emirates');
INSERT INTO country_codes(code, country) VALUES('GB', 'United Kingdom');
INSERT INTO country_codes(code, country) VALUES('US', 'United States of America');
INSERT INTO country_codes(code, country) VALUES('UY', 'Uruguay');
INSERT INTO country_codes(code, country) VALUES('UZ', 'Uzbekistan');
INSERT INTO country_codes(code, country) VALUES('VU', 'Vanuatu');
INSERT INTO country_codes(code, country) VALUES('VS', 'Vatican City State');
INSERT INTO country_codes(code, country) VALUES('VE', 'Venezuela');
INSERT INTO country_codes(code, country) VALUES('VN', 'Vietnam');
INSERT INTO country_codes(code, country) VALUES('VB', 'Virgin Islands (Brit)');
INSERT INTO country_codes(code, country) VALUES('VA', 'Virgin Islands (USA)');
INSERT INTO country_codes(code, country) VALUES('WK', 'Wake Island');
INSERT INTO country_codes(code, country) VALUES('WF', 'Wallis &amp; Futana Is');
INSERT INTO country_codes(code, country) VALUES('YE', 'Yemen');
INSERT INTO country_codes(code, country) VALUES('ZR', 'Zaire');
INSERT INTO country_codes(code, country) VALUES('ZM', 'Zambia');
INSERT INTO country_codes(code, country) VALUES('ZW', 'Zimbabwe');