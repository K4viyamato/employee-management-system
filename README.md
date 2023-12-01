

# Employee Management System
This Employee Management System is a simple Java application built using MySQL as the database to manage employee-related information within departments and designations.

##Pre-requisites##

Database: MySQL
Java Version: 1.8.0_202

##Development Environment##

Java FX Version: Included in the Java 1.8 distribution
Scene Builder Version: 2.0
IDE: NetBeans 8


##Project Structure##

This project was developed using Java FX in conjunction with Scene Builder 2.0, and NetBeans 8 served as the Integrated Development Environment (IDE). The application is structured to manage employee-related information within departments and designations.


##Database Configuration##

Username: root
Password: 1234


##Tables##

###Admin Table###


CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('Admin', 'User') NOT NULL DEFAULT 'User'
);

INSERT INTO admin (username, password, role) VALUES
('admin', '1234', 'Admin');


###Department Table###


CREATE TABLE Department (
    DepartmentID INT PRIMARY KEY AUTO_INCREMENT,
    DepartmentName VARCHAR(50) NOT NULL
);

INSERT INTO Department (DepartmentName) VALUES
('Human Resources'),
('Marketing'),
('Finance'),
('Information Technology'),
('Operations');


###Designation Table###

CREATE TABLE Designation (
    DesignationID INT PRIMARY KEY AUTO_INCREMENT,
    DesignationName VARCHAR(50) NOT NULL
);

INSERT INTO Designation (DesignationName) VALUES
('Manager'),
('Assistant Manager'),
('Senior Analyst'),
('Developer'),
('Coordinator');


###Employee Table###

CREATE TABLE Employee (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT UNIQUE,
    Name VARCHAR(100) NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    DepartmentID INT,
    DesignationID INT,
    Salary DECIMAL(10, 2),
    PhoneNumber VARCHAR(20),
    EPFNumber VARCHAR(100),
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID),
    FOREIGN KEY (DesignationID) REFERENCES Designation(DesignationID)
);


##Screenshots

Login
![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/a46c0f8e-525e-40c6-8dc5-b09a3b1020bb)

Home
![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/3ed52d9b-bc80-42b6-b0b9-1b3d5ba7e829)

Manage Employees
![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/52e8c78f-18cd-4e67-8e41-d0c6f534e98b)

Manage Users
![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/3a55d2af-a382-46f0-9362-a25bfe872577)

Manage Department And Designation
![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/5106bf6e-ebd8-4ea8-9f56-3e69d912ef3c)


**NOTE**
This system does not include password hashing. It's a basic Employee Management System that allows for managing employees' information within departments and designations.

Feel free to use and modify this system based on your requirements.




