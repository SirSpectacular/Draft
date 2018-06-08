import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class Controller implements Initializable {

    private History history;
    private InputBuffer inputBuffer;
    private ScheduledExecutorService scheduler;

    private final KeyCombination keyCombinationUndo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationRedo = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);

    @FXML
    private TextField field;

    @FXML
    private Label label;

    @FXML
    private void handleKeyReleased(KeyEvent event) {
        //System.out.println("Release");
        if (keyCombinationUndo.match(event)) {
            //System.out.println("Ctrl+Z pressed");

            history.undo();
            updateLayout();
        }
        else if(keyCombinationRedo.match(event)) {
            //System.out.println("Ctrl+Y pressed");

            history.redo();
            updateLayout();
        }
    }

    @FXML
    private void handleKeyTyped(KeyEvent event) {
        //System.out.println("Type");

        // Workaround :/
        int asciiChar = (int)(event.getCharacter().charAt(0));
        if (asciiChar == 25 || asciiChar == 26) return;

        //System.out.println(event.getCharacter() + " typed");
        //System.out.println("" + (int)(event.getCharacter().charAt(0)));

        inputBuffer.update(event.getCharacter());
        updateLayout();
    }

    private void updateLayout() {
        String text =
                (history.getCurrentEntryDate() != null)
                        ? "Version from: " + history.getCurrentEntryDate().toString() : "Initial version";
        label.setText(text);

        field.setText(history.getCurrentContent() + inputBuffer.get());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            history = new History(new FileLogger("draftLog.txt"));
            inputBuffer = new InputBuffer();
            startMonitoringBuffer();

        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        scheduler.shutdown();
    }

    private void startMonitoringBuffer() {
        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(
                () -> Platform.runLater(() -> {
                    if (!inputBuffer.isEmpty()) {
                        String content = inputBuffer.get();
                        history.addEntry(content);
                        inputBuffer.wipe();

                        updateLayout();
                    }
                }),
                1,
                1,
                TimeUnit.SECONDS);
    }
}