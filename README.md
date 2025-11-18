# Mood Tracker Application

A simple Java console-based application that allows users to track their moods with date, time, and notes.  
It supports adding, editing, deleting, searching, and writing moods to a text file.

---

## 🚀 Features

### ✔ Add Mood  
- Allows users to enter mood name, date, time, and notes  
- Validates if a mood entry already exists for the same date and time

### ✔ Delete Mood  
- Delete all moods from a specific date  
- OR delete a specific mood matching name + date + time

### ✔ Edit Mood Notes  
- Allows editing notes of an existing mood entry

### ✔ Search  
- Search moods by date  
- OR search for a specific mood

### ✔ Display All Moods  

### ✔ Write to File  
- Saves all mood entries into `Moods.txt`

---

## 📂 Project Structure

MoodTracker/
├── Mood.java # Mood class with attributes and methods
├── InvalidMoodException.java # Custom exception for duplicate moods
├── MoodTracker.java # Main application with all menu options
└── Moods.txt # (Generated after writing to file)


---

## 🛠️ Technologies Used
- **Java 8+**
- **ArrayList**
- **LocalDate & LocalTime**
- **Custom Exceptions**
- **File Writing (PrintWriter)**

---

## ▶ How to Run

1. Ensure Java 8 or higher is installed.
2. Compile the files:

```bash
javac Mood.java InvalidMoodException.java MoodTracker.java
