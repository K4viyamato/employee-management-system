

# Employee Management System
- This project was developed for educational purposes to demonstrate JavaFX, MySQL integration, and basic CRUD (Create, Read, Update, Delete) functionalities in an Employee Management System.


## Technology Stack
- **Programming Language:** Java
- **GUI Framework:** JavaFX
- **Database:** MySQL
- **IDE:** NetBeans 8.0
- **Other Tools:** Scene Builder 2.0


## Features
- **User Authentication:** Secure login for administrators with different roles (Admin/User).
- **Employee Management:** Add, update, and delete employee details including name, gender, department, designation, salary, contact information, and more.
- **Department and Designation Management:** Maintain a database of departments and designations to assign to employees.
- **Database Integration:** Utilizes MySQL for database management, allowing efficient storage and retrieval of employee-related information.
- **JavaFX Interface:** Developed using JavaFX and Scene Builder for a responsive and intuitive user interface.


## Installation and Setup
1. Clone the repository to your local machine.
2. Open the project in NetBeans IDE.
3. Configure the MySQL database settings in the application for proper functionality.
4. Run the application to start managing employee records efficiently.


## Database Configuration

- Username: root
* Password: 1234


## Tables & Queries

### Admin Table

- **Creating the admin Table**
  
`CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('Admin', 'User') NOT NULL DEFAULT 'User'
);`

- **Inserting admin User**

`INSERT INTO admin (username, password, role) VALUES
('admin', '1234', 'Admin');`





### Department Table

- **Creating the Department Table**

`CREATE TABLE Department (
    DepartmentID INT PRIMARY KEY AUTO_INCREMENT,
    DepartmentName VARCHAR(50) NOT NULL
);`

- **Inserting Example Data**

`INSERT INTO Department (DepartmentName) VALUES
('Human Resources'),
('Marketing'),
('Finance'),
('Information Technology'),
('Operations');`





### Designation Table

- **Creating the Designation Table**

`CREATE TABLE Designation (
    DesignationID INT PRIMARY KEY AUTO_INCREMENT,
    DesignationName VARCHAR(50) NOT NULL
);`

- **Inserting Example Data**

`INSERT INTO Designation (DesignationName) VALUES
('Manager'),
('Assistant Manager'),
('Senior Analyst'),
('Developer'),
('Coordinator');`






### Employee Table

- **Creating the Employee Table**

`CREATE TABLE Employee (
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
);`




## Screenshots

## Login

![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/a46c0f8e-525e-40c6-8dc5-b09a3b1020bb)

## Home

![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/3ed52d9b-bc80-42b6-b0b9-1b3d5ba7e829)

## Manage Employees

![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/52e8c78f-18cd-4e67-8e41-d0c6f534e98b)

## Manage Users

![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/3a55d2af-a382-46f0-9362-a25bfe872577)

## Manage Department And Designation

![image](https://github.com/K4viyamato/employee-management-system/assets/113100464/5106bf6e-ebd8-4ea8-9f56-3e69d912ef3c)


## **NOTE**
- This system does not include password hashing. It's a basic Employee Management System that allows for managing employees' information within departments and designations.

* This project was developed for educational purposes to demonstrate JavaFX, MySQL integration, and basic CRUD (Create, Read, Update, Delete) functionalities in an Employee Management System.




