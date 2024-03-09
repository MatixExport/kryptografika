package indie.outsource.View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class ViewMainController {
    public ToggleGroup group;
    public TextField filename1;
    public TextField filename2;
    public Button load_file_1;
    public TextField key0;
    public TextField key1;
    public TextField key2;

    public TextArea file_binary_1;
    public TextArea file_binary_2;



    public void load_file_1(ActionEvent event) {
        File file = select_file_load_dialog("Select file", "");
        filename1.setText(String.valueOf(file.toURI()));
        byte[] file1_all_bytes;
        try {
            file1_all_bytes = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file_binary_1.setText(new String(file1_all_bytes));
    }

    public void save_file_1(ActionEvent event) {
    }
    public void load_file_2(ActionEvent event) {
        File file = select_file_load_dialog("Select file","");

    }

    public void save_file_2(ActionEvent event) {

    }

    public void encode(ActionEvent event) {
    }

    public void decode(ActionEvent event) {
    }

    public void generate_keys(ActionEvent event) {
    }

    public void select_encode_type_file(ActionEvent event) {
    }

    public void select_encode_type_text(ActionEvent event) {
    }


    private File select_file_load_dialog(String title, String filename){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showOpenDialog(load_file_1.getScene().getWindow());
    }
    private File select_file_save_dialog(String title, String filename){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showSaveDialog(load_file_1.getScene().getWindow());
    }
}
