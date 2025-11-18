import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class MoodTracker {

    public static boolean isMoodValid(Mood mood, ArrayList<Mood> moodsList) throws InvalidMoodException {
        for (Mood tempMood : moodsList) {
            if (tempMood.equals(mood)) {
                throw new InvalidMoodException();
            }
        }
        return true;
    }

    public static boolean deleteMoods(LocalDate moodDate, ArrayList<Mood> moodsList) {
        return moodsList.removeIf(m -> m.getDate().equals(moodDate));
    }

    public static boolean deleteMood(Mood mood, ArrayList<Mood> moodsList) {
        return moodsList.removeIf(m -> m.equals(mood));
    }

    public static boolean editMood(Mood moodToEdit, ArrayList<Mood> moodsList) {
        for (Mood tempMood : moodsList) {
            if (tempMood.equals(moodToEdit)) {
                tempMood.setNotes(moodToEdit.getNotes());
                return true;
            }
        }
        return false;
    }

    public static void searchMoods(LocalDate moodDate, ArrayList<Mood> moodsList) {
        boolean found = false;
        for (Mood tempMood : moodsList) {
            if (tempMood.getDate().equals(moodDate)) {
                found = true;
                System.out.println(tempMood);
            }
        }
        if (!found) System.out.println("No matching records found!");
    }

    public static void searchMood(Mood mood, ArrayList<Mood> moodsList) {
        boolean found = false;
        for (Mood tempMood : moodsList) {
            if (tempMood.equals(mood)) {
                found = true;
                System.out.println(tempMood);
            }
        }
        if (!found) System.out.println("No matching record found!");
    }

    public static void main(String[] args) {
        ArrayList<Mood> moodsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to MoodTracker!");

        while (true) {
            System.out.println(
                "Press 'a' to add mood\n" +
                "'d' to delete mood(s)\n" +
                "'e' to edit mood\n" +
                "'s' to search for moods\n" +
                "'M' to get all moods\n" +
                "'w' to write the moods to a file\n" +
                "Type 'Exit' to exit"
            );

            String menuOption = scanner.nextLine();

            switch (menuOption) {
                case "a":
                    System.out.println("Enter mood name:");
                    String moodName = scanner.nextLine();
                    System.out.println("Tracking for current day? y/n");
                    String current = scanner.nextLine();
                    Mood moodToAdd = null;

                    if (current.equalsIgnoreCase("n")) {
                        try {
                            System.out.println("Enter date (MM/dd/yyyy):");
                            LocalDate date = LocalDate.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                            System.out.println("Enter time (HH:mm:ss):");
                            LocalTime time = LocalTime.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("HH:mm:ss"));

                            System.out.println("Enter notes:");
                            String notes = scanner.nextLine();

                            moodToAdd = new Mood(moodName, date, time, notes);

                        } catch (Exception e) {
                            System.out.println("Invalid date/time format.");
                            continue;
                        }
                    } else {
                        System.out.println("Enter notes:");
                        moodToAdd = new Mood(moodName, scanner.nextLine());
                    }

                    try {
                        if (isMoodValid(moodToAdd, moodsList)) {
                            moodsList.add(moodToAdd);
                            System.out.println("Mood added.");
                        }
                    } catch (InvalidMoodException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "d":
                    System.out.println("1. Delete by date\n2. Delete specific mood");
                    String del = scanner.nextLine();

                    if (del.equals("1")) {
                        try {
                            System.out.println("Enter date (MM/dd/yyyy):");
                            LocalDate date = LocalDate.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                            System.out.println(deleteMoods(date, moodsList)
                                        ? "Deleted." : "No entries found.");
                        } catch (Exception e) {
                            System.out.println("Invalid date.");
                        }
                    } else if (del.equals("2")) {
                        try {
                            System.out.println("Enter mood name:");
                            moodName = scanner.nextLine();

                            System.out.println("Enter date (MM/dd/yyyy):");
                            LocalDate date = LocalDate.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                            System.out.println("Enter time (HH:mm:ss):");
                            LocalTime time = LocalTime.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("HH:mm:ss"));

                            Mood delMood = new Mood(moodName, date, time);
                            System.out.println(deleteMood(delMood, moodsList)
                                        ? "Deleted." : "Not found.");
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                        }
                    }
                    break;

                case "e":
                    try {
                        System.out.println("Enter mood name:");
                        moodName = scanner.nextLine();

                        System.out.println("Enter date (MM/dd/yyyy):");
                        LocalDate date = LocalDate.parse(scanner.nextLine(),
                                DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                        System.out.println("Enter time (HH:mm:ss):");
                        LocalTime time = LocalTime.parse(scanner.nextLine(),
                                DateTimeFormatter.ofPattern("HH:mm:ss"));

                        System.out.println("Enter new notes:");
                        String notes = scanner.nextLine();

                        Mood moodToEdit = new Mood(moodName, date, time, notes);
                        System.out.println(editMood(moodToEdit, moodsList)
                                    ? "Updated." : "Not found.");
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                    }
                    break;

                case "s":
                    System.out.println("1. Search by date\n2. Search by details");
                    String search = scanner.nextLine();

                    if (search.equals("1")) {
                        try {
                            System.out.println("Enter date (MM/dd/yyyy):");
                            LocalDate date = LocalDate.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                            searchMoods(date, moodsList);
                        } catch (Exception e) {
                            System.out.println("Invalid date.");
                        }
                    } else if (search.equals("2")) {
                        try {
                            System.out.println("Enter mood name:");
                            moodName = scanner.nextLine();

                            System.out.println("Enter date (MM/dd/yyyy):");
                            LocalDate date = LocalDate.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                            System.out.println("Enter time (HH:mm:ss):");
                            LocalTime time = LocalTime.parse(scanner.nextLine(),
                                    DateTimeFormatter.ofPattern("HH:mm:ss"));

                            Mood mood = new Mood(moodName, date, time);
                            searchMood(mood, moodsList);
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                        }
                    }
                    break;

                case "M":
                    moodsList.forEach(System.out::println);
                    break;

                case "w":
                    try (PrintWriter writer = new PrintWriter(new FileWriter("Moods.txt"))) {
                        for (Mood m : moodsList) writer.println(m + "\n");
                        System.out.println("Written to file.");
                    } catch (IOException e) {
                        System.out.println("File write error.");
                    }
                    break;

                case "Exit":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
