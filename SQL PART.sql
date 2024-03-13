create database dbmsproject;
use dbmsproject;

CREATE TABLE Passenger (
   Passengerid INT AUTO_INCREMENT PRIMARY KEY,
   Firstname VARCHAR(50),
   Lastname VARCHAR(50),
   Email VARCHAR(100),
   Phone VARCHAR(15),
   Address TEXT
);
select * from Passenger;

CREATE TABLE Train (
Trainid INT AUTO_INCREMENT PRIMARY KEY,
Trainname varchar(100),
Sourcestation varchar(50),
Destinationstation varchar(50),
Departuretime time,
Arrivaltime time,
Totalseats int
);
select * from Train;

CREATE TABLE reservation (
   Reservationid INT AUTO_INCREMENT PRIMARY KEY,
   Passengerid INT,
   Trainid INT,
   Dateofreservation DATE,
   Status VARCHAR(20),
   Totalfare DECIMAL(10,2),
   FOREIGN KEY (Passengerid) REFERENCES Passenger(Passengerid),
   FOREIGN KEY (Trainid) REFERENCES Train(Trainid)
);
select * from reservation;

CREATE TABLE Reservationdetail (
   Reservationdetailid INT AUTO_INCREMENT PRIMARY KEY,
   Reservationid INT,
   Seatnumber VARCHAR(10),
   Coachnumber VARCHAR(10),
   FOREIGN KEY (Reservationid) REFERENCES reservation(Reservationid)
);
select* from Reservationdetail;