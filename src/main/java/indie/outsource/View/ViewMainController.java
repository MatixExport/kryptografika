package indie.outsource.View;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    private boolean file_mode = true;
    private byte[] data1;
    private byte[] data2;
    private boolean is_file1_loaded = false;
    private boolean is_file2_loaded = false;


    public void load_file_1(ActionEvent event) {
        File file = select_file_load_dialog("Select file", "");
        filename1.setText(String.valueOf(file.toURI()));
        try {
            data1 = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file_binary_1.setText(new String(data1));
        is_file1_loaded = true;
    }

    public void save_file_1(ActionEvent event) {
        File file = select_file_save_dialog("Save file", "");
        try {
            Files.createFile(file.toPath());
            Files.write(file.toPath(),data1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void load_file_2(ActionEvent event) {
        File file = select_file_load_dialog("Select file", "");
        filename2.setText(String.valueOf(file.toURI()));
        try {
            data2 = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file_binary_2.setText(new String(data2));
        is_file2_loaded = true;
    }

    public void save_file_2(ActionEvent event) {
        File file = select_file_save_dialog("Save file", "");
        try {
            Files.createFile(file.toPath());
            Files.write(file.toPath(),data2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encode(ActionEvent event) {
        if(file_mode){
            if(!is_file1_loaded){
                load_file_1(event); //przekazanie tu eventa to kolejny peek programing
            }
        }
        else{
            data1 = file_binary_1.getText().getBytes(StandardCharsets.UTF_8);    //czy aby na pewno?
        }

        //encoder.encode()

    }

    public void decode(ActionEvent event) {
        if(file_mode){
            if(!is_file2_loaded){
                load_file_2(event); //przekazanie tu eventa to kolejny peek programing
            }
        }
        else{
            data2 = file_binary_2.getText().getBytes(StandardCharsets.UTF_8);    //czy aby na pewno?
        }

        //encoder.encode()

    }

    public void generate_keys(ActionEvent event) {
        key0.setText("encoder.generate_key");
        key1.setText("encoder.generate_key");
        key2.setText("encoder.generate_key");
    }

    public void select_encode_type_file(ActionEvent event) {
        filename1.setDisable(false);
        filename2.setDisable(false);
        file_mode = true;
    }

    public void select_encode_type_text(ActionEvent event) {
        filename1.setDisable(true);
        filename2.setDisable(true);
        file_mode = false;
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
