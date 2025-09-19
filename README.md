# Project:  Train Ticket Tracker #

---
## Development Team ##
Business Client:  Ryley Collicutt 
<br/>
Lead Developer:  Brownhill Udeh 
<br/>
Quality Control:  Hunter
<br/>
---
## Description ##
This project will allow the tracking of **train tickets**.  
It records details such as the customer’s name, when the ticket was issued, station of departure, time of departure, travel length, and destination.  
The application also calculates the ticket price based on travel distance and applies discounts depending on whether the passenger is a student or a frequent rider.    <br/><br/>

---
## Color ##
Main Color: #003366 (dark blue) <br/>
Secondary Color: #f2f2f2 (light grey) <br/>
Accent Color: #ff9900 (orange) <br/>


---
## Required Fields ##
## Required Fields ##
This will be a list of fields and their datatype (class design format).  
There are expected to be a minimum of six fields.  

- id: int // primary key <br/>  
- name: String // customer name <br/>  
- issueDate: String // yyyy-MM-dd <br/>  
- station: String // station customer is leaving from <br/>  
- departureTime: String // hh:mm <br/>  
- destination: String // train destination <br/>  
- travelLength: int // length of travel in kilometers <br/>  
- ticketPrice: int // price of the ticket (calculated) <br/>  
- studentIndicator: boolean // is the customer a student? <br/>  
- frequentRiderIndicator: boolean // is the customer a frequent rider? <br/>  

---
## Calculation ##

The program will calculate the **ticket price** based on travel length and discounts.  

<br/>
Base Price = travelLength * 5 <br/>  
Student Discount = 20% <br/>  
Frequent Rider Discount = 15% <br/>  
<br/>

Example ticket price calculation:  
ticketPrice = travelLength * 5

if studentIndicator == true
ticketPrice = ticketPrice * 0.8

if frequentRiderIndicator == true
ticketPrice = ticketPrice * 0.85

---
## Report Details - THESE ARE NOT REQUIRED FOR SPRINT 1 ##
1. Provide all tickets purchased by a given passenger (search by name).  <br/>
2. Provide tickets issued on a specific date (ordered by departure time).  <br/>
3. Provide a revenue report showing all tickets sold within a date range.  
