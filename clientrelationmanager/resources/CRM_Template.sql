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


INSERT INTO Users (Username, Password, UserRole, User_Status) VALUES ('Carter','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_ADMIN',true);
INSERT INTO Users (Username, Password, UserRole, User_Status) VALUES ('Jax','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_USER',true);
INSERT INTO Users (Username, Password, UserRole, User_Status) VALUES ('Jackie','f58cf5e7e10f195e21b553096d092c763ed18b0e','ROLE_MANAGER',true);



INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Larry','Fliss','Active','2411 Robinson Ave','Conway','AR',72034);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Yukiko','Sato','Prospective','12 Illinois Ave','Urbana','IL',12345);
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State,ZIP) VALUES ('Gerald','Washington','Former','123 Fake St','Austin','TX',54321);

INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (2,'Yukiko','Sato',2,'Jax','Phone Call','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (1,'Larry','Fliss',3,'Jackie','Changed to Active status','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (3,'Gerald','Washington',3,'Jackie','Changed to Former status','2017-01-10');

