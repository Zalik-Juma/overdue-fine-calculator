// Program to calculate overdue fines for library books
/*
* Name: Zalik Barasa Juma
* Reg No: J77-10594-2024
* Description: A program to calculate overdue fines for library books
*/
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibraryFineCalculator {
    
    // Constants for fine rates
    private static final int FINE_RATE_1_7 = 20;
    private static final int FINE_RATE_8_14 = 50;
    private static final int FINE_RATE_15_PLUS = 100;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input book details
        System.out.println("=== Library Fine Calculator ===");
        System.out.print("Enter Book ID: ");
        String bookID = scanner.nextLine();
        
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        String dueDateStr = scanner.nextLine();
        LocalDate dueDate = LocalDate.parse(dueDateStr);
        
        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDateStr = scanner.nextLine();
        LocalDate returnDate = LocalDate.parse(returnDateStr);
        
        // Calculate days overdue
        long daysOverdue = calculateDaysOverdue(dueDate, returnDate);
        
        // Calculate fine
        int fineRate = getFineRate(daysOverdue);
        // Calculate total fine by multiplying daysOverdue by fineRate
        int fineAmount = (int) (daysOverdue * fineRate); 
        
        // Display results
        displayResults(bookID, dueDate, returnDate, daysOverdue, fineRate, fineAmount);
        scanner.close();
    }
    
    private static long calculateDaysOverdue(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)) {
            // If return date is before or on due date, no overdue
            return 0;
        }
        // Calculate days between due date and return date
        return ChronoUnit.DAYS.between(dueDate, returnDate);
    }
    
    private static int getFineRate(long daysOverdue) {
        if (daysOverdue <= 0) {
            return 0;
        } else if (daysOverdue <= 7) {
            return FINE_RATE_1_7;
        } else if (daysOverdue <= 14) {
            return FINE_RATE_8_14;
        } else {
            return FINE_RATE_15_PLUS;
        }
    }

    private static void displayResults(String bookID, LocalDate dueDate, LocalDate returnDate, long daysOverdue, int fineRate, int fineAmount) {
        System.out.println("\n=== FINE CALCULATION RESULTS ===");
        System.out.println("Book ID: " + bookID);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Days Overdue: " + daysOverdue);
        
        // Only display the fine rate if a fine is applicable
        if (fineAmount > 0) {
            System.out.println("Fine Rate: sh " + fineRate + " per day");
        } else {
            System.out.println("Fine Rate: sh 0 per day"); // Consistency for display
        }
        
        System.out.println("Total Fine Amount: sh " + fineAmount);

        if (daysOverdue == 0) {
            System.out.println("Status: Book returned on time - No fine!");
        } else {
            System.out.println("Status: Overdue - Fine applied");
        }
    }
}
