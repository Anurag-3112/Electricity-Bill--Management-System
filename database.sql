-- -----------------------------------------------------
-- Database: electricity_billing_system
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS electricity_billing_system;
USE electricity_billing_system;

-- -----------------------------------------------------
-- Table: Login
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Login (
    UserName VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL
);

-- -----------------------------------------------------
-- Table: Emp (Customer Information)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Emp (
    Name VARCHAR(100) NOT NULL,
    MeterNumber VARCHAR(20) PRIMARY KEY,
    Address VARCHAR(255),
    State VARCHAR(50),
    City VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(15)
);

-- -----------------------------------------------------
-- Table: Bill
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Bill (
    BillID INT AUTO_INCREMENT PRIMARY KEY,
    MeterNumber VARCHAR(20),
    Units INT NOT NULL,
    Month VARCHAR(20) NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (MeterNumber) REFERENCES Emp(MeterNumber)
);

-- -----------------------------------------------------
-- Table: Tax
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Tax (
    TaxID INT AUTO_INCREMENT PRIMARY KEY,
    MeterLocation VARCHAR(50),
    MeterType VARCHAR(50),
    PhaseCode VARCHAR(20),
    BillType VARCHAR(20),
    Days INT,
    MeterRent DECIMAL(10,2),
    MCB_Rent DECIMAL(10,2),
    ServiceRent DECIMAL(10,2),
    GST DECIMAL(5,2)
);

-- -----------------------------------------------------
-- Sample Data
-- -----------------------------------------------------

INSERT INTO Login (UserName, Password) VALUES
('admin', 'admin123');

INSERT INTO Emp (Name, MeterNumber, Address, State, City, Email, Phone) VALUES
('John Doe', 'MTR001', '123 Main Street', 'California', 'Los Angeles', 'john@example.com', '1234567890'),
('Jane Smith', 'MTR002', '456 Oak Avenue', 'New York', 'New York', 'jane@example.com', '9876543210');

INSERT INTO Tax (MeterLocation, MeterType, PhaseCode, BillType, Days, MeterRent, MCB_Rent, ServiceRent, GST) VALUES
('Indoor', 'Digital', 'PC001', 'Residential', 30, 50.00, 20.00, 15.00, 18.00),
('Outdoor', 'Analog', 'PC002', 'Commercial', 30, 75.00, 25.00, 20.00, 18.00);
