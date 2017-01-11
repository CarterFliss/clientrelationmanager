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
CREATE TABLE Clients
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
    PRIMARY KEY (ClientID)
);

CREATE TABLE Interactions
(
    EventID INT NOT NULL AUTO_INCREMENT,
    ClientID INT NOT NULL,    
    UserID INT NOT NULL,
    Interaction_Type VARCHAR(MAX) NOT NULL,
    Interaction_Date DATE NOT NULL,    
    PRIMARY KEY  (EventID),
    CONSTRAINT fk_clientID FOREIGN KEY (ClientID) REFERENCES Clients (ClientID),
    CONSTRAINT fk_userID FOREIGN KEY (UserID) REFERENCES Users.Users(UserID)
);

