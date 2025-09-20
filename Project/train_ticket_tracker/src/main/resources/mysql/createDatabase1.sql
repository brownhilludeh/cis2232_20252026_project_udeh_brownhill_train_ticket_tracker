DROP DATABASE IF EXISTS cis2232_train_ticket_tracker;
CREATE DATABASE cis2232_train_ticket_tracker;
USE cis2232_train_ticket_tracker;

CREATE TABLE Ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    issueDate DATE NOT NULL,
    station VARCHAR(50) NOT NULL ,
    departureTime TIME NOT NULL ,
    destination VARCHAR(50) NOT NULL ,
    travelLength INT NOT NULL ,
    ticketPrice DECIMAL(8,2) NOT NULL ,
    studentIndicator BOOLEAN DEFAULT 0,
    frequentRiderIndicator BOOLEAN DEFAULT 0 ,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP );

INSERT INTO Ticket 
(name, issueDate, station, departureTime, destination, travelLength, ticketPrice, studentIndicator, frequentRiderIndicator) 
VALUES
('John Doe', '2025-09-20', 'Charlottetown', '08:30:00', 'Stratford', 20, 100.00, 1, 0),
('Mary Smith', '2025-09-21', 'Summerside', '14:15:00', 'Charlottetown', 45, 225.00, 0, 1),
('Alex Brown', '2025-09-22', 'Cornwall', '09:00:00', 'Charlottetown', 10, 50.00, 1, 1),
('Sophia Johnson', '2025-09-23', 'Charlottetown', '07:45:00', 'Summerside', 60, 300.00, 0, 0),
('Liam Wilson', '2025-09-24', 'Stratford', '16:30:00', 'Charlottetown', 15, 75.00, 1, 0),
('Emma Davis', '2025-09-25', 'Cornwall', '18:00:00', 'Stratford', 12, 60.00, 0, 0),
('James Miller', '2025-09-26', 'Charlottetown', '10:15:00', 'Cornwall', 8, 40.00, 1, 1),
('Olivia Garcia', '2025-09-27', 'Summerside', '12:00:00', 'Charlottetown', 55, 275.00, 0, 1),
('Benjamin Lee', '2025-09-28', 'Charlottetown', '06:50:00', 'Summerside', 65, 325.00, 1, 0),
('Mia Martinez', '2025-09-29', 'Stratford', '19:30:00', 'Cornwall', 18, 90.00, 0, 0),
('Ethan Clark', '2025-09-30', 'Cornwall', '08:20:00', 'Charlottetown', 14, 70.00, 1, 0),
('Charlotte Lewis', '2025-10-01', 'Charlottetown', '13:40:00', 'Stratford', 22, 110.00, 0, 0),
('Henry Walker', '2025-10-02', 'Summerside', '17:15:00', 'Cornwall', 48, 240.00, 0, 1),
('Amelia Hall', '2025-10-03', 'Charlottetown', '09:10:00', 'Summerside', 62, 310.00, 1, 1),
('Lucas Allen', '2025-10-04', 'Stratford', '07:30:00', 'Cornwall', 19, 95.00, 0, 0),
('Isabella Young', '2025-10-05', 'Cornwall', '11:00:00', 'Charlottetown', 13, 65.00, 1, 0),
('Mason Hernandez', '2025-10-06', 'Charlottetown', '15:25:00', 'Summerside', 50, 250.00, 0, 0),
('Ava King', '2025-10-07', 'Summerside', '06:45:00', 'Charlottetown', 70, 350.00, 1, 1),
('William Scott', '2025-10-08', 'Cornwall', '10:50:00', 'Stratford', 16, 80.00, 0, 0),
('Emily Adams', '2025-10-09', 'Charlottetown', '08:05:00', 'Cornwall', 9, 45.00, 0, 0);
