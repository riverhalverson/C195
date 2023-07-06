Appointment Scheduler

Purpose: The purpose of this application is to allow a user to schedule appointments between customers. The application verifies login credentials, and logs successful and unsuccessful attempts in a log file. The user is able to create, update and delete customer and appointments from the main screen. The user is able to view reports with summarized information on the reports page.

Author: River Halverson
Contact:  rhalve3@my.wgu.edu
Version: 1.0
Date: 6/29/2023

IDE: IntelliJ 2023.1.1 (Community Edition)
Java:  17.0.7.0
JavaFX: 20.0.1?

Instructions: The user logs in with appropriate credentials upon start up, if there is incorrect information entered an error will display. After successful login, the user is alerted if there is an appointment within 15 minutes of their local time on todays date. On the main screen the user will see add, update, and delete buttons on the upper right, these will change accordingly if the user has appointment views or customers views selected along the bottom. The bottom radio buttons control if the user sees all the appointments, the appointments for this week, or the appointments for this month, or lastly to view all customers in the database. When the user wants to select or update either an appointment or customer, they simply click it on the table view and make their selection on the upper right. If they select add or update appointment, they will be brought to the appropriate page. The appointment id is auto generated so cannot be changed, the title, description and location are all user entered information. The rest is with combo boxes and a date picker. The user is informed if invalid data is entered into these fields. The user will be alerted if there is an overlap of appointments for a certain user, or if an appointment being made or updated falls out of business hours. If add or update customer is selected, the user can enter a name, address, postal code, and phone number for the customer, then they select the country and the divisions box auto fills accordingly. Once a customer or appointment is added, or updated, the user is returned to the main screen. From here they also have the option to view reports by clicking the reports button. On the reports window they can view appointments by each contact by selecting a contact in the upper right combo box. On the lower left they can view number of appointments by month for a certain appointment type, by making a selection in the combo box above the table view. On the lower right you can see a summary of the customers per country. When the user is done viewing reports they can return to the main screen by clicking exit, and on the main screen they can exit the program by clicking exit.

Additional Report: The additional report I added to my report page shows the user the total number of customers in each country. It simply lists each country that has a customer in it, and the total number of customers in that country next to it. 

MySQL Connector: 8.0.25
