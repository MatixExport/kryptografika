package indie.outsource.View;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiException {
    public GuiException(String text) {
        TextArea textArea = new TextArea(text);

        BorderPane borderPane = new BorderPane(textArea);

        textArea.setWrapText(true);
        textArea.setEditable(false);

        Scene scene = new Scene(borderPane, 600, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Error occurred");
        stage.show();
    }
}
