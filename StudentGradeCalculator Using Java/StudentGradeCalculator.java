import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Student Name
        System.out.print("Enter the student's name: ");
        String studentName = scanner.nextLine();

        // Input: Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Input: Marks obtained in each subject
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        System.out.println("Enter the marks obtained in each subject (out of 100):");
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("Subject %d: ", i + 1);
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Grade calculation
        char grade = calculateGrade(averagePercentage);

        // Display Results in tabular format
        displayResults(studentName, numSubjects, marks, totalMarks, averagePercentage, grade);

        // Personalized message
        congratulateOrMotivate(grade);

        scanner.close();
    }

    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private static void displayResults(String studentName, int numSubjects, int[] marks, int totalMarks, double averagePercentage, char grade) {
        System.out.println("\n--- Student Report ---");
        System.out.printf("Name: %s\n", studentName);
        System.out.printf("%-10s%-10s\n", "Subject", "Marks");
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("%-10d%-10d\n", i + 1, marks[i]);
        }
        System.out.printf("\nTotal Marks: %d\n", totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.printf("Grade: %c\n", grade);
    }

    private static void congratulateOrMotivate(char grade) {
        if (grade == 'A' || grade == 'B' || grade == 'C') {
            System.out.println("\nCongratulations! You passed with a good grade. Keep up the great work!");
        } else if (grade == 'D') {
            System.out.println("\nYou passed, but there's room for improvement. Keep studying and you'll do better next time!");
        } else {
            System.out.println("\nUnfortunately, you did not pass. Don't be discouraged; use this as motivation to work harder and improve.");
        }
    }
}
