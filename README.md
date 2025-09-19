# Project:  Train Ticket Tracker #

---
## Development Team ##
Business Client:  BJ MacLean
<br/>
Lead Developer:  BJ MacLean
<br/>
Quality Control:  BJ MacLean
<br/>
---
## Description ##
This project will allow the tracking of squash skills based on standard technical skill assessments.  This includes forehad and backhand drives and volleys.  The technical score
is calculated based on the players number of successful hits.  The following two links provide a demonstration of how it works in a spreadsheet and gives examples of
the calculation.  <br/><br/>
- https://docs.google.com/spreadsheets/d/19vjrh-0o7_vkw0Wj2n5tp2LzN9qZ-qc0K1GJWFWlPsQ<br/>
- https://forms.gle/K1BioRyX1j8s6Dct9
---
## Color ##
Main Color:  #000000 (black)<br/>
Secondary Color: #9c9a9a (grey)<br/>
Accent Color:  #fcba03 (yellow)<br/>
---
## Required Fields ##
This will be a list of fields and their datatype (class design format).  There are expected to be a minimum of six fields.  
-id: int //primary key <br/>
-assessmentDate: String Note:  yyyy-MM-dd <br/>
-createdDateTime: String Note: yyyy-MM-dd hh:mm:ss  <br/>
-athleteName: String <br/>
-assessorName: String <br/>
-forehandDrives: int <br/>
-backhandDrives: int <br/>
-forehandVolleyMax: int <br/>
-forehandVolleySum: int <br/>
-backhandVolleyMax: int <br/>
-backhandVolleySum: int <br/>
-technicalScore: int <br/>
---
## Calculation ##

Once the user enters all of values, the program will calculate the overall points for the assessment.  Each skill is entered provides points towards the overall technical score.  See the spreadsheet link above for a functioning example.
<br/>
Fh and bh drives 15 points each<br/>
Fh and bh volley sum 5 points each<br/>
Fh and bh volley max 8 points each<br/>
<br/>
Example technical score calculation:<br/>
<br/>
forehandDrives * 15 + backhandDrives *15<br/>
+forehandVolleySum * 5 + backhandVolleySum * 5<br/>
+forehandVolleyMax * 8 + backhandVolleyMax *8<br/>

---
## Report Details -THESE ARE NOT REQUIRED FOR SPRINT 1##
1 Provide all of the assesssments for a given player.  The player name can be entered and the report will provide all of their assessments ordered by date assessed descending.<br/>
2 Provide the assessments in a technical assessment score range.  The user can enter the min and max (inclusive) and see all of the assessments completed in that range.<br/>