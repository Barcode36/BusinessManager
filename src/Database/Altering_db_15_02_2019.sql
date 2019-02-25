CREATE TABLE MaterialPropertyTypes (
PropertyTypeID int(11) NOT NULL AUTO_INCREMENT,
PropertyTypeName varchar(100) NOT NULL,
PRIMARY KEY (PropertyTypeID)
);


CREATE TABLE CommonMaterialProperties(
PropertyID int(11) NOT NULL AUTO_INCREMENT, 
PropertyTypeID int(11) NOT NULL, 
PropertyName varchar(255) NOT NULL, 
PRIMARY KEY(PropertyID),
FOREIGN KEY(PropertyTypeID) REFERENCES MaterialPropertyTypes(PropertyTypeID)
);

CREATE TABLE CustomerPropertyTypes (
PropertyTypeID int(11) NOT NULL AUTO_INCREMENT,
PropertyTypeName varchar(100) NOT NULL,
PRIMARY KEY (PropertyTypeID)
);

CREATE TABLE CommonCustomerProperties(
PropertyID int(11) NOT NULL AUTO_INCREMENT, 
PropertyTypeID int(11) NOT NULL, 
PropertyName varchar(255) NOT NULL, 
PRIMARY KEY(PropertyID),
FOREIGN KEY(PropertyTypeID) REFERENCES CustomerPropertyTypes(PropertyTypeID)
);

INSERT INTO MaterialPropertyTypes
VALUES
(null, 'Type'),
(null, 'Color'),
(null, 'Manufacturer'),
(null, 'Seller'),
(null, 'Diameter'),
(null, 'Weight'),
(null, 'Printer Type');

INSERT INTO CommonMaterialProperties
VALUES
(null, 2, 'Lime Green'),
(null, 2, 'Black'),
(null, 2, 'White'),
(null, 2, 'Luminious Red'),
(null, 2, 'Luminious Orange'),
(null, 2, 'Red'),
(null, 2, 'Blue'),
(null, 2, 'Orange'),
(null, 2, 'Gold'),
(null, 2, 'Silver'),
(null, 2, 'Navy Blue'),
(null, 2, 'Green'),
(null, 2, 'Raspberry Red'),
(null, 2, 'Hot Red'),
(null, 2, 'Everybody''s magenta'),
(null, 2, 'Melon Yellow'),
(null, 2, 'Transparent Blue'),
(null, 2, 'Brown'),
(null, 2, 'Silk Blue'),
(null, 2, 'Sky Blue'),
-- 21
(null, 1, 'PLA'),
-- 22
(null, 1, 'ABS'),
-- 23
(null, 1, 'PETG'),
-- 24
(null, 1, 'TPU'),
(null, 1, 'Nylon'),
(null, 1, 'PC'),
(null, 1, 'PC/ABS'),
(null, 1, 'HIPS'),
(null, 1, 'PVA'),
(null, 1, 'ASA'),
(null, 1, 'POM'),
(null, 1, 'Wood'),
(null, 1, 'Metal'),
(null, 1, 'PMMA'),
-- 35
(null, 3, 'Mladec'),
-- 36
(null, 3, 'Fillamentum'),
-- 37
(null, 3, 'Devil Design'),
-- 38
(null, 3, 'Jinbo Hao'),
-- 39
(null, 3, 'YASIN Filamnets'),
-- 40
(null, 4, '3DeS s.r.o.'),
-- 41
(null, 4, 'Kreatiff s.r.o.'),
-- 42
(null, 4, 'Prusa Research'),
-- 43
(null, 4, 'EMG Trade s.r.o.'),
-- 44
(null, 4, 'WilTec s.r.o.'),
-- 45
(null, 5, '1.75'),
-- 46
(null, 5, '3.00'),
-- 47
(null, 6, '500'),
-- 48
(null, 6, '750'),
-- 49
(null, 6, '1000'),
-- 50
(null, 6, '2000'),
-- 51
(null, 7, 'FDM'),
-- 52
(null, 7, 'SLA');

INSERT INTO CustomerPropertyTypes
VALUES
(null, 'Company'),
(null, 'Country');

