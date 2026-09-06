
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static boolean isValidDate(String date) {

        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate.parse(date, formatter);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public static String getCurrentDate() {

        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return today.format(formatter);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseManager manager = new ExpenseManager();

        manager.loadExpenses();

        while (true) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("        EXPENSE TRACKER");
            System.out.println("=================================");

            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total");
            System.out.println("4. Search by Category");
            System.out.println("5. Delete Expense");
            System.out.println("6. Edit Expense");
            System.out.println("7. Monthly Total");
            System.out.println("8. Category Total");
            System.out.println("9. Expense Count");
            System.out.println("10. Clear All Expenses");
            System.out.println("11. Exit");

            System.out.print("\nEnter your choice: ");

            if (!sc.hasNextInt()) {

                System.out.println("Please enter a valid number!");

                sc.nextLine();

                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            // Add Expense
            if (choice == 1) {

                Expense expense = new Expense();

                expense.setId(manager.getNextId());

                while (true) {

                    System.out.print("Enter amount: ");

                    if (sc.hasNextDouble()) {

                        double amount = sc.nextDouble();
                        sc.nextLine();

                        if (amount > 0) {

                            expense.setAmount(amount);

                            break;
                        }

                        System.out.println(
                                "Amount must be greater than 0."
                        );

                    } else {

                        System.out.println(
                                "Enter a valid amount."
                        );

                        sc.nextLine();
                    }
                }

                while (true) {

                    System.out.print("Enter category: ");

                    String category =
                            sc.nextLine().trim();

                    if (!category.isEmpty()) {

                        expense.setCategory(category);

                        break;
                    }

                    System.out.println(
                            "Category cannot be empty."
                    );
                }

                while (true) {

                    System.out.print("Enter description: ");

                    String description =
                            sc.nextLine().trim();

                    if (!description.isEmpty()) {

                        expense.setDescription(description);

                        break;
                    }

                    System.out.println(
                            "Description cannot be empty."
                    );
                }

                expense.setDate(getCurrentDate());

                System.out.println(
                        "Date: " + expense.getDate()
                );

                manager.addExpense(expense);

                manager.saveExpenses();

                System.out.println(
                        "Expense added successfully!"
                );
            }

            // View Expenses
            else if (choice == 2) {

                manager.viewExpenses();
            }

            // Total
            else if (choice == 3) {

                System.out.printf(
                        "Total Expense: ₹%.2f%n",
                        manager.getTotal()
                );
            }

            // Search by Category
            else if (choice == 4) {

                System.out.print("Enter category: ");

                String category =
                        sc.nextLine().trim();

                if (category.isEmpty()) {

                    System.out.println(
                            "Category cannot be empty."
                    );

                } else {

                    manager.searchByCategory(category);
                }
            }

            // Delete Expense
            else if (choice == 5) {

                System.out.print("Enter expense ID: ");

                if (sc.hasNextInt()) {

                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print(
                            "Are you sure you want to delete this expense? (yes/no): "
                    );

                    String confirmation =
                            sc.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {

                        manager.deleteExpense(id);

                        manager.saveExpenses();

                    } else {

                        System.out.println(
                                "Delete cancelled."
                        );
                    }

                } else {

                    System.out.println("Invalid ID!");

                    sc.nextLine();
                }
            }

            // Edit Expense
            else if (choice == 6) {

                System.out.print(
                        "Enter expense ID to edit: "
                );

                if (!sc.hasNextInt()) {

                    System.out.println("Invalid ID!");

                    sc.nextLine();

                    continue;
                }

                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter new amount: ");

                if (!sc.hasNextDouble()) {

                    System.out.println(
                            "Invalid amount!"
                    );

                    sc.nextLine();

                    continue;
                }

                double amount = sc.nextDouble();
                sc.nextLine();

                if (amount <= 0) {

                    System.out.println(
                            "Amount must be greater than 0."
                    );

                    continue;
                }

                System.out.print("Enter new category: ");

                String category =
                        sc.nextLine().trim();

                if (category.isEmpty()) {

                    System.out.println(
                            "Category cannot be empty."
                    );

                    continue;
                }

                System.out.print(
                        "Enter new description: "
                );

                String description =
                        sc.nextLine().trim();

                if (description.isEmpty()) {

                    System.out.println(
                            "Description cannot be empty."
                    );

                    continue;
                }

                String date;

                while (true) {

                    System.out.print(
                            "Enter new date (DD-MM-YYYY): "
                    );

                    date =
                            sc.nextLine().trim();

                    if (isValidDate(date)) {
                        break;
                    }

                    System.out.println(
                            "Invalid date! Use DD-MM-YYYY."
                    );
                }

                manager.editExpense(
                        id,
                        amount,
                        category,
                        description,
                        date
                );

                manager.saveExpenses();
            }

            // Monthly Total
            else if (choice == 7) {

                System.out.print(
                        "Enter month (MM): "
                );

                String month =
                        sc.nextLine().trim();

                if (!month.matches(
                        "0[1-9]|1[0-2]")) {

                    System.out.println(
                            "Invalid month! Enter 01 to 12."
                    );

                    continue;
                }

                double total =
                        manager.getMonthlyTotal(month);

                System.out.printf(
                        "Total for month %s: ₹%.2f%n",
                        month,
                        total
                );
            }

            // Category Total
            else if (choice == 8) {

                System.out.print(
                        "Enter category: "
                );

                String category =
                        sc.nextLine().trim();

                if (category.isEmpty()) {

                    System.out.println(
                            "Category cannot be empty."
                    );

                } else {

                    double total =
                            manager.getCategoryTotal(category);

                    System.out.printf(
                            "Total spent on %s: ₹%.2f%n",
                            category,
                            total
                    );
                }
            }

            // Expense Count
            else if (choice == 9) {

                System.out.println(
                        "Total number of expenses: "
                                + manager.getExpenseCount()
                );
            }

            // Clear All
            else if (choice == 10) {

                System.out.print(
                        "Are you sure you want to clear ALL expenses? (yes/no): "
                );

                String confirmation =
                        sc.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {

                    manager.clearAllExpenses();

                    manager.saveExpenses();

                } else {

                    System.out.println(
                            "Clear operation cancelled."
                    );
                }
            }

            // Exit
            else if (choice == 11) {

                manager.saveExpenses();

                System.out.println(
                        "Expenses saved successfully!"
                );

                System.out.println(
                        "Thank you for using Expense Tracker!"
                );

                break;
            }

            else {

                System.out.println(
                        "Invalid choice!"
                );
            }
        }

        sc.close();
    }
}
