# Household Task Manager

This project implements a desktop application that helps couples fairly divide and manage household chores. The system uses Java with a SQLite database and features a chore assignment algorithm, point tracking system, and visual charts to monitor progress.

## Overview

The application supports the following key features:

- Assign and schedule household chores for two users
- Earn points for completed tasks
- View bar and pie charts of chore completion using **JFreeChart**
- Automatically distribute chores based on estimated completion times using a fair share algorithm
- Data persistence via an integrated **SQLite** database

## Methodology

- **Technology Stack**: Java (Swing GUI), SQLite, JFreeChart  
- **Chore Assignment Algorithm**: Chores are distributed weekly using a load-balancing approach based on time estimates per user. The algorithm ensures fair distribution of work by dynamically normalising load across both users.
- **Database**: The system connects to a SQLite database to store users, chores, completion status, scores, and time logs.
- **Visualisation**: Weekly chore completion is shown through dynamically generated bar and pie charts, helping couples track their efforts over time.

## System Features

- Role-based chore assignment and tracking
- Completion time estimates and scoring system
- Weekly load balancing and fair share calculation
- GUI interfaces for:
  - Chore management
  - Chart selection (bar/pie)
  - Real-time progress overview
- OAuth not applicable (local use only)

## Results

The system successfully facilitates weekly planning and promotes accountability in shared domestic responsibilities. The visual dashboard helps users understand workload balance over time.

## Ethical Considerations

- **Fairness**: The algorithm prioritises balanced workloads to prevent one user from being overburdened.
- **Data Use**: All user data is stored locally and used solely for application logic and display.

## Future Work

- Add user authentication for multiple households  
- Export progress reports to PDF  
- Schedule recurring chores based on frequency  

## Setup Instructions

To run this project locally, you will need:

- **Java Development Kit (JDK)** 11 or later
- **NetBeans IDE** (or similar Java IDE)
- **SQLite JDBC Driver**
- **JFreeChart** library for charts

Steps:

1. Clone the repository into your Java IDE
2. Ensure your SQLite DB path is correctly set in `ChoresDatabase.java`
3. Add the required `.jar` files for JFreeChart and SQLite JDBC to your build path
4. Run `HouseholdChoreSystem.java` to launch the app

## Acknowledgements

This project was developed as part of the **KF5012 Software Engineering Project** module at Northumbria University, with a focus on algorithm design, database integration, and desktop user interfaces.
