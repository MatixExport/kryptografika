package indie.outsource.View;

import indie.outsource.model.ByteOperations;
import indie.outsource.model.DesEncoder;
import indie.outsource.model.DesxEncoder;
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
import java.util.ArrayList;
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

    byte[] xd = new byte[]{0,0,0,1, 0,0,1,1, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,1, 0,1,1,1, 1,0,0,1, 1,0,0,1, 1,0,1,1, 1,0,1,1, 1,1,0,0, 1,1,0,1, 1,1,1,1, 1,1,1,1, 0,0,0,1};
    byte[] xd2 = new byte[]{1,1,0,1, 0,0,1,1, 0,0,1,1, 0,0,1,1, 0,1,0,1, 0,1,1,1, 0,1,1,1, 1,0,0,1, 0,1,1,1, 1,0,1,1, 1,0,1,1, 1,0,0,1, 1,1,0,1, 1,1,0,0, 1,1,1,1, 0,0,0,1};
    byte[] xd3 = new byte[]{1,0,0,1, 1,0,1,1, 0,0,1,1, 0,0,1,1, 0,1,0,1, 1,1,0,0, 0,1,0,1, 1,0,0,1, 0,1,1,1, 1,0,1,1, 1,0,0,1, 1,0,1,1, 1,1,0,1, 1,1,0,0, 1,1,1,1, 0,0,0,1};


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
    public byte[][] get_all_keys(){
        return new byte[][]{xd,ByteOperations.bits_to_bytes(xd2),ByteOperations.bits_to_bytes(xd3)};
    }
    public byte[] pack_and_encode(byte[] content){
        byte[][] keys = get_all_keys();
        byte[][] packages = ByteOperations.split_into_packages(content);
        for (int i = 0; i < packages.length; i++) {
            packages[i] = DesEncoder.encode(xd,packages[i]);
        }
        System.out.println("LAST ENCODED:");
        System.out.println(ByteOperations.byte_arr_to_string(packages[packages.length-1]));
        byte[] temp = ByteOperations.join_byte_arr(packages);
        System.out.println("FULL ENCODED:");
        System.out.println(ByteOperations.byte_arr_to_string(temp));
        return temp;
    }
    public byte[] decrypt_and_unpack(byte[] content){
        byte[][] keys = get_all_keys();
        System.out.println("FULL recived:");
        System.out.println(ByteOperations.byte_arr_to_string(content));
        byte[][] packages = ByteOperations.package_encrypted_msg(content);
        System.out.println("LAST RECIVED TO DECODE:");
        System.out.println(ByteOperations.byte_arr_to_string(packages[packages.length-1]));
        for (int i = 0; i < packages.length; i++) {
            packages[i] = DesEncoder.decode(xd,packages[i]);
        }

        return ByteOperations.unpackage_msg(packages);

    }
    public byte[] string_to_bytes(String string){
        return Base64.getDecoder().decode(string);
    }
    public String bytes_to_string(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
    public void encode(ActionEvent event) {
        if(file_mode){
            if(!is_file1_loaded){
                load_file_1(event); //przekazanie tu eventa to kolejny peek programing
            }

        }
        else{
            data1 = string_to_bytes(file_binary_1.getText());
        }
        data2 = pack_and_encode(data1);
        file_binary_2.setText(bytes_to_string(data2));
    }

    public void decode(ActionEvent event) {
        if(file_mode){
            if(!is_file2_loaded){
                load_file_2(event); //przekazanie tu eventa to kolejny peek programing
            }
        }
        else{
//            data2 = file_binary_2.getText().getBytes(StandardCharsets.UTF_8);    //
            data2 = string_to_bytes(file_binary_2.getText());

        }
        data1 = decrypt_and_unpack(data2);
        file_binary_1.setText(bytes_to_string(data1));
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
