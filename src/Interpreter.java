import java.util.Stack;

public class Interpreter {
    private Stack<Entry> entriesAtCurrentState;
    private Stack<Entry> entriesToRedo;

    private FileLogger fileLogger;

    public Interpreter(FileLogger fileLogger) {
        entriesAtCurrentState = new Stack<>();
        entriesToRedo = new Stack<>();
        this.fileLogger = fileLogger;
    };

    public Display addEntry(String content) {

        entriesAtCurrentState.push(new Entry(content));
        entriesToRedo = new Stack<>();
        fileLogger.log(Action.ADD, entriesAtCurrentState.peek());

        return new Display();
    }   //TODO: Przemyslec Display vs getDisplay();

    public Display redo() {

        if (!entriesToRedo.empty()) {
            entriesAtCurrentState.push(entriesToRedo.pop());
            fileLogger.log(Action.REDO, entriesAtCurrentState.peek());
        }
        //TODO: else throw exc

        return new Display();
    }

    public Display undo() {

        if (!entriesAtCurrentState.empty()) {
            entriesToRedo.push(entriesAtCurrentState.pop());
            fileLogger.log(Action.UNDO, entriesToRedo.peek());
        }
        //TODO: else throw exc

        return  new Display();
    }
}
