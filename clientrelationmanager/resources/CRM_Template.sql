/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Carter
 * Created: Jan 4, 2017
 */
/**
*  Initial creation of tables
*/
DROP DATABASE IF EXISTS ClientDB;
CREATE DATABASE IF NOT EXISTS ClientDB;

USE ClientDB;

CREATE TABLE IF NOT EXISTS Clients
(
    ClientID INT NOT NULL AUTO_INCREMENT,
    First_Name VARCHAR (60) NOT NULL,    
    Last_Name VARCHAR (60) NOT NULL,
    Status VARCHAR (120) NOT NULL,
    Address VARCHAR (120) NOT NULL,
    City VARCHAR (60) NOT NULL,
    Home_State VARCHAR (2) NOT NULL,
    ZIP INT NOT NULL,
    Phone VARCHAR (12),
    Email VARCHAR (120),
    PRIMARY KEY (ClientID),
    INDEX (ClientID, First_Name, Last_Name)
);

CREATE TABLE IF NOT EXISTS Users (
    UserID INT NOT NULL AUTO_INCREMENT,
    Username VARCHAR (120) NOT NULL,
    Password VARCHAR (120) NOT NULL,
    UserRole VARCHAR (20) NOT NULL,
    User_Status TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (UserID)
);

CREATE TABLE IF NOT EXISTS Interactions
(
    EventID INT NOT NULL AUTO_INCREMENT,
    ClientID INT,
    First_Name VARCHAR (60) NOT NULL,
    Last_Name VARCHAR (60) NOT NULL,
    UserID INT,
    Username VARCHAR (60) NOT NULL,
    Interaction_Type TEXT NOT NULL,
    Interaction_Date VARCHAR (30) NOT NULL,    
    PRIMARY KEY  (EventID),
    CONSTRAINT fk_clientID FOREIGN KEY (ClientID) REFERENCES Clients (ClientID) ON delete cascade on update cascade,    
    CONSTRAINT fk_userID FOREIGN KEY (UserID) REFERENCES Users (UserID) on delete cascade on update cascade,
    INDEX (EventID,ClientID,UserID)
);


INSERT INTO Users (Username, Password, UserRole, User_Status) VALUES ('Carter','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_ADMIN',1);
INSERT INTO Users (Username, Password, UserRole, User_Status) VALUES ('Jax','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users (Username, Password, UserRole, User_Status) VALUES ('Jackie','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_MANAGER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Erin','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_MANAGER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Stephanie','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Dustin','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Wesley','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_MANAGER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Robert','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Tabatha','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Bruce','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Dave','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Leigh','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('David','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_MANAGER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Chris','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);
INSERT INTO Users(Username, Password, UserRole, User_Status) VALUES('Bradley','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',1);




INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Larry','Fliss','Active','2411 Robinson Ave','Conway','AR',72034);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Yukiko','Sato','Prospective','12 Illinois Ave','Urbana','IL',12345);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Gerald','Washington','Former','123 Fake St','Austin','TX',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Debbie','Goggans','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Arnold','Ripka','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Donald','McNeil','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Pamela','Webb','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Jennifer','Neil','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Jessica','Easton','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Karen','Webb','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Catherine','Higginbotham','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Anthony','Griffin','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Benjamin','Wray','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Letha','Phelps','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Ellen','Person','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Alejandro','Peterson','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Michelle','Smith','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Karen','Norton','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Jennifer','Teel','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Michael','Petrick','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Lovie','Pyle','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Yvonne','Robertson','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Wilma','Skinner','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Debbie','Griffis','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Ray','Snavely','Active','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Brian','Moore','Prospective','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('David','Turner','Former','123 Fake St','Little Rock','AR',54321);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Daniel','Anderson','Active','123 Fake St','Little Rock','AR',54321);

INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (2,'Yukiko','Sato',2,'Jax','Phone Call','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (1,'Larry','Fliss',3,'Jackie','Changed to Active status','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (3,'Gerald','Washington',3,'Jackie','Changed to Former status','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (5,'Arnold','Ripka',11,'Dave','Lorem ipsum dolor sit amet','2017-03-04');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (13,'Benjamin','Wray',4,'Erin','Praesent eu velit eu felis consequat vehicula et at orci.','2017-03-09');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (6,'Donald','McNeil',9,'Tabatha','Interdum et malesuada fames ac ante ipsum primis in faucibus.','2017-03-09');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (20,'Michael','Petrick',4,'Erin','Curabitur scelerisque, tellus at rhoncus rutrum, augue arcu interdum magna, lobortis mollis nunc felis sed leo. Nullam et arcu mauris.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (3,'Gerald','Washington',7,'Wesley','Mauris sit amet ullamcorper risus, et sollicitudin nunc.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (3,'Gerald','Washington',13,'David','Proin quam leo, ultrices id sapien aliquam, consequat vulputate ante.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (15,'Ellen','Person',15,'Bradley','Morbi id tellus fermentum, facilisis tortor vel, sodales dui.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (20,'Michael','Petrick',9,'Tabatha','Fusce iaculis bibendum risus in feugiat.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (7,'Pamela','Webb',14,'Chris','Fusce lacinia mauris lacus, et finibus neque cursus a.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (14,'Letha','Phelps',8,'Robert','Aenean massa dui, euismod nec neque id, dictum suscipit nulla. Integer in nibh tincidunt, cursus purus non, molestie diam.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (10,'Karen','Webb',14,'Chris','Praesent interdum lorem eu elit luctus fringilla.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (1,'Larry','Fliss',2,'Jax','Phasellus nisl sem, lobortis a venenatis at, iaculis ut felis.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (9,'Jessica','Easton',8,'Robert','Maecenas dictum enim at massa elementum lacinia.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (12,'Anthony','Griffin',5,'Stephanie','Sed nec diam sapien.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (11,'Catherine','Higginbotham',15,'Bradley','Cras eleifend, tortor vel finibus fringilla, lectus nunc ultricies risus, sit amet venenatis orci lectus sed odio.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (3,'Gerald','Washington',13,'David','Cras pellentesque vulputate ante, in tempor nulla ultricies ut.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (4,'Debbie','Goggans',12,'Leigh','Aenean id eros at enim iaculis bibendum eget id mauris. Cras in auctor felis, ac mollis magna.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (4,'Debbie','Goggans',3,'Jackie','Cras vel augue feugiat, sagittis erat vitae, condimentum purus.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (14,'Letha','Phelps',11,'Dave','Integer sollicitudin nulla in ex faucibus posuere.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (15,'Ellen','Person',10,'Bruce','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (17,'Michelle','Smith',14,'Chris','Suspendisse varius neque quam, vitae sagittis velit mattis eget.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (11,'Catherine','Higginbotham',8,'Robert','Etiam at blandit odio.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (18,'Karen','Norton',5,'Stephanie','Sed at luctus nulla, tristique auctor nisi.','2017-03-15');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (5,'Arnold','Ripka',11,'Dave','Duis varius molestie varius.','2017-03-15');
