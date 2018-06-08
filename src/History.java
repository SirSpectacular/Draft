import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class History {
    private LinkedList<HistoryEntry> entriesAtCurrentState;
    private Stack<HistoryEntry> entriesToRedo;

    private FileLogger fileLogger;

    public History(FileLogger fileLogger) {
        entriesAtCurrentState = new LinkedList<>();
        entriesToRedo = new Stack<>();
        this.fileLogger = fileLogger;
    }

    public void addEntry(String content) {
        entriesAtCurrentState.push(new HistoryEntry(content));
        entriesToRedo = new Stack<>();

        fileLogger.log(Action.ADD, entriesAtCurrentState.peek());
    }

    public void redo() {

        if (!entriesToRedo.empty()) {
            entriesAtCurrentState.push(entriesToRedo.pop());

            fileLogger.log(Action.REDO, entriesAtCurrentState.peek());
        }
    }

    public void undo() {

        if (!entriesAtCurrentState.isEmpty()) {
            entriesToRedo.push(entriesAtCurrentState.pop());

            fileLogger.log(Action.UNDO, entriesToRedo.peek());
        }
    }

    public Date getCurrentEntryDate() {
        return (!entriesAtCurrentState.isEmpty()) ? entriesAtCurrentState.peek().getDateTime() : null;
    }

    public String getCurrentContent() {
        StringBuilder allContent = new StringBuilder();

        for (Iterator<HistoryEntry> it = entriesAtCurrentState.descendingIterator(); it.hasNext(); ) {
            HistoryEntry entry = it.next();
            allContent.append(entry.getContent());
        }

        return allContent.toString();
    }
}
