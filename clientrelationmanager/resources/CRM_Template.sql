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
    First_Name VARCHAR (60) NOT NULL,
    Last_Name VARCHAR (60) NOT NULL,
    UserID INT NOT NULL,
    Username VARCHAR (60) NOT NULL,
    Interaction_Type VARCHAR(MAX) NOT NULL,
    Interaction_Date DATE NOT NULL,    
    PRIMARY KEY  (EventID),
    CONSTRAINT fk_clientID FOREIGN KEY (ClientID) REFERENCES Clients (ClientID),
    CONSTRAINT fk_clientFirst FOREIGN KEY (First_Name) REFERENCES Clients (First_Name),
    CONSTRAINT fk_clientLast FOREIGN KEY (Last_Name) REFERENCES Clients (Last_Name),
    CONSTRAINT fk_userID FOREIGN KEY (UserID) REFERENCES UsersDB.Users(UserID),
    CONSTRAINT fk_userName FOREIGN KEY (Username) REFERENCES UsersDB.Users(Username),
);

INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State) VALUES ('Larry','Fliss','Active','2411 Robinson Ave','Conway','AR');
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State) VALUES ('Yukiko','Sato','Prospective','12 Illinois Ave','Urbana','IL');
INSERT INTO Clients (First_Name,Last_Name,Status,Address,City,Home_State) VALUES ('Gerald','Washington','Former','123 Fake St','Austin','TX');

INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (2,'Yukiko','Sato',2,'Jax','Phone Call','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (1,'Larry','Fliss',3,'Jackie','Changed to Active status','2017-01-10');
INSERT INTO Interactions (ClientID,First_Name,Last_Name,UserID,Username,Interaction_Type,Interaction_Date) VALUES (3,'Gerald','Washington',3,'Jackie','Changed to Former status','2017-01-10');

