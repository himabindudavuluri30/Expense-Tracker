# 💰 Expense Tracker

A console-based **Expense Tracker application built using Java** that allows users to manage their daily expenses efficiently.

## 🚀 Features

* ➕ Add Expense
* 📋 View All Expenses
* 💰 Calculate Total Expenses
* 🔍 Search Expenses by Category
* ✏️ Edit Expense
* 🗑️ Delete Expense
* 📅 Calculate Monthly Total
* 📊 Calculate Category-wise Total
* 🔢 Count Total Expenses
* 🧹 Clear All Expenses
* 💾 Save Expenses to a File
* 📂 Load Expenses Automatically

## 🛠️ Technologies Used

* **Java**
* **OOP (Object-Oriented Programming)**
* **ArrayList**
* **File Handling**
* **Exception Handling**
* **Java Date & Time API**
* **Git & GitHub**

## 📂 Project Structure

```text
ExpenseTracker/
│
├── Main.java
├── Expense.java
├── ExpenseManager.java
├── expenses.txt
├── README.md
└── .gitignore
```

### 📄 File Description

| File                  | Description                                    |
| --------------------- | ---------------------------------------------- |
| `Main.java`           | Handles user interaction and application menu  |
| `Expense.java`        | Represents an expense using an object          |
| `ExpenseManager.java` | Handles expense operations and file storage    |
| `expenses.txt`        | Stores expense data                            |
| `README.md`           | Project documentation                          |
| `.gitignore`          | Prevents unnecessary files from being uploaded |

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/himabindudavuluri30/Expense-Tracker.git
```

### 2. Navigate to the project

```bash
cd Expense-Tracker
```

### 3. Compile the Java files

```bash
javac Main.java Expense.java ExpenseManager.java
```

### 4. Run the application

```bash
java Main
```

## 🖥️ Application Menu

```text
=================================
        EXPENSE TRACKER
=================================

1. Add Expense
2. View Expenses
3. View Total
4. Search by Category
5. Delete Expense
6. Edit Expense
7. Monthly Total
8. Category Total
9. Expense Count
10. Clear All Expenses
11. Exit
```

## 💾 Data Storage

The application uses a simple text file named `expenses.txt` to store expense information.

Each expense contains:

* Expense ID
* Amount
* Category
* Description
* Date

The saved expenses are automatically loaded when the application starts.

## 🎯 Concepts Practiced

This project helped practice important Java concepts such as:

* Classes and Objects
* Encapsulation
* Constructors
* Getters and Setters
* ArrayList
* Loops
* Conditional Statements
* Methods
* Exception Handling
* File Handling
* Date Validation
* Git and GitHub

## 🔮 Future Improvements

Possible future upgrades include:

* MySQL database integration
* Spring Boot REST API
* User authentication
* Web-based interface
* Expense charts and dashboards
* CSV/PDF export
* Multiple user accounts

