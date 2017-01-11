/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Carter
 * Created: Jan 10, 2017
 */
CREATE DATABASE UsersDB;

CREATE TABLE Users (
    UserID INT NOT NULL AUTO_INCREMENT,
    Username VARCHAR (120) NOT NULL,
    Password VARCHAR (120) NOT NULL,
    User_Status TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (UserID)
);

CREATE TABLE Roles (
    UserRoleID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    Username VARCHAR (120) NOT NULL,
    UserRole VARCHAR (10) NOT NULL,
    PRIMARY KEY (UserRoleID),
    UNIQUE KEY uni_UserRole (UserRole, Username),
    CONSTRAINT fk_UserID FOREIGN KEY (UserID) REFERENCES Users (UserID),
    CONSTRAINT fk_Username FOREIGN KEY (Username) REFERENCES Users (Username),
);

INSERT INTO Users (Username, Password, User_Status) VALUES ('Carter','f58cf5e7e10f195e21b553096d092c763ed18b0e',true);
INSERT INTO Users (Username, Password, User_Status) VALUES ('Jax','f58cf5e7e10f195e21b553096d092c763ed18b0e',true);
INSERT INTO Users (Username, Password, User_Status) VALUES ('Jackie','f58cf5e7e10f195e21b553096d092c763ed18b0e',true);

INSERT INTO Roles (Username, UserRole) VALUES ('Carter','ADMIN');
INSERT INTO Roles (Username, UserRole) VALUES ('Jax','USER');
INSERT INTO Roles (Username, UserRole) VALUES ('Jackie','MANAGER');