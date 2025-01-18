# PizziSalle Order Management System

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technical Details](#technical-details)
4. [Design Patterns Used](#design-patterns-used)
5. [Setup Instructions](#setup-instructions)
6. [Usage](#usage)
7. [Folder Structure](#folder-structure)
8. [Contributors](#contributors)

---

## Project Overview

The **PizziSalle Order Management System** is a terminal-based Java application designed to streamline the order management process for the PizziSalle pizza restaurant group. The software uses design patterns to enhance scalability, maintainability, and overall performance. The system allows employees to register customer orders, select pizzas, customize ingredients, and manage customer data effectively.

### Business Context

PizziSalle operates four restaurants across:
- **Barcelona**
- **Girona**
- **Lleida**
- **Tarragona**

Additionally, there is a headquarters, PizziSalle General (PSG), which oversees operations but does not handle orders.

### Key Requirements
- Accepts orders for a variety of pizzas (including city-specific exclusives).
- Manages customer data and order history.
- Customizes pizza crusts and ingredients.
- Supports multiple beverage options.
- Implements a backend using SQLite for persistent data storage.

---

## Features

- **Pizza Customization**: Choose from pre-defined pizzas or customize ingredients and crust types.
- **Customer Management**: Stores customer details, including order history.
- **Order Assignment**: Assigns orders to delegations randomly.
- **Beverage Selection**: Offers water, soda, or beer (age-restricted).
- **Terminal-Based Interaction**: No GUI required; all operations are through the command line.

---

## Technical Details

- **Programming Language**: Java
- **Database**: SQLite for storing pizza and customer information.
- **Design Patterns**: Implements 6 design patterns (details below).
- **Input/Output**: Uses the terminal for data input and display.
- **Order Limitations**: Maximum of 10 for any order element.

---

## Design Patterns Used

1. **Singleton**:
    - **Usage**: Ensures a single instance of the database connection.
    - **Explanation**: Centralized database management.
    - **Class**: DatabaseConnection.java

2. **Factory**:
    - **Usage**: Simplifies the creation of pizza objects based on type.
    - **Explanation**: Decouples pizza instantiation logic.
    - **Class**: PizzaFactory.java

3. **Observer**:
    - **Usage**: Tracks updates to customer order status and notify delegations of new orders.
    - **Explanation**: Notifies different delegations about order changes.
    - **Class**: AbstractDelegation.java

4. **Decorator** (PizzaDecorator):
    - **Usage**: Adds extra ingredients or crust customizations dynamically.
    - **Explanation**: Extends pizza functionality without altering its structure.
    - **Class**: PizzaDecorator.java

5. **Composite**:
    - **Usage**: To represent the hierarchy of the delegations with the "General" delegation at the top and the city delegations at the bottom (like: Barcelona -> Girona -> Lleida -> Tarragona).
    - **Explanation**: Allows treating individual objects and compositions of objects uniformly.
    - **Class**: AbstractDelegation.java

6. **Patron Strategy**:
    - **Usage**: To implement different strategies for assigning orders to delegations.
    - **Explanation**: Allows selecting an algorithm at runtime.
    - **Class**: Beverage.java

---

## Setup Instructions

To run the **PizziSalle Order Management System**, follow the steps below:

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher.
~~- **SQLite**: Installed and configured.
- **Terminal/Command Prompt**: For interaction with the application.~~

### Steps
This steps will guide you through setting up the project and running the application.
But only if you are using IntelliJ IDEA as your IDE.
1. Navigate to the project directory and find the `Main.java` file. Is located at `src/main/java/salle.url.edu/Main.java`.
2. In the line `15` you will find a play button, click on it to run the application.
3. Follow the on-screen instructions to interact with the system.

#### Database Setup
1. To see the database `pizzisalle.db`, you can use a SQLite browser or command line. The database file is located at `src/main/resources/pizzisalle.db`.
2. If you want to see the database using the command line, you can use the following command:
   ```bash
    sqlite3 src/main/resources/pizzisalle.db
    ```
   Or you can use a SQLite browser to view the database. In this case, you need to install the drivers for SQLite.
3. To see the tables in the database, you can use the following command:
   ```sql
    .tables
    ```
4. To see the schema of a table, you can use the following command:
   ```sql
    .schema table_name
    ```
6. To see the data in a table, you can use the following command:
   ```sql
    SELECT * FROM table_name;
    ```
   
---

## Usage

1. **Main Menu**: The application starts with a main menu that allows you to navigate to different sections.
2. **Order Management**: Register new orders, customize pizzas, and select beverages.
3. **Customer Management**: Add new customers, view customer details, and order history.
4. **Delegation Management**: Assign orders to delegations and view delegation details.
5. **Database Management**: View database tables, schema, and data.
6. **Exit**: Close the application.
7. **NOTE**: Follow the on-screen instructions to navigate through the application.

---

## Folder Structure

The project is structured as follows:

```
.
.
├── src/
│   └── main/
│       ├── java/
│       │    └── salle.url.edu/
│       │       ├── controllers/
│       │       │   └── PizziSalleController.java
│       │       ├── database/
│       │       │   └── DatabaseConnection.java
│       │       ├── enums/
│       │       │   ├── Beverage.java
│       │       │   ├── Crust.java
│       │       │   ├── Delegation.java
│       │       │   └── Ingredient.java
│       │       ├── models/
│       │       │   ├── delegations/
│       │       │   │   ├── AbstractDelegation.java
│       │       │   │   ├── GeneralDelegation.java
│       │       │   │   └── SpecificDelegation.java
│       │       │   ├── drinks/
│       │       │   │   ├── BeerValidation.java
│       │       │   │   ├── BeverageValidation.java
│       │       │   │   └── DefaultValidation.java
│       │       │   ├── pizzas/
│       │       │   │   ├── all_pizzas/
│       │       │   │   │   ├── no_ingredients/
│       │       │   │   │   │   └── Margherita.java
│       │       │   │   │   ├── three_ingredients/
│       │       │   │   │   │   ├── American.java
│       │       │   │   │   │   ├── BaconCrispy.java
│       │       │   │   │   │   └── Traviata.java
│       │       │   │   │   └── two_ingredients/
│       │       │   │   │       └── Hawaiian.java
│       │       │   │   ├── decorators/
│       │       │   │   │   ├── BeverageDecorator.java
│       │       │   │   │   ├── CrustDecorator.java
│       │       │   │   │   ├── IngredientDecorator.java
│       │       │   │   │   └── PizzaDecorator.java
│       │       │   │   └── specific_pizzas/
│       │       │   │       └── Pizza.java
│       │       │   ├── Customer.java
│       │       │   └── Order.java
│       │       ├── repositories/
│       │       │   ├── CustomerRepository.java
│       │       │   ├── OrderRepository.java
│       │       │   └── PizzaRepository.java
│       │       ├── services/
│       │       │   ├── CustomerService.java
│       │       │   ├── OrderService.java
│       │       │   ├── PizzaFactory.java
│       │       │   └── PizzaService.java
│       │       ├── utils/
│       │       │   └── ValidationUtils.java
│       │       └── Main.java
│       └── resources/
│           ├── pizzisalle.db
│           └── PizziSalle.drawio
├── target/
|   └── ...
├── .gitignore
├── pom.xml
└── README.md
```

---

## Contributors
- **Joaquim Angàs - (joaquim.angas)**
- **Andrea Ballester - (andrea.ballester)**
- **Pol Guarch - (pol.guarch)**
- **Armand Lopez - (armand.lopez)**
- **Marc Martín - (marc.martin)**
- **Oriol Rebordosa - (oriol.rebordosa)**
- **Carlos Romero - (c.romero)**
- **Joan Tarragó - (j.tarrago)**
