import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Draft extends Application {

    private Controller controllerHandle;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("draft.fxml"));
        Parent root = loader.load();

        controllerHandle = loader.getController();

        Scene scene = new Scene(root, 500, 100);
        primaryStage.setTitle("DraftBook");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        controllerHandle.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}