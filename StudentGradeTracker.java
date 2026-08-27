import java.util.*;

public class StudentGradeTracker {
    static class Student {
        String name;
        ArrayList<Double> scores;

        Student(String name, ArrayList<Double> scores) {
            this.name = name;
            this.scores = scores;
        }

        double average() {
            double total = 0;
            for (double score : scores) total += score;
            return scores.isEmpty() ? 0 : total / scores.size();
        }

        double highest() {
            return scores.isEmpty() ? 0 : Collections.max(scores);
        }

        double lowest() {
            return scores.isEmpty() ? 0 : Collections.min(scores);
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter number of subjects: ");
        int n = readInt(1, 100);
        ArrayList<Double> scores = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter score for subject " + i + " (0-100): ");
            scores.add(readDouble(0, 100));
        }

        students.add(new Student(name, scores));
        System.out.println("Student added successfully.");
    }

    static void displaySummary() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double classTotal = 0;
        double classHighest = -1;
        double classLowest = 101;
        String highestStudent = "", lowestStudent = "";

        System.out.println("\n===== STUDENT GRADE SUMMARY =====");
        System.out.printf("%-20s %-12s %-12s %-12s%n",
                "Student", "Average", "Highest", "Lowest");

        for (Student s : students) {
            double avg = s.average();
            double high = s.highest();
            double low = s.lowest();

            System.out.printf("%-20s %-12.2f %-12.2f %-12.2f%n",
                    s.name, avg, high, low);

            classTotal += avg;
            if (high > classHighest) {
                classHighest = high;
                highestStudent = s.name;
            }
            if (low < classLowest) {
                classLowest = low;
                lowestStudent = s.name;
            }
        }

        System.out.printf("%nClass average: %.2f%n", classTotal / students.size());
        System.out.printf("Highest individual score: %.2f (%s)%n",
                classHighest, highestStudent);
        System.out.printf("Lowest individual score: %.2f (%s)%n",
                classLowest, lowestStudent);
    }

    static int readInt(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (Exception ignored) {}
            System.out.print("Invalid input. Enter a number from " + min + " to " + max + ": ");
        }
    }

    static double readDouble(double min, double max) {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (Exception ignored) {}
            System.out.print("Invalid input. Enter a value from " + min + " to " + max + ": ");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add student");
            System.out.println("2. Display summary");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt(1, 3);
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displaySummary();
                case 3 -> {
                    System.out.println("Thank you for using Student Grade Tracker.");
                    return;
                }
            }
        }
    }
}
