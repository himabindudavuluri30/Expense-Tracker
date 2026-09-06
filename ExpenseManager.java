import java.util.ArrayList;
import java.io.*;

public class ExpenseManager {

    ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public int getNextId() {

        int maxId = 0;

        for (Expense expense : expenses) {

            if (expense.id > maxId) {
                maxId = expense.id;
            }
        }

        return maxId + 1;
    }

    public int getExpenseCount() {
        return expenses.size();
    }

    public void viewExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        printTableHeader();

        for (Expense expense : expenses) {
            printExpense(expense);
        }

        printTableFooter();

        System.out.println(
            "Total number of expenses: " + expenses.size()
        );
    }

    private void printTableHeader() {

        System.out.println();
        System.out.println(
            "--------------------------------------------------------------------------------"
        );

        System.out.printf(
            "%-5s %-12s %-15s %-25s %-15s%n",
            "ID",
            "Amount",
            "Category",
            "Description",
            "Date"
        );

        System.out.println(
            "--------------------------------------------------------------------------------"
        );
    }

    private void printExpense(Expense expense) {

        System.out.printf(
            "%-5d ₹%-11.2f %-15s %-25s %-15s%n",
            expense.id,
            expense.amount,
            expense.category,
            expense.description,
            expense.date
        );
    }

    private void printTableFooter() {

        System.out.println(
            "--------------------------------------------------------------------------------"
        );
    }

    public double getTotal() {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        return total;
    }

    public void searchByCategory(String category) {

        boolean found = false;

        System.out.println("\nExpenses in category: " + category);

        for (Expense expense : expenses) {

            if (expense.category.equalsIgnoreCase(category)) {

                if (!found) {
                    printTableHeader();
                }

                printExpense(expense);

                found = true;
            }
        }

        if (found) {
            printTableFooter();
        } else {
            System.out.println("No expenses found.");
        }
    }

    public double getCategoryTotal(String category) {

        double total = 0;

        for (Expense expense : expenses) {

            if (expense.category.equalsIgnoreCase(category)) {
                total += expense.amount;
            }
        }

        return total;
    }

    public void deleteExpense(int id) {

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).id == id) {

                expenses.remove(i);

                System.out.println(
                    "Expense deleted successfully!"
                );

                return;
            }
        }

        System.out.println("Expense ID not found.");
    }

    public void editExpense(
            int id,
            double amount,
            String category,
            String description,
            String date) {

        for (Expense expense : expenses) {

            if (expense.id == id) {

                expense.amount = amount;
                expense.category = category;
                expense.description = description;
                expense.date = date;

                System.out.println(
                    "Expense updated successfully!"
                );

                return;
            }
        }

        System.out.println("Expense ID not found.");
    }

    public double getMonthlyTotal(String month) {

        double total = 0;

        for (Expense expense : expenses) {

            String[] parts = expense.date.split("-");

            if (parts.length == 3 &&
                parts[1].equals(month)) {

                total += expense.amount;
            }
        }

        return total;
    }

    public void clearAllExpenses() {

        expenses.clear();

        System.out.println(
            "All expenses cleared successfully!"
        );
    }

    public void saveExpenses() {

        try {

            FileWriter writer =
                new FileWriter("expenses.txt");

            for (Expense expense : expenses) {

                writer.write(
                    expense.id + "," +
                    expense.amount + "," +
                    expense.category + "," +
                    expense.description + "," +
                    expense.date + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                "Error saving expenses."
            );
        }
    }

    public void loadExpenses() {

        if (!expenses.isEmpty()) {
            return;
        }

        try {

            File file =
                new File("expenses.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader =
                new BufferedReader(
                    new FileReader(file)
                );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                    line.split(",", -1);

                if (data.length != 5) {
                    continue;
                }

                Expense expense =
                    new Expense();

                expense.id =
                    Integer.parseInt(data[0]);

                expense.amount =
                    Double.parseDouble(data[1]);

                expense.category =
                    data[2];

                expense.description =
                    data[3];

                expense.date =
                    data[4];

                expenses.add(expense);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println(
                "Error loading expenses."
            );
        }
    }
}