INSERT INTO CommonCustomerProperties
VALUES
(null,2,'Afghanistan'),
(null,2,'Albania'),
(null,2,'Algeria'),
(null,2,'American Samoa'),
(null,2,'Andorra'),
(null,2,'Angola'),
(null,2,'Anguilla'),
(null,2,'Antarctica'),
(null,2,'Antigua and Barbuda'),
(null,2,'Argentina'),
(null,2,'Armenia'),
(null,2,'Aruba'),
(null,2,'Australia'),
(null,2,'Austria'),
(null,2,'Azerbaijan'),
(null,2,'Bahamas'),
(null,2,'Bahrain'),
(null,2,'Bangladesh'),
(null,2,'Barbados'),
(null,2,'Belarus'),
(null,2,'Belgium'),
(null,2,'Belize'),
(null,2,'Benin'),
(null,2,'Bermuda'),
(null,2,'Bhutan'),
(null,2,'Bolivia'),
(null,2,'Bosnia and Herzegovina'),
(null,2,'Botswana'),
(null,2,'Bouvet Island'),
(null,2,'Brazil'),
(null,2,'British Indian Ocean Territory'),
(null,2,'Brunei Darussalam'),
(null,2,'Bulgaria'),
(null,2,'Burkina Faso'),
(null,2,'Burundi'),
(null,2,'Cambodia'),
(null,2,'Cameroon'),
(null,2,'Canada'),
(null,2,'Cape Verde'),
(null,2,'Cayman Islands'),
(null,2,'Central African Republic'),
(null,2,'Chad'),
(null,2,'Chile'),
(null,2,'China'),
(null,2,'Christmas Island'),
(null,2,'Cocos (Keeling), Islands'),
(null,2,'Colombia'),
(null,2,'Comoros'),
(null,2,'Congo'),
(null,2,'Cook Islands'),
(null,2,'Costa Rica'),
(null,2,'Croatia (Hrvatska),'),
(null,2,'Cuba'),
(null,2,'Cyprus'),
(null,2,'Czech Republic'),
(null,2,'Denmark'),
(null,2,'Djibouti'),
(null,2,'Dominica'),
(null,2,'Dominican Republic'),
(null,2,'East Timor'),
(null,2,'Ecuador'),
(null,2,'Egypt'),
(null,2,'El Salvador'),
(null,2,'Equatorial Guinea'),
(null,2,'Eritrea'),
(null,2,'Estonia'),
(null,2,'Ethiopia'),
(null,2,'Falkland Islands (Malvinas),'),
(null,2,'Faroe Islands'),
(null,2,'Fiji'),
(null,2,'Finland'),
(null,2,'France'),
(null,2,'France, Metropolitan'),
(null,2,'French Guiana'),
(null,2,'French Polynesia'),
(null,2,'French Southern Territories'),
(null,2,'Gabon'),
(null,2,'Gambia'),
(null,2,'Georgia'),
(null,2,'Germany'),
(null,2,'Ghana'),
(null,2,'Gibraltar'),
(null,2,'Guernsey'),
(null,2,'Greece'),
(null,2,'Greenland'),
(null,2,'Grenada'),
(null,2,'Guadeloupe'),
(null,2,'Guam'),
(null,2,'Guatemala'),
(null,2,'Guinea'),
(null,2,'Guinea-Bissau'),
(null,2,'Guyana'),
(null,2,'Haiti'),
(null,2,'Heard and Mc Donald Islands'),
(null,2,'Honduras'),
(null,2,'Hong Kong'),
(null,2,'Hungary'),
(null,2,'Iceland'),
(null,2,'India'),
(null,2,'Isle of Man'),
(null,2,'Indonesia'),
(null,2,'Iran (Islamic Republic of),'),
(null,2,'Iraq'),
(null,2,'Ireland'),
(null,2,'Israel'),
(null,2,'Italy'),
(null,2,'Ivory Coast'),
(null,2,'Jersey'),
(null,2,'Jamaica'),
(null,2,'Japan'),
(null,2,'Jordan'),
(null,2,'Kazakhstan'),
(null,2,'Kenya'),
(null,2,'Kiribati'),
(null,2,'Korea, Democratic People\'s Republic of'),
(null,2,'Korea, Republic of'),
(null,2,'Kosovo'),
(null,2,'Kuwait'),
(null,2,'Kyrgyzstan'),
(null,2,'Lao People\'s Democratic Republic'),
(null,2,'Latvia'),
(null,2,'Lebanon'),
(null,2,'Lesotho'),
(null,2,'Liberia'),
(null,2,'Libyan Arab Jamahiriya'),
(null,2,'Liechtenstein'),
(null,2,'Lithuania'),
(null,2,'Luxembourg'),
(null,2,'Macau'),
(null,2,'Macedonia'),
(null,2,'Madagascar'),
(null,2,'Malawi'),
(null,2,'Malaysia'),
(null,2,'Maldives'),
(null,2,'Mali'),
(null,2,'Malta'),
(null,2,'Marshall Islands'),
(null,2,'Martinique'),
(null,2,'Mauritania'),
(null,2,'Mauritius'),
(null,2,'Mayotte'),
(null,2,'Mexico'),
(null,2,'Micronesia, Federated States of'),
(null,2,'Moldova, Republic of'),
(null,2,'Monaco'),
(null,2,'Mongolia'),
(null,2,'Montenegro'),
(null,2,'Montserrat'),
(null,2,'Morocco'),
(null,2,'Mozambique'),
(null,2,'Myanmar'),
(null,2,'Namibia'),
(null,2,'Nauru'),
(null,2,'Nepal'),
(null,2,'Netherlands'),
(null,2,'Netherlands Antilles'),
(null,2,'New Caledonia'),
(null,2,'New Zealand'),
(null,2,'Nicaragua'),
(null,2,'Niger'),
(null,2,'Nigeria'),
(null,2,'Niue'),
(null,2,'Norfolk Island'),
(null,2,'Northern Mariana Islands'),
(null,2,'Norway'),
(null,2,'Oman'),
(null,2,'Pakistan'),
(null,2,'Palau'),
(null,2,'Palestine'),
(null,2,'Panama'),
(null,2,'Papua New Guinea'),
(null,2,'Paraguay'),
(null,2,'Peru'),
(null,2,'Philippines'),
(null,2,'Pitcairn'),
(null,2,'Poland'),
(null,2,'Portugal'),
(null,2,'Puerto Rico'),
(null,2,'Qatar'),
(null,2,'Reunion'),
(null,2,'Romania'),
(null,2,'Russian Federation'),
(null,2,'Rwanda'),
(null,2,'Saint Kitts and Nevis'),
(null,2,'Saint Lucia'),
(null,2,'Saint Vincent and the Grenadines'),
(null,2,'Samoa'),
(null,2,'San Marino'),
(null,2,'Sao Tome and Principe'),
(null,2,'Saudi Arabia'),
(null,2,'Senegal'),
(null,2,'Serbia'),
(null,2,'Seychelles'),
(null,2,'Sierra Leone'),
(null,2,'Singapore'),
(null,2,'Slovakia'),
(null,2,'Slovenia'),
(null,2,'Solomon Islands'),
(null,2,'Somalia'),
(null,2,'South Africa'),
(null,2,'South Georgia South Sandwich Islands'),
(null,2,'Spain'),
(null,2,'Sri Lanka'),
(null,2,'St. Helena'),
(null,2,'St. Pierre and Miquelon'),
(null,2,'Sudan'),
(null,2,'Suriname'),
(null,2,'Svalbard and Jan Mayen Islands'),
(null,2,'Swaziland'),
(null,2,'Sweden'),
(null,2,'Switzerland'),
(null,2,'Syrian Arab Republic'),
(null,2,'Taiwan'),
(null,2,'Tajikistan'),
(null,2,'Tanzania, United Republic of'),
(null,2,'Thailand'),
(null,2,'Togo'),
(null,2,'Tokelau'),
(null,2,'Tonga'),
(null,2,'Trinidad and Tobago'),
(null,2,'Tunisia'),
(null,2,'Turkey'),
(null,2,'Turkmenistan'),
(null,2,'Turks and Caicos Islands'),
(null,2,'Tuvalu'),
(null,2,'Uganda'),
(null,2,'Ukraine'),
(null,2,'United Arab Emirates'),
(null,2,'United Kingdom'),
(null,2,'United States'),
(null,2,'United States minor outlying islands'),
(null,2,'Uruguay'),
(null,2,'Uzbekistan'),
(null,2,'Vanuatu'),
(null,2,'Vatican City State'),
(null,2,'Venezuela'),
(null,2,'Vietnam'),
(null,2,'Virgin Islands (British),'),
(null,2,'Virgin Islands (U.S.),'),
(null,2,'Wallis and Futuna Islands'),
(null,2,'Western Sahara'),
(null,2,'Yemen'),
(null,2,'Zaire'),
(null,2,'Zambia'),
(null,2,'Zimbabwe'),
-- 246
(null, 1, 'None'),
-- 247
(null, 1, 'T-Systems Slovakia s. r. o.');


