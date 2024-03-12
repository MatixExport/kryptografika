package indie.outsource.model;

public interface CharsetAdapter {
    String byte_to_string(byte[] bytes);
    byte[] string_to_byte(String string);

}
