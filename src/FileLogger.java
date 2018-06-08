import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileLogger {

    private PrintWriter writer;

    FileLogger(String fileName) throws UnsupportedEncodingException, FileNotFoundException {
        writer = new PrintWriter(fileName, "UTF-8");
    }

    public void log(Action action, HistoryEntry entry) {
        System.out.println(action.toString() + ": " + entry.toString());
        writer.println(action.toString() + ": " + entry.toString());
        writer.flush();
    }
}
