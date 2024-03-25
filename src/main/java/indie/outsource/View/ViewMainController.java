package indie.outsource.View;

import indie.outsource.model.ByteOperations;
import indie.outsource.model.CharsetAdapter;
import indie.outsource.model.Util;
import indie.outsource.model.ViewEncodersAdapters.DesToViewEncoderAdapter;
import indie.outsource.model.ViewEncodersAdapters.DesxToViewEncoderAdapter;
import indie.outsource.model.ViewEncodersAdapters.ViewEncoder;
import indie.outsource.model.charsetAdapters.ToCharCharsetAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.UnaryOperator;

public class ViewMainController {

    //region fxml controls
    public ToggleGroup group;
    public TextField filename1;
    public TextField filename2;
    public Button load_file_1;
    public TextField key0;
    public TextField key1;
    public TextField key2;

    public ComboBox<String> encoder_box;

    public TextArea file_binary_1;
    public TextArea file_binary_2;

    //endregion
    //region vars
    private boolean file_mode = true;
    private byte[] data1;
    private byte[] data2;
    private boolean is_file1_loaded = false;
    private boolean is_file2_loaded = false;

    CharsetAdapter selectedCharsetAdapter = new ToCharCharsetAdapter();
    ObservableList<String> encode_options =
            FXCollections.observableArrayList(
                    "DES", "DESX"
            );
    Map<String, ViewEncoder> encoders_map = new HashMap<>() {{
        put("DES", new DesToViewEncoderAdapter());
        put("DESX", new DesxToViewEncoderAdapter());
    }};

    //endregion
    //region init
    public void initialize() {
        key0.setTextFormatter(hexTextFormatter());
        key1.setTextFormatter(hexTextFormatter());
        key2.setTextFormatter(hexTextFormatter());
        encoder_box.setItems(encode_options);
        encoder_box.getSelectionModel().selectFirst();
        generate_keys();
    }

    public static TextFormatter<TextFormatter.Change> hexTextFormatter() {
        UnaryOperator<TextFormatter.Change> hexFilter = change -> {
            return change.getControlNewText().matches("[0-9a-fA-F]{0,16}") ? change : null;
        };
        return new TextFormatter<>(hexFilter);
    }
    //endregion
    //region file load/save
    @FXML
    private void load_file_1() {
        File file = select_file_load_dialog("Select file", "");
        filename1.setText(String.valueOf(file.toURI()));
        try {
            data1 = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (Exception e) {
            new GuiException(Arrays.toString(e.getStackTrace()));
        }
        file_binary_1.setText(new String(data1));
        is_file1_loaded = true;
    }

    @FXML
    private void save_file_1() {
        File file = select_file_save_dialog("Save file", "");
        try {
            Files.createFile(file.toPath());
            Files.write(file.toPath(), data1);
        } catch (Exception e) {
            new GuiException(Arrays.toString(e.getStackTrace()));
        }
    }

    @FXML
    private void load_file_2() {
        File file = select_file_load_dialog("Select file", "");
        filename2.setText(String.valueOf(file.toURI()));
        try {
            data2 = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (Exception e) {
            new GuiException(Arrays.toString(e.getStackTrace()));
        }
        file_binary_2.setText(new String(data2));
        is_file2_loaded = true;
    }

    @FXML
    private void save_file_2() {
        File file = select_file_save_dialog("Save file", "");
        try {
            Files.createFile(file.toPath());
            Files.write(file.toPath(), data2);
        } catch (Exception e) {
            new GuiException(Arrays.toString(e.getStackTrace()));
        }
    }

    private File select_file_load_dialog(String title, String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showOpenDialog(load_file_1.getScene().getWindow());
    }

    private File select_file_save_dialog(String title, String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showSaveDialog(load_file_1.getScene().getWindow());
    }

    //endregion
    //region encode/decode
    @FXML
    private void encode() {
        if (file_mode) {
            if (!is_file1_loaded) {
                load_file_1();
            }
        } else {
            data1 = string_to_bytes(file_binary_1.getText());
            System.out.println(ByteOperations.byte_arr_to_hex(data1));
        }
        data2 = Util.pack_and_encode(get_current_encoder(), get_all_keys(), data1);
        System.out.println("ENCODED TEXT");
        System.out.println(ByteOperations.byte_arr_to_hex(data2));
        file_binary_2.setText(bytes_to_string(data2));
    }

    @FXML
    private void decode() {
        if (file_mode) {
            if (!is_file2_loaded) {
                load_file_2();
            }
        } else {
            data2 = string_to_bytes(file_binary_2.getText());
        }

        data1 = Util.decrypt_and_unpack(get_current_encoder(), get_all_keys(), data2);
        file_binary_1.setText(bytes_to_string(data1));
    }

    private ViewEncoder get_current_encoder() {
        return encoders_map.get(encoder_box.getValue());
    }
    //endregion
    //region keys
    public byte[][] get_all_keys() {
        HexFormat hexFormat = HexFormat.of();

        if (Objects.equals(encoder_box.getValue(), "DES")) {
            if (key0.getText().length() < 16) {
                new GuiException("At least one of the keys is missing or has wrong length.");
            }
        } else {
            if (key0.getText().length() < 16 | key1.getText().length() < 16 | key2.getText().length() < 16) {
                new GuiException("At least one of the keys is missing or has wrong length.");
            }
        }

        return new byte[][]{
                hexFormat.parseHex(key0.getText()),
                hexFormat.parseHex(key1.getText()),
                hexFormat.parseHex(key2.getText())
        };

    }

    public void generate_keys() {
        key0.setText(Util.genereateHexString(16));
        key1.setText(Util.genereateHexString(16));
        key2.setText(Util.genereateHexString(16));
    }
    //endregion
    //region other
    public byte[] string_to_bytes(String string) {
        return selectedCharsetAdapter.string_to_byte(string);
    }

    public String bytes_to_string(byte[] bytes) {
        return selectedCharsetAdapter.byte_to_string(bytes);
    }


    @FXML
    private void select_encode_type_file() {
        filename1.setDisable(false);
        filename2.setDisable(false);
        file_mode = true;
    }

    @FXML
    private void select_encode_type_text() {
        filename1.setDisable(true);
        filename2.setDisable(true);
        file_mode = false;
    }
    //endregion

}
