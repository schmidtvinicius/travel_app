PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

CREATE TABLE Country (code CHAR (2) PRIMARY KEY NOT NULL UNIQUE, name VARCHAR (50) NOT NULL);
INSERT INTO Country (code, name) VALUES ('AD', 'Andorra');
INSERT INTO Country (code, name) VALUES ('AE', 'United Arab Emirates');
INSERT INTO Country (code, name) VALUES ('AF', 'Afghanistan');
INSERT INTO Country (code, name) VALUES ('AG', 'Antigua and Barbuda');
INSERT INTO Country (code, name) VALUES ('AI', 'Anguilla');
INSERT INTO Country (code, name) VALUES ('AL', 'Albania');
INSERT INTO Country (code, name) VALUES ('AM', 'Armenia');
INSERT INTO Country (code, name) VALUES ('AO', 'Angola');
INSERT INTO Country (code, name) VALUES ('AQ', 'Antarctica');
INSERT INTO Country (code, name) VALUES ('AR', 'Argentina');
INSERT INTO Country (code, name) VALUES ('AS', 'American Samoa');
INSERT INTO Country (code, name) VALUES ('AT', 'Austria');
INSERT INTO Country (code, name) VALUES ('AU', 'Australia');
INSERT INTO Country (code, name) VALUES ('AW', 'Aruba');
INSERT INTO Country (code, name) VALUES ('AX', 'Åland Islands');
INSERT INTO Country (code, name) VALUES ('AZ', 'Azerbaijan');
INSERT INTO Country (code, name) VALUES ('BA', 'Bosnia and Herzegovina');
INSERT INTO Country (code, name) VALUES ('BB', 'Barbados');
INSERT INTO Country (code, name) VALUES ('BD', 'Bangladesh');
INSERT INTO Country (code, name) VALUES ('BE', 'Belgium');
INSERT INTO Country (code, name) VALUES ('BF', 'Burkina Faso');
INSERT INTO Country (code, name) VALUES ('BG', 'Bulgaria');
INSERT INTO Country (code, name) VALUES ('BH', 'Bahrain');
INSERT INTO Country (code, name) VALUES ('BI', 'Burundi');
INSERT INTO Country (code, name) VALUES ('BJ', 'Benin');
INSERT INTO Country (code, name) VALUES ('BL', 'Saint Barthélemy');
INSERT INTO Country (code, name) VALUES ('BM', 'Bermuda');
INSERT INTO Country (code, name) VALUES ('BN', 'Brunei Darussalam');
INSERT INTO Country (code, name) VALUES ('BO', 'Bolivia (Plurinational State of)');
INSERT INTO Country (code, name) VALUES ('BQ', 'Bonaire, Sint Eustatius and Saba');
INSERT INTO Country (code, name) VALUES ('BR', 'Brazil');
INSERT INTO Country (code, name) VALUES ('BS', 'Bahamas');
INSERT INTO Country (code, name) VALUES ('BT', 'Bhutan');
INSERT INTO Country (code, name) VALUES ('BV', 'Bouvet Island');
INSERT INTO Country (code, name) VALUES ('BW', 'Botswana');
INSERT INTO Country (code, name) VALUES ('BY', 'Belarus');
INSERT INTO Country (code, name) VALUES ('BZ', 'Belize');
INSERT INTO Country (code, name) VALUES ('CA', 'Canada');
INSERT INTO Country (code, name) VALUES ('CC', 'Cocos (Keeling) Islands');
INSERT INTO Country (code, name) VALUES ('CD', 'Congo, Democratic Republic of the');
INSERT INTO Country (code, name) VALUES ('CF', 'Central African Republic');
INSERT INTO Country (code, name) VALUES ('CG', 'Congo');
INSERT INTO Country (code, name) VALUES ('CH', 'Switzerland');
INSERT INTO Country (code, name) VALUES ('CI', 'Côte d''Ivoire');
INSERT INTO Country (code, name) VALUES ('CK', 'Cook Islands');
INSERT INTO Country (code, name) VALUES ('CL', 'Chile');
INSERT INTO Country (code, name) VALUES ('CM', 'Cameroon');
INSERT INTO Country (code, name) VALUES ('CN', 'China');
INSERT INTO Country (code, name) VALUES ('CO', 'Colombia');
INSERT INTO Country (code, name) VALUES ('CR', 'Costa Rica');
INSERT INTO Country (code, name) VALUES ('CU', 'Cuba');
INSERT INTO Country (code, name) VALUES ('CV', 'Cabo Verde');
INSERT INTO Country (code, name) VALUES ('CW', 'Curaçao');
INSERT INTO Country (code, name) VALUES ('CX', 'Christmas Island');
INSERT INTO Country (code, name) VALUES ('CY', 'Cyprus');
INSERT INTO Country (code, name) VALUES ('CZ', 'Czechia');
INSERT INTO Country (code, name) VALUES ('DE', 'Germany');
INSERT INTO Country (code, name) VALUES ('DJ', 'Djibouti');
INSERT INTO Country (code, name) VALUES ('DK', 'Denmark');
INSERT INTO Country (code, name) VALUES ('DM', 'Dominica');
INSERT INTO Country (code, name) VALUES ('DO', 'Dominican Republic');
INSERT INTO Country (code, name) VALUES ('DZ', 'Algeria');
INSERT INTO Country (code, name) VALUES ('EC', 'Ecuador');
INSERT INTO Country (code, name) VALUES ('EE', 'Estonia');
INSERT INTO Country (code, name) VALUES ('EG', 'Egypt');
INSERT INTO Country (code, name) VALUES ('EH', 'Western Sahara');
INSERT INTO Country (code, name) VALUES ('ER', 'Eritrea');
INSERT INTO Country (code, name) VALUES ('ES', 'Spain');
INSERT INTO Country (code, name) VALUES ('ET', 'Ethiopia');
INSERT INTO Country (code, name) VALUES ('FI', 'Finland');
INSERT INTO Country (code, name) VALUES ('FJ', 'Fiji');
INSERT INTO Country (code, name) VALUES ('FK', 'Falkland Islands (Malvinas)');
INSERT INTO Country (code, name) VALUES ('FM', 'Micronesia (Federated States of)');
INSERT INTO Country (code, name) VALUES ('FO', 'Faroe Islands');
INSERT INTO Country (code, name) VALUES ('FR', 'France');
INSERT INTO Country (code, name) VALUES ('GA', 'Gabon');
INSERT INTO Country (code, name) VALUES ('GB', 'United Kingdom of Great Britain and Northern Ireland');
INSERT INTO Country (code, name) VALUES ('GD', 'Grenada');
INSERT INTO Country (code, name) VALUES ('GE', 'Georgia');
INSERT INTO Country (code, name) VALUES ('GF', 'French Guiana');
INSERT INTO Country (code, name) VALUES ('GG', 'Guernsey');
INSERT INTO Country (code, name) VALUES ('GH', 'Ghana');
INSERT INTO Country (code, name) VALUES ('GI', 'Gibraltar');
INSERT INTO Country (code, name) VALUES ('GL', 'Greenland');
INSERT INTO Country (code, name) VALUES ('GM', 'Gambia');
INSERT INTO Country (code, name) VALUES ('GN', 'Guinea');
INSERT INTO Country (code, name) VALUES ('GP', 'Guadeloupe');
INSERT INTO Country (code, name) VALUES ('GQ', 'Equatorial Guinea');
INSERT INTO Country (code, name) VALUES ('GR', 'Greece');
INSERT INTO Country (code, name) VALUES ('GS', 'South Georgia and the South Sandwich Islands');
INSERT INTO Country (code, name) VALUES ('GT', 'Guatemala');
INSERT INTO Country (code, name) VALUES ('GU', 'Guam');
INSERT INTO Country (code, name) VALUES ('GW', 'Guinea-Bissau');
INSERT INTO Country (code, name) VALUES ('GY', 'Guyana');
INSERT INTO Country (code, name) VALUES ('HK', 'Hong Kong');
INSERT INTO Country (code, name) VALUES ('HM', 'Heard Island and McDonald Islands');
INSERT INTO Country (code, name) VALUES ('HN', 'Honduras');
INSERT INTO Country (code, name) VALUES ('HR', 'Croatia');
INSERT INTO Country (code, name) VALUES ('HT', 'Haiti');
INSERT INTO Country (code, name) VALUES ('HU', 'Hungary');
INSERT INTO Country (code, name) VALUES ('ID', 'Indonesia');
INSERT INTO Country (code, name) VALUES ('IE', 'Ireland');
INSERT INTO Country (code, name) VALUES ('IL', 'Israel');
INSERT INTO Country (code, name) VALUES ('IM', 'Isle of Man');
INSERT INTO Country (code, name) VALUES ('IN', 'India');
INSERT INTO Country (code, name) VALUES ('IO', 'British Indian Ocean Territory');
INSERT INTO Country (code, name) VALUES ('IQ', 'Iraq');
INSERT INTO Country (code, name) VALUES ('IR', 'Iran (Islamic Republic of)');
INSERT INTO Country (code, name) VALUES ('IS', 'Iceland');
INSERT INTO Country (code, name) VALUES ('IT', 'Italy');
INSERT INTO Country (code, name) VALUES ('JE', 'Jersey');
INSERT INTO Country (code, name) VALUES ('JM', 'Jamaica');
INSERT INTO Country (code, name) VALUES ('JO', 'Jordan');
INSERT INTO Country (code, name) VALUES ('JP', 'Japan');
INSERT INTO Country (code, name) VALUES ('KE', 'Kenya');
INSERT INTO Country (code, name) VALUES ('KG', 'Kyrgyzstan');
INSERT INTO Country (code, name) VALUES ('KH', 'Cambodia');
INSERT INTO Country (code, name) VALUES ('KI', 'Kiribati');
INSERT INTO Country (code, name) VALUES ('KM', 'Comoros');
INSERT INTO Country (code, name) VALUES ('KN', 'Saint Kitts and Nevis');
INSERT INTO Country (code, name) VALUES ('KP', 'Korea (Democratic People''s Republic of)');
INSERT INTO Country (code, name) VALUES ('KR', 'Korea, Republic of');
INSERT INTO Country (code, name) VALUES ('KW', 'Kuwait');
INSERT INTO Country (code, name) VALUES ('KY', 'Cayman Islands');
INSERT INTO Country (code, name) VALUES ('KZ', 'Kazakhstan');
INSERT INTO Country (code, name) VALUES ('LA', 'Lao People''s Democratic Republic');
INSERT INTO Country (code, name) VALUES ('LB', 'Lebanon');
INSERT INTO Country (code, name) VALUES ('LC', 'Saint Lucia');
INSERT INTO Country (code, name) VALUES ('LI', 'Liechtenstein');
INSERT INTO Country (code, name) VALUES ('LK', 'Sri Lanka');
INSERT INTO Country (code, name) VALUES ('LR', 'Liberia');
INSERT INTO Country (code, name) VALUES ('LS', 'Lesotho');
INSERT INTO Country (code, name) VALUES ('LT', 'Lithuania');
INSERT INTO Country (code, name) VALUES ('LU', 'Luxembourg');
INSERT INTO Country (code, name) VALUES ('LV', 'Latvia');
INSERT INTO Country (code, name) VALUES ('LY', 'Libya');
INSERT INTO Country (code, name) VALUES ('MA', 'Morocco');
INSERT INTO Country (code, name) VALUES ('MC', 'Monaco');
INSERT INTO Country (code, name) VALUES ('MD', 'Moldova, Republic of');
INSERT INTO Country (code, name) VALUES ('ME', 'Montenegro');
INSERT INTO Country (code, name) VALUES ('MF', 'Saint Martin (French part)');
INSERT INTO Country (code, name) VALUES ('MG', 'Madagascar');
INSERT INTO Country (code, name) VALUES ('MH', 'Marshall Islands');
INSERT INTO Country (code, name) VALUES ('MK', 'North Macedonia');
INSERT INTO Country (code, name) VALUES ('ML', 'Mali');
INSERT INTO Country (code, name) VALUES ('MM', 'Myanmar');
INSERT INTO Country (code, name) VALUES ('MN', 'Mongolia');
INSERT INTO Country (code, name) VALUES ('MO', 'Macao');
INSERT INTO Country (code, name) VALUES ('MP', 'Northern Mariana Islands');
INSERT INTO Country (code, name) VALUES ('MQ', 'Martinique');
INSERT INTO Country (code, name) VALUES ('MR', 'Mauritania');
INSERT INTO Country (code, name) VALUES ('MS', 'Montserrat');
INSERT INTO Country (code, name) VALUES ('MT', 'Malta');
INSERT INTO Country (code, name) VALUES ('MU', 'Mauritius');
INSERT INTO Country (code, name) VALUES ('MV', 'Maldives');
INSERT INTO Country (code, name) VALUES ('MW', 'Malawi');
INSERT INTO Country (code, name) VALUES ('MX', 'Mexico');
INSERT INTO Country (code, name) VALUES ('MY', 'Malaysia');
INSERT INTO Country (code, name) VALUES ('MZ', 'Mozambique');
INSERT INTO Country (code, name) VALUES ('NA', 'Namibia');
INSERT INTO Country (code, name) VALUES ('NC', 'New Caledonia');
INSERT INTO Country (code, name) VALUES ('NE', 'Niger');
INSERT INTO Country (code, name) VALUES ('NF', 'Norfolk Island');
INSERT INTO Country (code, name) VALUES ('NG', 'Nigeria');
INSERT INTO Country (code, name) VALUES ('NI', 'Nicaragua');
INSERT INTO Country (code, name) VALUES ('NL', 'Netherlands');
INSERT INTO Country (code, name) VALUES ('NO', 'Norway');
INSERT INTO Country (code, name) VALUES ('NP', 'Nepal');
INSERT INTO Country (code, name) VALUES ('NR', 'Nauru');
INSERT INTO Country (code, name) VALUES ('NU', 'Niue');
INSERT INTO Country (code, name) VALUES ('NZ', 'New Zealand');
INSERT INTO Country (code, name) VALUES ('OM', 'Oman');
INSERT INTO Country (code, name) VALUES ('PA', 'Panama');
INSERT INTO Country (code, name) VALUES ('PE', 'Peru');
INSERT INTO Country (code, name) VALUES ('PF', 'French Polynesia');
INSERT INTO Country (code, name) VALUES ('PG', 'Papua New Guinea');
INSERT INTO Country (code, name) VALUES ('PH', 'Philippines');
INSERT INTO Country (code, name) VALUES ('PK', 'Pakistan');
INSERT INTO Country (code, name) VALUES ('PL', 'Poland');
INSERT INTO Country (code, name) VALUES ('PM', 'Saint Pierre and Miquelon');
INSERT INTO Country (code, name) VALUES ('PN', 'Pitcairn');
INSERT INTO Country (code, name) VALUES ('PR', 'Puerto Rico');
INSERT INTO Country (code, name) VALUES ('PS', 'Palestine, State of');
INSERT INTO Country (code, name) VALUES ('PT', 'Portugal');
INSERT INTO Country (code, name) VALUES ('PW', 'Palau');
INSERT INTO Country (code, name) VALUES ('PY', 'Paraguay');
INSERT INTO Country (code, name) VALUES ('QA', 'Qatar');
INSERT INTO Country (code, name) VALUES ('RE', 'Réunion');
INSERT INTO Country (code, name) VALUES ('RO', 'Romania');
INSERT INTO Country (code, name) VALUES ('RS', 'Serbia');
INSERT INTO Country (code, name) VALUES ('RU', 'Russian Federation');
INSERT INTO Country (code, name) VALUES ('RW', 'Rwanda');
INSERT INTO Country (code, name) VALUES ('SA', 'Saudi Arabia');
INSERT INTO Country (code, name) VALUES ('SB', 'Solomon Islands');
INSERT INTO Country (code, name) VALUES ('SC', 'Seychelles');
INSERT INTO Country (code, name) VALUES ('SD', 'Sudan');
INSERT INTO Country (code, name) VALUES ('SE', 'Sweden');
INSERT INTO Country (code, name) VALUES ('SG', 'Singapore');
INSERT INTO Country (code, name) VALUES ('SH', 'Saint Helena, Ascension and Tristan da Cunha');
INSERT INTO Country (code, name) VALUES ('SI', 'Slovenia');
INSERT INTO Country (code, name) VALUES ('SJ', 'Svalbard and Jan Mayen');
INSERT INTO Country (code, name) VALUES ('SK', 'Slovakia');
INSERT INTO Country (code, name) VALUES ('SL', 'Sierra Leone');
INSERT INTO Country (code, name) VALUES ('SM', 'San Marino');
INSERT INTO Country (code, name) VALUES ('SN', 'Senegal');
INSERT INTO Country (code, name) VALUES ('SO', 'Somalia');
INSERT INTO Country (code, name) VALUES ('SR', 'Suriname');
INSERT INTO Country (code, name) VALUES ('SS', 'South Sudan');
INSERT INTO Country (code, name) VALUES ('ST', 'Sao Tome and Principe');
INSERT INTO Country (code, name) VALUES ('SV', 'El Salvador');
INSERT INTO Country (code, name) VALUES ('SX', 'Sint Maarten (Dutch part)');
INSERT INTO Country (code, name) VALUES ('SY', 'Syrian Arab Republic');
INSERT INTO Country (code, name) VALUES ('SZ', 'Eswatini');
INSERT INTO Country (code, name) VALUES ('TC', 'Turks and Caicos Islands');
INSERT INTO Country (code, name) VALUES ('TD', 'Chad');
INSERT INTO Country (code, name) VALUES ('TF', 'French Southern Territories');
INSERT INTO Country (code, name) VALUES ('TG', 'Togo');
INSERT INTO Country (code, name) VALUES ('TH', 'Thailand');
INSERT INTO Country (code, name) VALUES ('TJ', 'Tajikistan');
INSERT INTO Country (code, name) VALUES ('TK', 'Tokelau');
INSERT INTO Country (code, name) VALUES ('TL', 'Timor-Leste');
INSERT INTO Country (code, name) VALUES ('TM', 'Turkmenistan');
INSERT INTO Country (code, name) VALUES ('TN', 'Tunisia');
INSERT INTO Country (code, name) VALUES ('TO', 'Tonga');
INSERT INTO Country (code, name) VALUES ('TR', 'Turkey');
INSERT INTO Country (code, name) VALUES ('TT', 'Trinidad and Tobago');
INSERT INTO Country (code, name) VALUES ('TV', 'Tuvalu');
INSERT INTO Country (code, name) VALUES ('TW', 'Taiwan, Province of China');
INSERT INTO Country (code, name) VALUES ('TZ', 'Tanzania, United Republic of');
INSERT INTO Country (code, name) VALUES ('UA', 'Ukraine');
INSERT INTO Country (code, name) VALUES ('UG', 'Uganda');
INSERT INTO Country (code, name) VALUES ('UM', 'United States Minor Outlying Islands');
INSERT INTO Country (code, name) VALUES ('US', 'United States of America');
INSERT INTO Country (code, name) VALUES ('UY', 'Uruguay');
INSERT INTO Country (code, name) VALUES ('UZ', 'Uzbekistan');
INSERT INTO Country (code, name) VALUES ('VA', 'Holy See');
INSERT INTO Country (code, name) VALUES ('VC', 'Saint Vincent and the Grenadines');
INSERT INTO Country (code, name) VALUES ('VE', 'Venezuela (Bolivarian Republic of)');
INSERT INTO Country (code, name) VALUES ('VG', 'Virgin Islands (British)');
INSERT INTO Country (code, name) VALUES ('VI', 'Virgin Islands (U.S.)');
INSERT INTO Country (code, name) VALUES ('VN', 'Viet Nam');
INSERT INTO Country (code, name) VALUES ('VU', 'Vanuatu');
INSERT INTO Country (code, name) VALUES ('WF', 'Wallis and Futuna');
INSERT INTO Country (code, name) VALUES ('WS', 'Samoa');
INSERT INTO Country (code, name) VALUES ('YE', 'Yemen');
INSERT INTO Country (code, name) VALUES ('YT', 'Mayotte');
INSERT INTO Country (code, name) VALUES ('ZA', 'South Africa');
INSERT INTO Country (code, name) VALUES ('ZM', 'Zambia');
INSERT INTO Country (code, name) VALUES ('ZW', 'Zimbabwe');

CREATE TABLE Entry (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, trip_id INTEGER NOT NULL REFERENCES Trip (id), date TEXT (10) NOT NULL, text VARCHAR);

CREATE TABLE Trip (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, name VARCHAR (50) NOT NULL, icon BLOB, start_date TEXT (10) NOT NULL, end_date TEXT (10) NOT NULL);

CREATE TABLE Trip_country (country_code CHAR (2) NOT NULL REFERENCES Country (code), trip_id INTEGER NOT NULL REFERENCES Trip (id), PRIMARY KEY (country_code, trip_id));

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
