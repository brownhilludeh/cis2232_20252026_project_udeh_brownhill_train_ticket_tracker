-- Drop existing database if it exists
DROP DATABASE IF EXISTS cis2232_train_ticket_tracker;

-- Create a new database
CREATE DATABASE cis2232_train_ticket_tracker;
USE cis2232_train_ticket_tracker;

-- Create the Ticket table
CREATE TABLE Ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    issueDate DATE NOT NULL,
    station VARCHAR(50) NOT NULL,
    departureTime TIME NOT NULL,
    destination VARCHAR(50) NOT NULL,
    travelLength INT NOT NULL,
    ticketPrice DECIMAL(8,2) NOT NULL,
    isStudent BOOLEAN DEFAULT 0,
    isFrequent BOOLEAN DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO Ticket
(name, issueDate, station, departureTime, destination, travelLength, ticketPrice, isStudent, isFrequent)
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
    ('Emily Adams', '2025-10-09', 'Charlottetown', '08:05:00', 'Cornwall', 9, 45.00, 0, 0),
    ('Alice Johnson', '2025-10-01', 'Toronto', '08:30', 'Ottawa', 4.5, 75.00, 1, 0),
    ('Bob Smith', '2025-10-02', 'Montreal', '09:15', 'Quebec City', 3.0, 60.00, 0, 1),
    ('Carol White', '2025-10-02', 'Vancouver', '07:00', 'Calgary', 5.0, 120.00, 0, 0),
    ('David Brown', '2025-10-03', 'Halifax', '10:45', 'Saint John', 2.5, 45.00, 1, 1),
    ('Emma Davis', '2025-10-03', 'Edmonton', '13:30', 'Winnipeg', 6.0, 130.00, 0, 0),
    ('Frank Miller', '2025-10-04', 'Toronto', '15:00', 'Montreal', 5.0, 110.00, 1, 1),
    ('Grace Wilson', '2025-10-04', 'Ottawa', '16:20', 'Toronto', 4.5, 80.00, 0, 0),
    ('Henry Taylor', '2025-10-05', 'Calgary', '09:00', 'Vancouver', 5.5, 125.00, 1, 0),
    ('Isabella Moore', '2025-10-05', 'Quebec City', '12:15', 'Montreal', 3.0, 55.00, 0, 1),
    ('Jack Anderson', '2025-10-06', 'Winnipeg', '08:00', 'Edmonton', 6.0, 140.00, 1, 0),
    ('Karen Thomas', '2025-10-06', 'Saint John', '14:45', 'Halifax', 2.5, 50.00, 0, 1),
    ('Leo Martin', '2025-10-07', 'Montreal', '07:30', 'Toronto', 5.0, 105.00, 1, 1),
    ('Mia Lee', '2025-10-07', 'Vancouver', '11:00', 'Calgary', 5.0, 120.00, 0, 0),
    ('Nathan Harris', '2025-10-08', 'Toronto', '09:15', 'Ottawa', 4.5, 75.00, 1, 1),
    ('Olivia Clark', '2025-10-08', 'Edmonton', '13:30', 'Winnipeg', 6.0, 135.00, 0, 0),
    ('Paul Lewis', '2025-10-09', 'Calgary', '08:45', 'Vancouver', 5.5, 125.00, 1, 0),
    ('Quinn Walker', '2025-10-09', 'Ottawa', '15:00', 'Montreal', 3.0, 60.00, 0, 1),
    ('Rachel Hall', '2025-10-10', 'Halifax', '10:00', 'Saint John', 2.5, 45.00, 1, 1),
    ('Samuel Allen', '2025-10-10', 'Winnipeg', '12:30', 'Edmonton', 6.0, 140.00, 0, 0),
    ('Tina Young', '2025-10-11', 'Toronto', '14:15', 'Montreal', 5.0, 110.00, 1, 1),
    ('Alice Johnson', '2025-10-01', 'Toronto', '08:30', 'Ottawa', 4.5, 67.50, 1, 0),
    ('Bob Smith', '2025-10-02', 'Montreal', '09:15', 'Quebec City', 3.0, 45.00, 0, 1),
    ('Carol White', '2025-10-02', 'Vancouver', '07:00', 'Calgary', 5.0, 75.00, 0, 0),
    ('David Brown', '2025-10-03', 'Halifax', '10:45', 'Saint John', 2.5, 37.50, 1, 1),
    ('Emma Davis', '2025-10-03', 'Edmonton', '13:30', 'Winnipeg', 6.0, 90.00, 0, 0),
    ('Frank Miller', '2025-10-04', 'Toronto', '15:00', 'Montreal', 5.0, 75.00, 1, 1),
    ('Grace Wilson', '2025-10-04', 'Ottawa', '16:20', 'Toronto', 4.5, 67.50, 0, 0),
    ('Henry Taylor', '2025-10-05', 'Calgary', '09:00', 'Vancouver', 5.5, 82.50, 1, 0),
    ('Isabella Moore', '2025-10-05', 'Quebec City', '12:15', 'Montreal', 3.0, 45.00, 0, 1),
    ('Jack Anderson', '2025-10-06', 'Winnipeg', '08:00', 'Edmonton', 6.0, 90.00, 1, 0),
    ('Karen Thomas', '2025-10-06', 'Saint John', '14:45', 'Halifax', 2.5, 37.50, 0, 1),
    ('Leo Martin', '2025-10-07', 'Montreal', '07:30', 'Toronto', 5.0, 75.00, 1, 1),
    ('Mia Lee', '2025-10-07', 'Vancouver', '11:00', 'Calgary', 5.0, 75.00, 0, 0),
    ('Nathan Harris', '2025-10-08', 'Toronto', '09:15', 'Ottawa', 4.5, 67.50, 1, 1),
    ('Olivia Clark', '2025-10-08', 'Edmonton', '13:30', 'Winnipeg', 6.0, 90.00, 0, 0),
    ('Paul Lewis', '2025-10-09', 'Calgary', '08:45', 'Vancouver', 5.5, 82.50, 1, 0),
    ('Quinn Walker', '2025-10-09', 'Ottawa', '15:00', 'Montreal', 3.0, 45.00, 0, 1),
    ('Rachel Hall', '2025-10-10', 'Halifax', '10:00', 'Saint John', 2.5, 37.50, 1, 1),
    ('Samuel Allen', '2025-10-10', 'Winnipeg', '12:30', 'Edmonton', 6.0, 90.00, 0, 0),
    ('Tina Young', '2025-10-11', 'Toronto', '14:15', 'Montreal', 5.0, 75.00, 1, 1);