ALTER TABLE Materials DROP FOREIGN KEY Materials_ibfk_1;
ALTER TABLE Materials DROP FOREIGN KEY Materials_ibfk_2;
ALTER TABLE Materials DROP FOREIGN KEY Materials_ibfk_3;
ALTER TABLE Materials DROP KEY DiameterID;
ALTER TABLE Materials DROP KEY MaterialTypeID;
ALTER TABLE Materials DROP KEY WeightID;
ALTER TABLE Materials ADD FOREIGN KEY (ColorID) REFERENCES CommonMaterialProperties(PropertyID);
ALTER TABLE Materials ADD FOREIGN KEY (ManufacturerID) REFERENCES CommonMaterialProperties(PropertyID);
ALTER TABLE Materials ADD FOREIGN KEY (SellerID) REFERENCES CommonMaterialProperties(PropertyID);
ALTER TABLE Materials ADD FOREIGN KEY (WeightID) REFERENCES CommonMaterialProperties(PropertyID);
ALTER TABLE Materials ADD FOREIGN KEY (DiameterID) REFERENCES CommonMaterialProperties(PropertyID);
ALTER TABLE Materials ADD FOREIGN KEY (MaterialTypeID) REFERENCES CommonMaterialProperties(PropertyID);

ALTER TABLE Printers DROP FOREIGN KEY Printers_ibfk_1;
ALTER TABLE Printers DROP KEY PrinterTypeID;
ALTER TABLE Printers ADD FOREIGN KEY (PrinterID) REFERENCES CommonMaterialProperties(PropertyID);


