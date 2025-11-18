
public class InvalidMoodException extends Exception {
    public InvalidMoodException() {
        super("Mood already exists for this date and time.");
    }
}
