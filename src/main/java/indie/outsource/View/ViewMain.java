package indie.outsource.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
/*
            Autorzy
NAZWISKO    IMIE    INDEX
Giełczyński Mateusz 247662
Kubiś       JAKUB   247712
 */

public class ViewMain extends Application{
    private Parent loadFxml() {
        try {
            return FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainForm.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