ALTER TABLE Customers DROP FOREIGN KEY Customers_ibfk_1;
ALTER TABLE Customers DROP FOREIGN KEY Customers_ibfk_2;
ALTER TABLE Customers DROP KEY CountryID;
ALTER TABLE Customers DROP KEY CompanyID;
ALTER TABLE Customers ADD FOREIGN KEY (CountryID) REFERENCES CommonCustomerProperties(PropertyID);
ALTER TABLE Customers ADD FOREIGN KEY (CompanyID) REFERENCES CommonCustomerProperties(PropertyID);

-- set Pritners
/*
*
*
*/
UPDATE Printers SET PrinterTypeID=51 WHERE PrinterID=1;
UPDATE Printers SET PrinterTypeID=52 WHERE PrinterID=2;

-- set Material Types
/*
*
*
*/
-- set PLA
UPDATE Materials SET MaterialTypeID=21 WHERE MaterialID IN (1,15,24,19,11,8,37,2,6,30,3,23,22,7,9,4,5,10,12,13,14,32,16,17,18,20,21,28,27,26,29,31,36,33,34,35);
-- set ABS
UPDATE Materials SET MaterialTypeID=22 WHERE MaterialID IN (8,33,35);
-- set PETG
UPDATE Materials SET MaterialTypeID=23 WHERE MaterialID IN (31,36);
-- set TPU
UPDATE Materials SET MaterialTypeID=23 WHERE MaterialID IN (37);

-- set Material manufacturers
/*
*
*
*/
-- set Mladec
UPDATE Materials SET ManufacturerID=35 WHERE MaterialID IN (1,2,3,6,7,8,9,10,11,12,13,14,16,19,22);
-- set Fillamentum
UPDATE Materials SET ManufacturerID=36 WHERE MaterialID IN (4,5,25,26,27,28,29);
-- Devil Design
UPDATE Materials SET ManufacturerID=37 WHERE MaterialID IN (15,17,18,20,21,23,24,37);
-- Jinbo Hao
UPDATE Materials SET ManufacturerID=38 WHERE MaterialID IN (30,32,33,34,35);
-- YASIN Filamnets
UPDATE Materials SET ManufacturerID=39 WHERE MaterialID IN (31,36);

-- set Material sellers
/*
*
*
*/
-- set 3DeS s.r.o.
UPDATE Materials SET SellerID=40 WHERE MaterialID IN (2,3,4,5,6,7,8,9,10,11,12,13,25,26,27,28,29);
-- set Kreatiff s.r.o.
UPDATE Materials SET SellerID=41 WHERE MaterialID IN (14,15,16,17,18,19,20,21,22,23,24);
-- set Prusa Research
UPDATE Materials SET SellerID=42 WHERE MaterialID IN (1);
-- set EMG Trade s.r.o.
UPDATE Materials SET SellerID=43 WHERE MaterialID IN (30,31,32,33,34,35,36);
-- set WilTec s.r.o
UPDATE Materials SET SellerID=44 WHERE MaterialID IN (37);

-- set Material diameters
/*
*
*
*/
-- set 1.75
UPDATE Materials SET DiameterID=45;

-- set Material weights
/*
*
*
*/
-- set 1000
UPDATE Materials SET WeightID=49;
-- set 750
UPDATE Materials SET WeightID=47 WHERE MaterialID IN (4,5,25,26,27,28,29);
-- set 2000
UPDATE Materials SET WeightID=50 WHERE MaterialID IN (23,24);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

-- set Customer Companies
/*
*
*
*/
-- set None
UPDATE Customers SET CompanyID=246;
-- set T-Systems
UPDATE Customers SET CompanyID=247 WHERE CustomerID IN (5003,5005,5007,5010,5014,5015,5016,5017,5018,5023,5024,5025,5032,5045,5048,5049,5050,5051,5056,5059);


-- drop unnecessary tables
DROP TABLE IF EXISTS MaterialColors,MaterialDiameters,MaterialManufacturers,MaterialSellers,MaterialTypes,MaterialWeights,Companies,Countries;

