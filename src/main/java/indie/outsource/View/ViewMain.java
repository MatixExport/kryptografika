package indie.outsource.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewMain extends Application{
    private Parent loadFxml() {
        try {
            return FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainForm.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
            // kolejny peek programing z naszej strony
        }
    }


    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(loadFxml());
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
